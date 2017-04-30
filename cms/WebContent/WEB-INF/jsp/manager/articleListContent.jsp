<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="tab1">
	<tbody>
		<tr>
			<th class="tl pl5">---</th>
			<th class="tl pl5">标题</th>
			<th class="tl pl5">所属栏目</th>
			<th class="tl pl5">作者</th>
			<th class="tl pl5">发布日期</th>
			<th class="tl pl5">点击次数</th>
			<th class="tl pl5">操作</th>
		</tr>
		<c:forEach items="${articles }" var="a">
		<tr>
			<td><input type="checkbox" value="${a.id}"></td>
			<td class="pl5">${a.title}</td>
			<td class="pl5">${a.category.name}</td>
			<td class="pl5">${a.author}</td>
			<td class="pl5">${a.publisurDate}</td>
			<td class="pl5">${a.clickTimes}</td>
			<!-- 拓展 js -->
			<td class="pl5"><span class="ico_edit" val="${a.id }"></span> <span
				class="ico_del ml5" val="${a.id }" id="one"></span></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
