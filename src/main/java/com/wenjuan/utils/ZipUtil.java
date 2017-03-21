package com.wenjuan.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static void writeZip(List<String> files, String fileName, HttpServletResponse response) throws IOException {
        String outputFilePath = System.getProperty("java.io.tmpdir") + "/excel" + RandomStringUtils.random(10, true, true) + ".zip";
        OutputStream os = new BufferedOutputStream(new FileOutputStream(outputFilePath));
        ZipOutputStream zos = new ZipOutputStream(os);
        byte[] buf = new byte[12 * 512];
        int len;
        for (String fileStr : files) {
            File file = new File(fileStr);
            if (!file.exists() || !file.isFile()) continue;
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            while ((len = bis.read(buf)) > 0) {
                zos.write(buf, 0, len);
            }
        }
        zos.closeEntry();
        zos.close();
        File tmpFile = new File(outputFilePath);
        InputStream is = new FileInputStream(tmpFile);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        response.setContentType("application/zip");//根据个人需要,这个是下载文件的类型
        javax.servlet.ServletOutputStream out = response.getOutputStream();
        StreamUtils.copy(is, out);
        tmpFile.delete();
    }
}
