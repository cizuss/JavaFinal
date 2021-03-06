/**
 * LogFileWriter.java
 *
 * Copyright (c) 2014 Teamnet. All Rights Reserved.
 *
 * This source file may not be copied, modified or redistributed,
 * in whole or in part, in any form or for any reason, without the express
 * written consent of Teamnet.
 **/
package ro.teamnet.zth.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class LogFileWriter {
    public static final String TOMCAT_PATH=System.getenv("CATALINA_HOME").replace(";","");

    /**
     * This method log headers to %CATALINA_HOME%\logs\header.log
     * @param headerName - Header name
     * @param headerValue - Header value
     */
    public static void logHeader(String headerName,String headerValue){

        try {
            RandomAccessFile randomAccessFile=new RandomAccessFile("/usr/local/tomcat/conf/logs/header.log","rw");
            StringBuilder sb=new StringBuilder();
            String ln;
            while((ln=randomAccessFile.readLine())!=null){
//                sb.append(ln).append("\r\n");
            }
            sb.append(new Date()).append(":").append(headerName).append(":").append(headerValue).append("\r\n");
            randomAccessFile.writeBytes(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        logHeader("un_header","valoare header");
    }


}
