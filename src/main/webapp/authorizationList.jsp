<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='<c:url value="/assets/css/style.css"></c:url>' rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta charset="ISO-8859-1">
        <title>Authorization List</title>
    </head>
    <body>
        <div class="layout">
            <jsp:include page="sidebar.jsp" />
            <div class="main">
                <div class="row">
                    <h1>Authorizations</h1>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Authorization Date</th>
                            <th>Authorized Hours</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="authorization" items="${authorizations}">
                            <tr>
                                <td>${authorization.teacher.first_name}
                                    ${authorization.teacher.last_name}</td>
                                <td>${authorization.authorization_date}</td>
                                <td>${authorization.authorized_hours}</td>

                                <td>
                                    <a href="authorizationListController?id=${authorization.id}"><i
                                            class='fas fa-file-pdf'
                                            style='font-size:25px'></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>