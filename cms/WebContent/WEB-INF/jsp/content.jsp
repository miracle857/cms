<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=path%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息管理系统</title>
<link rel="Shortcut Icon" href="theme/1/images/home.png" />
<link rel="stylesheet" type="text/css" href="theme/1/css/common.css">
<link rel="stylesheet" type="text/css"
	href="theme/1/css/front/style.css">
</head>

<body>
	<div id="main">

		<div class="top">
			<div class="topbg">
				<div class="logo_sign">
					<div class="logo"></div>
					<div class="sign">
						<span>欢迎您</span> <span class="sp_home"> <a
							href="index.html">首页</a>
						</span> <span class="sp_backstage"> <a
							href="manager/toHome.action">后台管理</a>
						</span>
						<!-- <span class="sp_signout">退出</span> -->
					</div>
				</div>
				<div class="nav">
					<ul class="navUI">
						<li><a href="toIndex.action">首页</a></li>
						<c:forEach items="${categories }" var="c">
							<li><a href="jsp\list.html?id=${c.id}">${c.name}</a></li>
						</c:forEach>

					</ul>
				</div>
				<div class="banner">
					<div class="banimg">
						<img width="1072" height="241"
							src="theme/1/images/front/banner.png">
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="congw">
				<div class="conarticle">
					<div class="position">
						<span class="poshome">您现在的位置：</span> <a href="index.html">首页</a>
						>> <a href="list.html?id=1"> ${article.category.name}</a>
					</div>
					<div class="title">${article.title}</div>
					<div class="abstract">
						<span>更新时间：${article.publisurDate}</span> <span>发布人：${article.author}</span> <span>点击${article.clickTimes}次</span>
					</div>
					<div class="details">
						<center>
							<p>${article.content}</p>
							
						</center>
					</div>
				</div>
				<div class="link">
					<div class="linelink">
						<span>友情链接：</span><a href="javascript:void(0)">上海教育网</a><a
							href="javascript:void(0)">上海教育网</a><a href="javascript:void(0)">上海教育网</a><a
							href="javascript:void(0)">上海教育网</a><a href="javascript:void(0)">上海教育网</a><a
							href="javascript:void(0)">上海教育网</a>
					</div>
				</div>
			</div>
		</div>

		<div class="footer">
			<div class="foot">
				<p>
					杰普信息发布系统 技术支持：<a href="javascript:void(0)">上海杰普软件科技有限公司</a>
					电话：021-xxxxxxx
				</p>
			</div>
		</div>
	</div>
</body>
</html>