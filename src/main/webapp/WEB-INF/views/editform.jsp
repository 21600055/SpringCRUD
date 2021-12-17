<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.mycom.myapp.board.BoardDAO, com.mycom.myapp.board.BoardVO,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시물 수정</h1>
	<form:form commandName="boardVO" method="POST" action="../editok">
		<form:hidden path="seq" />
		<table id="edit">
			<tr>
				<td>Title:</td>
				<td><form:input path="title" /></td>
			</tr>
			<tr>
				<td>Writer:</td>
				<td><form:input path="writer" /></td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><form:input path="category" /></td>
			</tr>
			<tr>
				<td>Content:</td>
				<td><form:textarea cols="50" rows="5" path="content" /></td>
			</tr>
		</table>
		<button type="button" onclick="location.href='../list'">목록보기</button>
		<button type="submit">등록하기</button>
	</form:form>

</body>
</html>