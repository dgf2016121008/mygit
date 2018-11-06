<%@page import="entity.user"%>
<%@page import="entity.Blogtext" %>
<%@page import="java.util.List"%>
<%@page import="Dao.DBUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="gb2312">
<title>个人博客</title>
<meta name="keywords" content="博客" />
<meta name="description" content="个人博客" />
<link href="css/base.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/media.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
</head>
<body>
<div class="ibody">
  <header>
    <h1>如影随形</h1>
    <h2>影子是一个会撒谎的精灵，它在虚空中流浪和等待被发现之间;在存在与不存在之间....</h2>
    <div class="logo"><a href="/"></a></div>
    <nav id="topnav"><a href="blog.jsp?uId=<%=(String)request.getParameter("uId")%>">首页</a><a href="me.jsp?uId=<%=(String)request.getParameter("uId")%>">我的博客</a><a href="login.jsp">登录</a><a href="register.jsp">注册</a></nav>
  </header>
  <article>
    <h2 class="about_h">您现在的位置是：<a href="/">首页</a>><a href="1/">我的博客</a></h2>
    <div class="bloglist">
     <%
							request.setCharacterEncoding("UTF-8");
							response.setCharacterEncoding("UTF-8");
							response.setContentType("text/html;charset = UTF-8");
							
							String uId = (String)request.getParameter("uId");
							System.out.println("uId"+uId);
							List<user> userlist = DBUtils.doQO(request.getParameter("uId"));
							
						
						
						for(int i= 0; i<userlist.size();i++)
						{
							user user = userlist.get(i);
								%>
      <div class="newblog">
        <ul>
          <h3><%=user.getName() %></h3>
          <div class="autor"><span>文章号：<%=user.getBid() %></span><span>分类<%=user.getType() %></span><span>修改（<a href="uedit.jsp">30</a>）</span><span>删除（<a href="delete.do">30</a>）</span><span>作者：<%=user.getuId() %></div>
          <p><%=user.getContent() %></p>
        </ul>
      </div>
    <% } %>
   
    </div>
    <div class="page"><a title="Total record"><b>113</b></a><b>1</b><a href="/">2</a><a href="/">3</a><a href="/">4</a><a href="/">5</a><a href="/">&gt;</a><a href="/">&gt;&gt;</a></div>
  </article>
  <aside>
    <div class="ph_news">    
      <h2>
        <p>作者介绍</p>
      </h2>
      <%user user = DBUtils.doQP(uId);
							if(user!=null)
							{
						%>
      <ul>
        <li><%=user.getuId() %></li>
        <li><%=user.getUsex() %></li>
        <li><%=user.getUname() %></li>
        <li><%=user.getUaddr() %></li>
        <li><%=user.getEmail() %></li>
      <%} %>      
      </ul>
   
    </div>
    <div class="rnav">
      <li class="rnav1 "><a href="edit.jsp">撰写博文</a></li>

    </div>
    <tr><tr><tr>
    <div class="ph_news">
          
      <h2>
        <p>作者文章</p>
      </h2>
      <ul>
      <%	for(int i= 0; i<userlist.size();i++)
						{
							user user1 = userlist.get(i);
								%>
        <li><%=user1.getName() %></li>
        <% } %>
      </ul>
      
    </div>
   
  </aside>
  <script src="js/silder.js"></script>
  <div class="clear"></div>
  <!-- 清除浮动 --> 
</div>
</body>
</html>
