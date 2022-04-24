package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    private final int port = 5888;
    private ServerSocket server_socket;   // socket for message
    private ServerSocket fileServerSocket;  // socket for download

    private String path ="/Users/x/Desktop/test"; //temp file path

    public FileServer() {
        try {
            //init
            server_socket = new ServerSocket(this.port);
            fileServerSocket = new ServerSocket(8888);

            System.out.println("listening.....");

            while(true) {
                Socket client_socket = server_socket.accept();
                System.out.println("client:"+client_socket.getRemoteSocketAddress()+" established");
                new ServerThread(client_socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *   thread for handling client request
     */
    private class ServerThread extends Thread{
        private Socket client_socket;
        private BufferedReader server_in;
        private PrintWriter server_out;

        public ServerThread(Socket client_socket) {
            try {
                //init
                this.client_socket = client_socket;
                server_in = new BufferedReader(new InputStreamReader(this.client_socket.getInputStream()));
                server_out = new PrintWriter(this.client_socket.getOutputStream(),true);


            } catch (IOException e) {
            }
        }

        public void run() {
            try {
                String fromClientData ;

                while((fromClientData = server_in.readLine()) != null){
                    //get the file list
                    if(fromClientData.startsWith("index")){
                        File dir = new File(path);
                        if (dir.isDirectory()){
                            String[] list = dir.list();
                            String filelist = "list[";
                            for (int i = 0; i < list.length; i++) {
                                if (i == list.length-1){
                                    filelist  = filelist + list[i]+"]";
                                }else {
                                    filelist  = filelist + list[i]+":";
                                }
                            }
                            server_out.println(filelist);
                        }
                    } else if(fromClientData.startsWith("get")){
                        String[] fileNames  = ParseMessageUtil.getRequestDownFileName(fromClientData);
                        for (String fileName: fileNames){
                            File file = new File(path,fileName);
                            if(!file.exists()){
                                server_out.println("error["+ fileName + " doesn't exist]");
                            }else {
                                server_out.println("ok["+file.getName()+":"+file.length()+"]");
                                new HandleFileThread(0,file.getName(),file.length()+"").start();
                            }
                        }
                    } else {
                        System.out.println("unknown command!");
                        server_out.println("error[unknown command]");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         *   thread for downloading file
         */
        class HandleFileThread extends Thread{
            private String filename;
            private String filesize;

            public HandleFileThread(int mode,String name,String size){
                filename = name;
                filesize = size;
            }

            public void run() {
                try {
                    Socket socket = fileServerSocket.accept();

                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path,filename)));
                    BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

                    System.out.println(filename + "：start download");
                    int len;
                    byte[] arr =new byte[8192];
                    while((len = bis.read(arr)) != -1){
                        bos.write(arr,0,len);
                        bos.flush();
                    }

                    socket.shutdownOutput();
                    System.out.println(filename + "：download finished");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new FileServer();
    }
}
