<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>用户注册</title>
<meta name="generator" content="Bootply" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<style type="text/css">
.modal-footer {
	border-top: 0px;
}
.div-b{filter:alpha(Opacity=70);-moz-opacity:0.7;opacity: 0.7} 
</style>
<script type="text/javascript">
	function Check(theform) {
		var m;
		
		if (Login.uId.value == "") {
			m = "请输入您的ID";
			alert(m);
		} else if (Login.upwd.value == "") {
			m = "请输入密码";
			alert(m);
		} 
		else {
			return true;
		}
		return false;
	}
</script>
</head>
<body background="images/timgg.jpg">
	<!--login modal-->
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog div-b">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h1 class="text-center">用户登录</h1>
      </div>
      <div class="modal-body">
          <form class="form col-md-12 center-block" action="me.jsp" method="post" name="Login" onSubmit="return Check(this)">
            <div class="form-group">
            <label>用户名</label>
              <input type="text" name="uId" class="form-control input-lg" placeholder="用户ID">
            </div>
            <div class="form-group">
            <label>密码</label>
              <input type="password" name="upwd" class="form-control input-lg" placeholder="密码">
            </div>
            <div class="checkbox">
        </div>
         <div class="btn-group pull-right">
          <button class="submit btn btn-success">登录</button>
           <button class="submit btn btn-danger"><a href="register.jsp">注册 </a></button>
         <br><br><br><br>
              <div class="btn-group center-block " style="width:200px">
   	         <a href="blog.jsp"> 返回首页 </a>
              </div>
              
		  </div>	
          </form>
      </div>
      <div class="modal-footer">
        
      </div>
  </div>
  </div>
</div>
	<!-- script references -->
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

</body>
</html>