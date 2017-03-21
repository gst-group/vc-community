package com.wenjuan.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class EmojiUtil {
    public static BufferedReader bufread;
    private static String path = "";
    private static String readStr = "";

    public static void main(String[] args) {
        String s = "sadf";
        boolean a = s.contains("ad");
        System.out.println(a);
    }

    public static String test(String content, String emos, HttpServletRequest req) {
        path = req.getSession().getServletContext().getRealPath("") + "/emoji.txt";
        File filename = new File(path);

        String read;
        String c = "";
        FileReader fileread;
        try {
            fileread = new FileReader(filename);
            bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {


                    String emo = read.substring(read.indexOf(':') + 1, read.lastIndexOf(";"));

                    String ji = "";
                    if (emos.equals(emo)) {
                        ji = read.substring(1, read.indexOf(":"));
                        //System.out.println(req.getServletContext().getRealPath(File.separator)+"/emoji/"+ji);
                        c = content.replace(emos, "<img src=\"" +"../emoji/" + ji + ".png\">");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    //	public static void main(String[] args) {
//		String content="waefwaef[df呲牙][df抓狂][df奋斗]awefewa";
//		  String read;
//		  String c;
//	        FileReader fileread;
//	        try {
//	            fileread = new FileReader(filename);
//	            bufread = new BufferedReader(fileread);
//	            try {
//	                while ((read = bufread.readLine()) != null) {
//	                	// String emoji="[df呲牙]";
//	     		        String emo = read.substring(read.indexOf(':')+1, read.lastIndexOf(";"));
//	     		        String content2 =content.replace(emo,"a");
//	     		        if(!content2.equals(content)){
//	     		        	//获取匹配到的emo
//	     		        	c=test(content,emo);
//	     		        	content=c;
//	     		        }
//
//	     		        }
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        }
//	        System.out.println(content);
//	}
    public String emoJi(String content, HttpServletRequest req) {
        path = req.getSession().getServletContext().getRealPath("") + "/emoji.txt";

        File filename = new File(path);

        String read;
        String c;
        FileReader fileread;
        try {
            fileread = new FileReader(filename);
            bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                    // String emoji="[df呲牙]";
                    String emo = read.substring(read.indexOf(':') + 1, read.lastIndexOf(";"));
                    String content2 = content.replace(emo, "a");
                    if (!content2.equals(content)) {
                        //获取匹配到的emo
                        c = test(content, emo, req);
                        content = c;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
}