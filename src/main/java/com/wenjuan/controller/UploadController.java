package com.wenjuan.controller;

import com.netease.is.antispam.demo.v2.ImageCheckAPIDemo;
import com.wenjuan.bean.MessageBean;
import com.wenjuan.model.User;
import com.wenjuan.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping(value = "/upload")
public class UploadController {
  static String tempFolder = System.getProperty("java.io.tmpdir");

  private static Map<String, List<String>> tagFiles = new HashMap<>();
  private static Logger logger = Logger.getLogger(UploadController.class);

  @Autowired
  HttpServletRequest request;

  @Autowired
  @Qualifier("userService")
  UserService userService;

  /**
   * @param src  图片输入流,类型为InputStream或byte[]
   * @param type 上传类型，0为图片，1为avatar
   * @return extra相对于IP地址的path路径，
   * 例如访问路径为 http://0.0.0.0/1/2.jpg,将返回/1/2.jpg
   */
  public static MessageBean uploadImage(Object src, HttpServletRequest request, UserService userService, int type) {
    logger.info("upload-------------------------------");
    assert src != null && (src instanceof InputStream || src instanceof byte[]);
    User user = User.getCurrentUser();
    if (type == 1 && user == null) {
      return MessageBean.UNLOGIN;
    }
    MessageBean messageBean;
    if (type == 0 || type == 1) {
      String directory = type == 0 ? "image" : "avatar";
      String path = request.getSession().getServletContext().getRealPath(directory);
      File directoryFile = new File(path);
      if (!directoryFile.exists()) {
        directoryFile.mkdirs();
      }
      logger.info(path+"************************************");
      String fileName = String.format("%d%d.jpg", System.currentTimeMillis(), Math.abs(new Random().nextLong()));
      File targetFile = new File(directoryFile, fileName);
      //保存
      try {
        if (!targetFile.exists()) {
          targetFile.createNewFile();
        }
        OutputStream os = new FileOutputStream(targetFile);
        if (src instanceof InputStream) {
          IOUtils.copy((InputStream) src, os);
        } else if (src instanceof byte[]) {
          byte[] imgSrc = (byte[]) src;
          for (int i = 0; i < imgSrc.length; ++i) {
            if (imgSrc[i] < 0) {//调整异常数据
              imgSrc[i] += 256;
            }
          }
          os.write(imgSrc);
        }
        os.flush();
        os.close();
      } catch (Exception e) {
        return MessageBean.SYSTEM_ERROR;
      }
      messageBean = MessageBean.SUCCESS.clone();
      String relativePath = targetFile.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath(""), "");
      logger.info("imgUrl++++++++++++++++++++++++++"+relativePath);
      try {
        if (checkImage(targetFile.getAbsolutePath())) {
          messageBean.setExtra(relativePath);
          if (type == 1) {
            User newUserInfo = new User();
            newUserInfo.setId(user.getId());
            newUserInfo.setAvatar(relativePath);
            userService.updateByPrimaryKey(newUserInfo);
          }
          return messageBean;
        } else {
          return MessageBean.getInvalidField("图片");
        }
      } catch (Exception e) {
        logger.info("error---------------------------");
        e.printStackTrace();
        return MessageBean.SYSTEM_ERROR;
      }
    } else {
      messageBean = MessageBean.getInvalidField("type");
    }
    return messageBean;
  }

  /**
   * 上传视频，分段上传
   * 只支持Mp4文件
   *
   * @param file  上传的部分文件
   * @param tag   上传文件的标识，第一次上传为空字符串，第一次上传完成后会获得到tag供下一次上传。
   * @param isEnd 上传是否完成，默认为true
   * @return isEnd为false时tag，true为extra相对于主机的path路径，
   * 例如访问路径为 http://0.0.0.0/1/2.mp4,将返回/1/2.mp4
   */
  @RequestMapping(value = "/video")
  public MessageBean uploadVideo(@RequestParam(value = "file") MultipartFile file, @RequestParam String tag, @RequestParam(defaultValue = "true") boolean isEnd) {
    MessageBean messageBean = MessageBean.SUCCESS.clone();
    List<String> listFile;
    if (tag == null) {
      tag = String.format("%s_%d", RandomStringUtils.random(10), System.currentTimeMillis());
      listFile = new ArrayList<>();
      tagFiles.put(tag, listFile);
    } else {
      listFile = tagFiles.get(tag);
    }
    if (listFile == null) {
      return MessageBean.getInvalidField("标识");
    }
    try {
      File tmpFile = File.createTempFile(tag, "tmp");
      OutputStream os = new FileOutputStream(tmpFile);
      InputStream is = file.getInputStream();
      byte[] buffer = new byte[6 * 512];
      int readCount = 0;
      while ((readCount = is.read(buffer)) != -1) {
        os.write(buffer, 0, readCount);
      }
      is.close();
      os.close();
      listFile.add(tmpFile.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (isEnd) {
      String path = combine(tag);
      if (path.isEmpty()) {
        messageBean = MessageBean.getInvalidField("文件类型");
      } else {
        messageBean.setExtra(path);
      }
    } else {
      messageBean.setExtra(tag);
    }
    return messageBean;
  }

  /**
   * 返回合并的文件的路径
   *
   * @param tag
   * @return
   */
  private String combine(String tag) {
    List<String> fileStrs = tagFiles.get(tag);
    File targetFile = new File(request.getSession().getServletContext().getRealPath("file"), String.format("%d_%d.mp4", System.currentTimeMillis(), (new Random().nextLong())));
    OutputStream targetFileOutputStream = null;
    try {
      targetFileOutputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
      byte[] buffer = new byte[6 * 512];
      int readCount = 0;
      for (String fileStr : fileStrs) {
        InputStream is = new FileInputStream(fileStr);
        while ((readCount = is.read(buffer)) != -1) {
          targetFileOutputStream.write(buffer, 0, readCount);
        }
        is.close();
      }
      String contentType = new MimetypesFileTypeMap().getContentType(targetFile);

      if (contentType.startsWith("video")) {
        return targetFile.getAbsolutePath().replace(request.getSession().getServletContext().getRealPath(""), "");
      } else {
        targetFile.delete();///不是合法的视频文件
        return "";
      }
    } catch (Exception e) {
      return "";
    } finally {
      try {
        if (targetFileOutputStream != null) {
          targetFileOutputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 上传图片,二进制流
   *
   * @param image 上传的图片
   * @param type  上传类型，0为图片，1为avatar
   * @return extra相对于IP地址的path路径，
   * 例如访问路径为 http://0.0.0.0/1/2.jpg,将返回/1/2.jpg
   */
  @RequestMapping(value = "/image")
  public MessageBean uploadImage(@RequestParam(value = "image") MultipartFile image, @RequestParam(defaultValue = "0") int type) {
    if (image == null) {
      return MessageBean.getInvalidField("上传文件");
    }
    InputStream is = null;
    try {
      is = image.getInputStream();
      return uploadImage(is, request, userService, type);
    } catch (IOException e) {
      return MessageBean.SYSTEM_ERROR;
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * base64上传方式：
   * 请求url为/upload/imageByBase64.do?type=1
   * body为纯base64字符串，不要换行，不要其他字符。
   *
   * @param type 上传类型，0为图片，1为avatar
   * @return extra相对于IP地址的path路径，
   * 例如访问路径为 http://0.0.0.0/1/2.jpg,将返回/1/2.jpg
   */
  @RequestMapping("/imageByBase64")
  public MessageBean uploadImageByBase64(@RequestParam(defaultValue = "0") int type, @RequestParam(required = false) String picss) {
    try {
      String splitSymbol = "pics=";
      String base64 = IOUtils.toString(request.getInputStream());
      int indexOfEqual = base64.indexOf(splitSymbol);
      if (indexOfEqual >= 0) {
        base64 = base64.substring(indexOfEqual + splitSymbol.length());
      }
      if (base64.isEmpty()) {
        if (picss.isEmpty()) {
          return MessageBean.getInvalidField("上传图片不存在或图片超过大小限制");

        } else {
          base64 = picss;
        }
      }
      return uploadImage(new BASE64Decoder().decodeBuffer(base64), request, userService, type);
    } catch (IOException e) {
      e.printStackTrace();
      return MessageBean.SYSTEM_ERROR;
    }
  }

  private static boolean checkImage(String absolutePath) throws Exception {
    logger.info("IMGURL"+absolutePath);
    return ImageCheckAPIDemo.checkImage(absolutePath);
  }
}
