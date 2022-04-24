package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadManager {

    private Socket client_socket;
    private PrintStream client_out;
    private BufferedReader client_in;
    private String ip = "127.0.0.1";
    private int port = 5888;

    private String downloadSavePath = "/Users/x/Downloads/test";

    public DownloadManager() {

        connectServer();

        Scanner s = new Scanner(System.in);
        try{
            String message = null;
            while(true){
                message = s.nextLine();
                if(message.equals("quit") || message.equals("q")){
                    break;
                }
                client_out.println(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * connect to server
     */
    private void connectServer() {

        try {
            //init
            client_socket = new Socket(ip, port);
            client_out = new PrintStream(client_socket.getOutputStream(), true);
            client_in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));

            //get file list
            client_out.println("index");

            //thread for listening
            new ClientThread().start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * listening
     */
    class ClientThread extends Thread {
        public void run() {
            try {
                String fromServer_data;
                while ((fromServer_data = client_in.readLine()) != null) {

                    if (fromServer_data.startsWith("list")) {
                        String[] fileList = ParseMessageUtil.getFileList(fromServer_data);
                        for (String filename : fileList) {
                            System.out.println(filename);
                        }
                    }

                    if (fromServer_data.startsWith("ok")) {
                        String downFileName = ParseMessageUtil.getDownFileName(fromServer_data);
                        String downFileSize = ParseMessageUtil.getDownFileSize(fromServer_data);

                        new HandelFileThread(0, downFileName, downFileSize).start();
                    }

                    if (fromServer_data.startsWith("error")) {
                        String res = ParseMessageUtil.getDownResult(fromServer_data);
                        System.out.println(res);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * thread for download
         */
        class HandelFileThread extends Thread {
            private String filename;
            private Long fileSize;

            public HandelFileThread(int mode, String filename, String fileSize) {
                this.filename = filename;
                this.fileSize = Long.parseLong(fileSize);
            }

            public void run() {
                try {
                    Socket socket = new Socket(ip, 8888);
                    BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(downloadSavePath + "/" + filename));

                    int len;
                    byte[] arr = new byte[8192];
                    double sumDown = 0;
                    int i = 0;

                    System.out.println(filename + " :start download");
                    while ((len = bis.read(arr)) != -1) {
                        sumDown += len;

                        if (i++ % 100 == 0){
                            System.out.println(filename + " :downloading：" + Math.round(100 * sumDown / fileSize)  + "%");
                        }

                        bos.write(arr, 0, len);
                        bos.flush();
                    }

                    bos.close();
                    bis.close();
                    socket.close();
                    System.out.println(filename + " :download finished");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new DownloadManager();
    }
}
