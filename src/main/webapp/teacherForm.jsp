<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Form</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.btn-default{
	float:right;
}
</style>
</head>
<body>
<jsp:include page="sidebar.jsp" />
<div class="main container">
<c:choose>
  <c:when test="${param.id != null}">
    <h1>Edit Teacher</h1>
  </c:when>
  <c:otherwise>
    <h1>Add Teacher</h1>
  </c:otherwise>
</c:choose>
    <form action="teacherFormController" method="post">
        <input type="hidden" name="id" value="${teacher.id}">
         <div class="form-group">
	      <label>First Name:</label>
	      <input type="text" class="form-control" placeholder="Enter first name" name="first_name" value="${teacher.first_name}">
	    </div>
	    <div class="form-group">
	      <label>Last Name:</label>
	      <input type="text" class="form-control" placeholder="Enter last name" name="last_name" value="${teacher.last_name}">
	    </div>
	    <div class="form-group">
	      <label>Email:</label>
	      <input type="email" class="form-control" placeholder="Enter email" name="email" value="${teacher.email}">
	    </div>
	    <div class="form-group">
	      <label>Institution:</label>
	      <input type="text" class="form-control" placeholder="Enter institution" name="institution" value="${teacher.institution}">
	    </div>
	    <div class="form-group">
	      <label>Phone:</label>
	      <input type="text" class="form-control" placeholder="Enter phone" name="phone" value="${teacher.phone}">
	    </div>
        <input type="submit" value="Save" class="btn btn-default">
    </form>
</div>
</body>
</html>