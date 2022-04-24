package com.company;

public class ParseMessageUtil {

    /**
     * download message： ok["fileName":"fileSize"]
     *        fileName
     *        fileSize
     */
    public static String getDownFileName(String data){
        return data.substring(data.indexOf("[")+1,data.indexOf(":"));
    }
    public static String getDownFileSize(String data){
        return data.substring(data.indexOf(":")+1,data.length()-1);
    }
    /**
     * result message： ok["message"] or error["message"]
     */
    public static String getDownResult(String data){
        return data.substring(data.indexOf("[")+1,data.length()-1);
    }
    /**
     * request message： get "fileName","fileName"
     */
    public static String[] getRequestDownFileName(String data){
        return data.substring(data.indexOf("get ")+4,data.length()).split(",");
    }

    /**
     * list["fileName":"fileName":"fileName"...]
     */
    public static String[] getFileList(String data){
        String list = data.substring(data.indexOf("[")+1,data.length()-1);
        return  list.split(":");
    }
}
