<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="assets/css/style.css" rel="stylesheet" type="text/css">
<style>
body {

}

.sidenav {
  height: 100%;
  width: 160px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  padding-top: 20px;
}

.sidenav a {
  padding: 2px;
  text-decoration: none;
  font-size: 20px;
  color: #818181;
  display: block;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.main {
  margin-left: 160px; /* Same as the width of the sidenav /
  font-size: 28px; / Increased text to enable scrolling */
  padding: 0px 10px;
}

.sidenav .menu{
	padding-left:10px;
}

.sidenav .sub-menu{
	padding-left:15px;
}
.sidenav .sub-menu a{
font-size: 17px;
}
.sidenav li{
list-style: none;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>
<title>Insert title here</title>
</head>
<body>
<div class="sidenav">
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