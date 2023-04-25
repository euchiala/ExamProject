<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <link href='<c:url value="/assets/css/style.css"></c:url>' rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Teacher List</title>
</head>
<body>
<div class="layout">
<jsp:include page="sidebar.jsp" />
<div class="main">
    <div class="add-btn">
        <a href="teacherForm.jsp" class="btn btn-default">Add</a> <br>
	</div>
    <div class="">
        <form action="import" method="post" enctype="multipart/form-data">
             <div class="form-group">
                 <input type="submit" value="Import" class="btn btn-default">
                 <input type="file" name="file" class="btn btn-default">
             </div>    
         </form>
    </div>
    <div class="row">
	    
        <h1>Teachers</h1>
    </div>
    <table class="table table-striped">
    <thead>	
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Institution</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>
     </thead>
      <tbody>
        <c:forEach var="teacher" items="${teachers}">
            <tr>
                <td>${teacher.first_name} ${teacher.last_name}</td>
                <td>${teacher.email}</td>
                <td>${teacher.institution}</td>
                <td>${teacher.phone}</td>

                <td>
                	<a href="authorizationController?id=${teacher.id}"><i class='fas fa-file-pdf' style='font-size:25px'></i></a>
                    <a href="teacherFormController?id=${teacher.id}"><i class="fa fa-edit" style="font-size:25px"></i></a>
                    <a href="?id=${teacher.id}"><i class="fa fa-trash" style="font-size:25px"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</div>
</body>
</html>