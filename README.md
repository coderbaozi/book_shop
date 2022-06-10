# 	JavaWeb学习历程

​	从5.8安装Tomacat，到今天6.10号，JavaWeb阶段的学习也打上了一个句号。大概也1个月的时间。本次学习项目架构是Servlet+jsp+mysql来构建整个项目。虽然jsp已经是过时的技术，但是这次学习的编程思想和思路仿佛让我打开了新世界的大门，对代码有了更加深刻的意识。

​		书城项目的大半个开源地址：[残血书城](https://github.com/coderbaozi/book_shop)

## 反射层面

​		使用反射，每次访问Servlet程序来执行，只需要抽象一个BaseServlet类通过反射来执行操作，例如：如果我们要执行有关用户的操作，可以通过抽象为一个UserServlet类，这样来不需要我们频繁的去配置Servlet程序，只需要前端加上一个访问Servlet的参数来定位访问某一个Servlet，也方便我们管理维护代码。与一个操作一个Servlet相比，代码的逻辑更加清晰。

### JavaBean层

​		JavaBean层，也涉及反射的操作，当我们每次通过BaseDAO层进行查询时，我们可能会得到一行，一列，一个结果，我们都知道得到这些对应的Class类是不同的，所以我们使用Apach 开发的DBUtils  jar包时，需要使用泛型来通过底层的反射机制来保存我们查询到的数据到javaBean层。这里的底层原理我还没了解的很深刻，只是了解了一种[土办法](https://www.bilibili.com/video/BV1zv41157NC?p=28&vd_source=5154031eeb7fc624925507fa2709b5db)来封装JavaBean的方法。



```java
package com.bookshop.dao;

import com.bookshop.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//添加泛型可以操作domain
public class BasicDAO<T> {
    private QueryRunner qr=new QueryRunner();

    //开发通用的dml方法
    public int update(String sql,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        int affectedRows = qr.update(connection, sql, args);
        JdbcUtils.close(null,connection,null);
        return affectedRows;
    }

    //开发查询多行
    public List<T> queryMulity(String sql,Class<T> clazz,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        List<T> list = qr.query(connection, sql, new BeanListHandler<>(clazz), args);
        JdbcUtils.close(null,connection,null);
        return list;
    }
    //查询单行
    public T querySingle(String sql,Class<T> clazz,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        T rs = qr.query(connection, sql, new BeanHandler<>(clazz), args);
        JdbcUtils.close(null,connection,null);
        return rs;
    }
    //查询单个对象
    public Object queryScalar(String sql,Object...args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        Object rs = qr.query(connection, sql, new ScalarHandler<>(), args);
        JdbcUtils.close(null,connection,null);
        return rs;
    }
}

```

## 代码逻辑层

​		项目的三层架构，让我对设计模式和项目架构的力量有了懵懂的理解，这次学习，我动手敲了大半个书城项目(后面的ThreadLocal，AJAX请求，Json，Fliter过滤器（做前台，后台的实现）没在书城项目上应用实践)，书城项目分为三层架构，前端：jsp+Jquery来实现一些简单的操作。后端：web层（负责Servlet程序），service层（被Servlet调用来实现一些逻辑，service层来调用DAO层），Dao层（操作数据库中的表）。这样的设计架构，代码的思路清晰明了。后期维护也非常方便。

开始时接触后端这样的三层架构，感觉没有service层存在的必要，直接用DAO来实现功能多方便，但后来仔细思索，我发现可以在Service层实现特有的业务（仿佛）。

奈何本人资历尚浅，尚不明白这种架构的意义在何处。

​		为什么要用一个接口，难道是将来到公司，这些小业务分发给具体的员工，让员工来实现这些接口的实现。

![image-20220610223338867](https://raw.githubusercontent.com/coderbaozi/Images/master/image-20220610223338867.png)

## 依赖

​		开始时导入依赖，也就是相应的jar包，需要我们自己去找资源下载相应的jar包然后导入到项目的WEB-INF目录下建一个lib的包来存放jar包，jar包就像是别人造好的轮子，我们只需要加上我们的业务逻辑，就能达到我们想要的效果。我们时常会因为找jar包浪费我们太多的时间，于是，阴差阳错间，我发现了一个项目管理工具Maven 让依赖的导入从此顺畅了起来。另外Maven的配置，有一个小细节，就是目录，第一次由于我配置的目录多了一层发现怎么导入依赖都报红，而且导入依赖是不需要魔法的，直接在[Maven-repository](https://mvnrepository.com/) 中搜索相应的依赖，直接粘贴到<dependiencies></dependendiencies>标签内，然后点击一个 M 的小按钮即可导入。另外，idea是不支持新版本的Maven的，最好不用用Idea提供的Maven。（如果你能完美运行，当我没说）。

![image-20220610222842065](https://raw.githubusercontent.com/coderbaozi/Images/master/image-20220610222842065.png)

## 测试

![image-20220610223741644](https://raw.githubusercontent.com/coderbaozi/Images/master/image-20220610223741644.png)

这张图中的testall是我们编写完DAO层程序和service层做测试用的，至少我们做一个简单的测试，可以测试我们的sql语句编写的对不对，有没有初步完成我们想要的效果，奈何本人懒，每次编写完不测试，直接开始了漫长的改bug之路，这样每次部署项目到Tomact的时间是很长的，往往没有@TEST测试框架来的舒服。所以测试也是一个不小的收获。

## 路径问题

​	开发中，这些应该是前端要考虑的问题，前端对于出现次数较高的元素，往往选择抽离出来，一方面简洁，减少代码冗余，另一方面可以方便我们的管理和维护。开始时，项目资源，配置的全部都是相对路径。相对路径牵一发则动全身，极力推荐绝对路径。

## 文件上传下载

​		下面是文件上传的东西，本人还没精通这一层面。

Servlet

```java
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
```

Html/jsp

```html
<%--
  Created by IntelliJ IDEA.
  User: QZY
  Date: 2022/5/28
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form enctype="multipart/form-data" action="http://localhost:8080/book_shop/fileupload" method="post">
    请输入用户名：<input type="text" name="username"><br>
    上传文件<input type="file" name="photo"><br>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>

```

Download

```java
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
```

## JSP/JQuery

​		JSP给我最深的就是EL表达式，和JSTL标签库，这两个库，让我们自由的在前端添加脚本代码。前后端数据交互。这种方式前端可以加入后端的代码，可能不利于维护。这可能就是这个技术被淘汰的原因吧。

​		Jquery框架，我对前端框架的理解可能这些框架所做的事情就是减少与Dom的交互。

## 分页

​		这里的分页，是后端处理的，通过数据库的分页查询，来达到分页的效果，前端每次给后端查询的页码，后端查询到的数据保存到List集合中。将list集合保存到request域中，后来加入功能，按照价格降序/升序查询，只需要在Sql中加入逻辑。区间查询...

​		分页，后端抽象了一个Page类，用来保存分页的数据，像每页展示多少个数据，当前页码，总记录数...后来把分页给抽象出来只需要改变相应的url就可以将分页部署带其他的功能中，不过后来听说，这些事情，前端有写好的模块，就不需要我们再来造轮子了。

## 购物车模块/生成订单模块

​		购物车模块，购物车模块选择了一种简单的方案，将购物车东西，和购物车中的商品放入Session域中。抽象了一个购物车类，和一个购物车中的商品类。

​		生成订单模块，也采用了订单对象和订单项目。并且数据库表的实际上会将订单号和用户Id联系起来，这时候有人会推荐使用外键约束来达到两个表的联系，这种方法是严禁使用的，因为两个表的联系本来就是物理层面的内容，只要人为保证数据的正确性即可。

## AJAX/Json

 		Ajax可以在JQuery种绑定事件，可以实现页面的局部刷新。

​		Json分为Json对象和Json字符串，前后端数据交互，要使用Json字符串。

## ThreadLocal

​		鄙人对JUC了解较浅，ThreadLocal可以绑定一个值到一个线程中，如果我们使用了连个表关联插入，如果因为某个原因我们一个表插入失败了，这是后我们要调用Connection.rollback（）来回滚。ThreadLocal可以防止，我们还没有回滚，它就把这个连接放回连接池，我们给ThreadLocal绑定一个conn，就可以保证，我们一系列操作是在一个线程中的。不会因为连接的放回 造成的回滚失败，或者别的程序拿到了这个连接。另外连接池技术中，一个程序只能得到一个连接。

Fliter

​		Fliter实现，一些过滤，比如说管理员和普通用户。进而限制普通用户访问后台。
