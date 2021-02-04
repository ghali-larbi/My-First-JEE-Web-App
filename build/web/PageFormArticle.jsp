<%-- 
    Document   : PageFormArticle
    Created on : 2 févr. 2021, 11:36:26
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
    </head>
    <body>
      
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${article != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${article == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${article != null}">
            			Edit Article
            		</c:if>
						<c:if test="${article == null}">
            			Add New Article
            		</c:if>
					</h2>
				</caption>

				<c:if test="${article != null}">
					<input type="hidden" name="id" value="<c:out value='${article.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>article Name</label> <input type="text"
						value="<c:out value='${article.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
    </body>
</html>
