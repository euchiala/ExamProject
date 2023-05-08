<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
				<script>
				function generatePDF(callback) {
				    var DATA = document.getElementById('htmlData');

				    html2canvas(DATA).then(canvas => {
				        var imgData = canvas.toDataURL('image/png');
				        var doc = new jsPDF('p', 'mm', 'a4');
				        var width = doc.internal.pageSize.getWidth();
				        var height = (canvas.height * width) / canvas.width;

				        doc.addImage(imgData, 'PNG', 0, 0, width, height);

				        var firstName = "${authorization.teacher.first_name}";
				        var lastName = "${authorization.teacher.last_name}";
				        var currentDate = new Date();
				        var day = ("0" + currentDate.getDate()).slice(-2);
				        var month = ("0" + (currentDate.getMonth() + 1)).slice(-2);
				        var year = currentDate.getFullYear();
				        var fileName = "Authorization_" + firstName + "_" + lastName + "_" + day + month + year + ".pdf";
				        if(window.location.href.includes("authorizationListController")){
				        	doc.save(fileName);
				        	window.location.replace("http://localhost:8080/ExamProject/");
				        }else {
				        	doc.save(fileName);
					        callback();

				        }
				    });
				}

				function submitForm() {
				    // Define the callback function to be executed after the PDF has been generated
				    var callback = function() {
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
				    };

				    // Call the generatePDF function and pass in the callback function
				    generatePDF(callback);
				}


				</script>
			</head>

			<body>
				<jsp:include page="sidebar.jsp" />
				<div class="main container" id="htmlData">
					<div class="section-global">
						<div class="pdf-header">
							<div class="col-left">
								<img src='<c:url value="/assets/img/iit.png"></c:url>' class='logo' />
							</div>
							<div class="col-middle">
								<h1>Authorization</h1>
							</div>
							<div class="col-right">
								<img src='<c:url value="/assets/img/slogan.jpg"></c:url>' class='logo' />
							</div>
						</div>
						<div class="pdf-body">
							<input type="hidden" name="id" value="${authorization.teacher.id}">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>1.Teacher informations</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="body-col">
												<h1>First Name: </h1>
												<h2>${authorization.teacher.first_name}</h2>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="body-col">
												<h1>Last Name:</h1>
												<h2>${authorization.teacher.last_name}</h2>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="body-col">
												<h1>Email:</h1>
												<h2>${authorization.teacher.email}</h2>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="body-col">
												<h1>Institution:</h1>
												<h2>${authorization.teacher.institution}</h2>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="body-col">
												<h1>Phone:</h1>
												<h2>${authorization.teacher.phone}</h2>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>2.Authorization informations</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="body-col">
												<h1>authorization date:</h1>
												<fmt:formatDate value="${authorization.authorization_date}"
													pattern="yyyy-MM-dd" var="formattedDate" />
												<h2>${formattedDate}</h2>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="body-col">
												<h1>authorization hours:</h1>
												<h2>${authorization.authorized_hours}</h2>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="pdf-footer">
							<h3>Signature et cachet</h3>
							<div class="signature">
							</div>
						</div>
					</div>
				</div>
				<div class="button-submit main">
					<input type="button" value="" class="btn btn-default" onclick="generatePDF();submitForm();">
				</div>
			</body>

			</html>