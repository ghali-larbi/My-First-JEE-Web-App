<%@page import="model.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>article</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
    body{
        overflow-x: hidden;
    }
</style>
</head>
<body>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center mt-5">List of article</h3>
			
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New article</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
                                                <th>action</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
                                     
                                    <c:forEach var="article" items="${listArticle}">

						<tr>
							<td><c:out value="${article.id}" /></td>
							<td><c:out value="${article.name}" /></td>
							
							<td><a class="btn btn-md btn-warning" href="edit?id=<c:out value='${article.id}' />">Edit</a>
                                                            &nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-md btn-danger"
								href="delete?id=<c:out value='${article.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
                              
					
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>

