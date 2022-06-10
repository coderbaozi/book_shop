package com.bookshop.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取下载的文件名
        String downFileName="VCG21d6ab26ba9.jpg";
        //2.获取要下载的内容（通过servletContext对象可以读取）
        ServletContext servletContext = getServletContext();
        //回传之前告诉客户端，返回的数据类型
        //获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/file/" + downFileName);
        resp.setContentType(mimeType);

        //告诉客户端收到的数据是用于下载的还是用于响应头
        //Content-Disposition响应头，表示收到的数据怎么处理
        //attachment表示附件 表示下载使用
        //filename=文件名表示指定下载的文件名
        if(req.getHeader("User-Agent").contains("firefox")){
            resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("中国.jpg","UTF-8"));
        }else{
           // resp.setHeader("Content-Disposition","attachment;filename==?UTF-8?B?"+encoder.encode("这是编码内容".getBytes(StandardCharsets.UTF_8)));
        }

        //  在服务器端，/表示http://ip:port/工程名/ 映射到代码的web目录
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downFileName);
        //获取相应的输出流
        OutputStream outputStream = resp.getOutputStream();
        //读取流中全部的数据复制给输出流，输出给客户端
        IOUtils.copy(resourceAsStream,outputStream);

    }
}
