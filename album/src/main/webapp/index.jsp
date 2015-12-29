<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>夏忆年华</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>

<body>
  <div class="container text-center">
    <h1>欢迎登陆网络相册！</h1>
    <form action="UserServlet?op=login" method="post" class="form-inline">
     <label for="userName">用户名:</label> <input type="text" id="userName" name="userName"  placeholder="Enter Username" class="form-control "/> <br><br>
     <label for="userPswd">密码:</label> <input type="password" id="userPswd" name="userPswd" placeholder="Enter Password" class="form-control"/> <br><br>
     <input type="submit" value="登录" class="btn btn-default"/>
     <input type="button" value="注册" onclick="location.href='register.jsp' " class="btn btn-default"/>
   </form>
 </div>
</body>
</html>
