package com.wenjuan.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

@Controller
@RequestMapping("/util")
public class UtilController {

    Logger logger = Logger.getLogger(UtilController.class);

    static private String tmpDir = System.getProperty("java.io.tmpdir");

    @RequestMapping("/amr2mp3")
    public void convertAmr2Mp3(@RequestParam String url, HttpServletResponse response) throws IOException {
        URL urlRequest = new URL(url);
        File source = new File(tmpDir + "/amr" + RandomStringUtils.random(10, true, true) + ".amr");
        source.createNewFile();
        OutputStream os = new FileOutputStream(source);
        StreamUtils.copy(urlRequest.openStream(), os);
        os.close();
        File dest = new File(tmpDir + "/amr" + RandomStringUtils.random(10, true, true) + ".mp3");

        Runtime run = Runtime.getRuntime();
        String cmd = String.format("/usr/local/bin/ffmpeg -i %s -acodec libmp3lame %s", source.getAbsoluteFile(), dest.getAbsoluteFile());
        logger.info(cmd);
        try {
            run.exec(cmd).waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        InputStream is = new FileInputStream(dest);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(dest.getName(), "UTF-8") + "\"");
        response.setContentType("audio/mp3");
        javax.servlet.ServletOutputStream out = response.getOutputStream();
        StreamUtils.copy(is, out);
        is.close();
        source.delete();
        dest.delete();
    }
}
