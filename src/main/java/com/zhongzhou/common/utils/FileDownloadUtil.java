package com.zhongzhou.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @ClassName: FileDownloadUtil
 * @Description: 文件下载工具类
 * @Author: wj
 * @Date: 2020-03-08 18:06:36
 **/
public class FileDownloadUtil implements Serializable {
    private static final long serialVersionUID = -5899668355856525182L;

    /**
     * 下载文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public static void download(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) {
        File file = new File(filePath + File.separator + fileName);
        //下载
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            throw new RuntimeException("文件已被删除");
        }
    }
}
