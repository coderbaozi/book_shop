package com.bookshop.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class fileupload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //客户端以流的形式发送，必须用流的形式接收。
//        System.out.println(req.getParameter("username"));
//        System.out.println(req.getParameter("photo"));
        //得到一个流，通过这个流去读取
        //通过apach封装的jar包去解析
        //ServletFileUpload类用于解析上传的数据
        //FileItem类，表示每一个表单项
        //ServletFileUpload.isMultipartContent(HttpServletRequest req)
        //   用于判断上传的 数据格式是否是多段的数据格式---一个input就是一段
        //praseRequest（HttpServletRequest req）解析上传的数据
        //FileItem.isFormField（）判断当前表单项是否是普通表单项，还是上传的文件类型。
        //ture 普通类型的表单项-----false表示上传的文件类型。
        //FileItem.getFieldName（）获取表单项的name属性值
        //FileItem.getSting（）表示获取当前表单项的值。
        //FileItem.getName（）获取上传的文件名
        //FileItem.write（file）讲上传的文件写道参数file所指向的磁盘位置。
        //1.先判断上传数据是不是多段（只有是多段的数据才是文件上传）
        if(ServletFileUpload.isMultipartContent(req)){
            //创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            //创建用于解析上床数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);
            //解析上传数据，得到每一个表单项FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //判断每一个表单项是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    if(fileItem.isFormField()){
                        //普通表单项
                        System.out.println("表单项的name属性值："+fileItem.getFieldName());
                        //参数UTF-8解决乱码问题
                        System.out.println("表单项的value属性值："+fileItem.getString("UTF-8"));
                    }else{
                        //上传的文件
                        System.out.println("表单项的name属性值："+fileItem.getFieldName());
                        //上传的文件名
                        System.out.println("上传的文件名"+fileItem.getName());
                        fileItem.write(new File("D:\\"+fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

        }
    }
}
