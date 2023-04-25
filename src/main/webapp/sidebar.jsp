<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href='<c:url value="/assets/css/style.css"></c:url>' rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" type="text/css">

<title>Insert title here</title>
</head>
<body>
<div class="sidenav">
<img src='<c:url value="/assets/img/iit.png"></c:url>' class='logo' /> 
<ul class="menu">
<li><a href="">Teacher</a>
<ul class="sub-menu">
<li><a href="teacherForm.jsp">Add</a></li>
<li><a href="/ExamProject/">List</a></li>
</ul>
</li>
<li><a href="#">Authorization</a></li>
</ul>
</div>
</body>
</html>