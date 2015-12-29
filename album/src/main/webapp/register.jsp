<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>注册</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="js/bootstrap.js"></script>
  <script src="js/jQuery.js"></script>
</head>
<body>
  <div class="container">
    <h1>注册</h1>
    <form action="UserServlet?op=register" method="post" id="register" class="form-inline">
      <fieldset>
        <legend>用户名与密码:</legend>
        <label for="userName">用户名:</label> <input type="text" id="userName"
        name="userName" class="form-control "/> <label for="userPswd">密码:</label> <input
        type="password" id="userPswd" name="userPswd" class="form-control"/>
        <br><br><br>
      </fieldset>
      <fieldset>
        <legend>性别:</legend>
        <label class="radio-inline"><input type="radio" value="男" id="boy"  name="sex">男</label>
        <label class="radio-inline"><input type="radio" value="女" id="girl" name="sex" >女</label>
        <label class="radio-inline"><input type="radio" value="保密" id="secrecy" name="sex">保密</label>
        <br><br><br>
      </fieldset>
      <fieldset>
        <legend>我最喜爱的:</legend>
        <label class="checkbox-inline"><input type="checkbox" value="计算机" id="computer" name="hobby">计算机</label>
        <label class="checkbox-inline"><input type="checkbox" value="旅游" id="trval" name="hobby" >旅游</label>
        <label class="checkbox-inline"><input type="checkbox" value="购物" id="buy" name="hobby">购物</label>
        <br><br><br>
      </fieldset>
      <fieldset>
        <legend>提交:</legend>
        <input type="submit" value="注册" id="submit" name="submit" class="btn btn-default"/> <input
        type="reset" value="重置" id="reset" name="reset"
        onclick="javascript:history.back(-1)'" class="btn btn-default"/>
      </fieldset>
    </form>
  </div>
</body>
</html>
