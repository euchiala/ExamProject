<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Authorization preview</title>
        <link href='<c:url value="/assets/css/style.css"></c:url>' rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
        <script>
            function generatePDF() {
                var doc = new jsPDF();
                var elementHTML = $('.main.container').html();
                var specialElementHandlers = {
                    '#sidebar': function(element, renderer) {
                    return true;
                    }
                };
                doc.fromHTML(elementHTML, 15, 15, {
                    'width': 170,
                    'elementHandlers': specialElementHandlers
                });
                doc.save('authorization.pdf');
            }
            function submitForm() {
                // Submit the form to authorizationController's doPost method
                var form = document.createElement("form");
                form.setAttribute("method", "POST");
                form.setAttribute("action", "authorizationController");

                // Add form data as hidden inputs
                var idInput = document.createElement("input");
                idInput.setAttribute("type", "hidden");
                idInput.setAttribute("name", "teacher_id");
                idInput.setAttribute("value", "${authorization.teacher.id}");
                form.appendChild(idInput);

                var authDateInput = document.createElement("input");
                authDateInput.setAttribute("type", "hidden");
                authDateInput.setAttribute("name", "authorization_date");
                authDateInput.setAttribute("value", "${authorization.authorization_date}");
                form.appendChild(authDateInput);

                var authHoursInput = document.createElement("input");
                authHoursInput.setAttribute("type", "hidden");
                authHoursInput.setAttribute("name", "authorized_hours");
                authHoursInput.setAttribute("value", "${authorization.authorized_hours}");
                form.appendChild(authHoursInput);

                // Submit the form
                document.body.appendChild(form);
                form.submit();
            }
        </script>
    </head>
    <body>
        <jsp:include page="sidebar.jsp" />
        <div class="main container">
            <h1>Authorization</h1>
            <input type="hidden" name="id" value="${authorization.teacher.id}">
            <div class="row">
                <h1>First Name: </h1>
                <h2>${authorization.teacher.first_name}</h2>
            </div>
            <div class="row">
                <h1>Last Name:</h1>
                <h2>${authorization.teacher.last_name}</h2>
            </div>
            <div class="row">
                <h1>Email:</h1>
                <h2>${authorization.teacher.email}</h2>
            </div>
            <div class="row">
                <h1>Institution:</h1>
                <h2>${authorization.teacher.institution}</h2>
            </div>
            <div class="row">
                <h1>Phone:</h1>
                <h2>${authorization.teacher.phone}</h2>
            </div>
            <div class="row">
                <h1>authorization date:</h1>
                <fmt:formatDate value="${authorization.authorization_date}" pattern="yyyy-MM-dd" var="formattedDate" />
                <h2>${formattedDate}</h2>            
            </div>
            <div class="row">
                <h1>authorization hours:</h1>
                <h2>${authorization.authorized_hours}</h2>
            </div>
            <input type="button" value="Print" class="btn btn-default" onclick="generatePDF(); submitForm();">
        </div>
    </body>
</html>