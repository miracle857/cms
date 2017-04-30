<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="top">
	<div class="topbg">
		<div class="logo_sign">
			<div class="logo"></div>
			<div class="sign">
				<span>欢迎您</span> <span class="sp_home"> <a
					href="index">首页</a>
				</span> <span class="sp_backstage"> <a href="homeaction">后台管理</a>
				</span>
				<!-- <span class="sp_signout">退出</span> -->
			</div>
		</div>
		<div class="nav">
			<ul class="navUI">
				<li><a href="index">首页</a></li>
				<c:forEach items="${categories }" var="c">
					<li><a href="list.html?id=${c.id}">${c.name}</a></li>
				</c:forEach>

			</ul>
		</div>
		<div class="banner">
			<div class="banimg">
				<img width="1072" height="241" src="theme/1/images/front/banner.png">
			</div>
		</div>
	</div>
</div>