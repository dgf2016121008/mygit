<%@page import="entity.user"%>
<%@page import="java.util.List"%>
<%@page import="Dao.DBUtils"%>
<%@page import="entity.Blogtext" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="gb2312">
<title>博客主页</title>
<meta name="keywords" content="博客" />
<meta name="description" content="博客" />
<link href="css/base.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
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
    <nav id="topnav"><a href="blog.jsp?uId=<%=(String)request.getParameter("uId")%>">主页</a><a href="me.jsp?uId=<%=(String)request.getParameter("uId")%>">我的博客</a><a href="login.jsp">登录</a><a href="register.jsp">注册</a></nav>
  </header>
  <article>
    <div class="banner">
      <ul class="texts">
        <p>The best life is use of willing attitude, a happy-go-lucky life. </p>
        <p>最好的生活是用心甘情愿的态度，过随遇而安的生活。</p>
      </ul>
    </div>
    <div class="bloglist">
      <h2>
        <p><span>推荐</span>文章</p>
      </h2>
      <%
							request.setCharacterEncoding("UTF-8");
							response.setCharacterEncoding("UTF-8");
							response.setContentType("text/html;charset = UTF-8");
							String uId = (String)request.getParameter("uId");
							List<Blogtext> list = DBUtils.doBQuery();
							for(int i=0;i<list.size();i++)
							{
								%>
      
      <div class="blogs">
                <% Blogtext c = list.get(i); %>
        <h3><%=c.getName() %></h3>
        
        <ul>
          <p><%=c.getContent() %></p>
        </ul>
        <p class="autor"><span><%=c.getuId() %></span><span><%=c.getType() %></span></p>
      </div>
      <%  } %>
    </div>
  </article>
  <aside>
    
    <div class="topspaceinfo">
      <h1>ִ执子之手，与子偕老</h1>
      <p>于千万人之中，我遇见了我所遇见的人....</p>
    </div>
    
    <div class="tj_news">
      <h2>
        <p class="tj_t1">推荐文章</p>
      </h2>
      <% for(int i=0;i<list.size();i++)
		{ %>
      <ul>
         <% Blogtext b = list.get(i); %>
        <li><%=b.getName() %></li>
        <%} %>
      </ul>
      <h2>
        <p class="tj_t2">最新文章</p>
      </h2>
     <% for(int i=0;i<list.size();i++)
		{ %>
      <ul>
         <% Blogtext a = list.get(i); %>
        <li><%=a.getName() %></li>
        <%} %>
      </ul>
    </div>
   
   
  </aside>
  <script src="js/silder.js"></script>
  <div class="clear"></div>
  <!-- 清除浮动 --> 
</div>
<div style="text-align:center;">

</div>
</body>
</html>
