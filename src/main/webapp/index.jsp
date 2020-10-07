<%--
  Created by IntelliJ IDEA.
  User: fanchaojian
  Date: 2020-9-21
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h2>this is my first rest project</h2>

    <hr />
    <h2>文件上传</h2>

    <form method="post" action="fileUpload/blog" enctype="multipart/form-data">
        上传文件：
        <input type="file" name="upload" /> <br />
        <input type="submit" value="上传">
    </form>

</head>
<body>

</body>
</html>
