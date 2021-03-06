<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251"%>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>New Conference</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/conference/add" method="post">
                        <h3>New conference</h3>
                        <select class="selectpicker form-control form-group" name="group">
                            <option value="-1">Default</option>
                            <c:forEach items="${groups}" var="group">
                                <option value="${group.id}">${group.name}</option>
                            </c:forEach>
                        </select>

                        <input class="form-control form-group" type="text" name="name" placeholder="Name" value="Name">
                        <input class="form-control form-group" type="text" name="description" placeholder="Description" value="Description">
                        <input class="form-control form-group" type="text" name="price" placeholder="Price" value="2">
                        <input class="form-control form-group" type="text" name="date" placeholder="Date" value="01.01.2000">
                        <input class="form-control form-group" type="text" name="email" placeholder="Email" value="Email">

                  <%--<form enctype="multipart/form-data" method="POST">--%>
                        Image <input type="file" name="photo">
                    <%--</form>--%>
                    <input type="submit" class="btn btn-primary" value="Add">
            </form>
        </div>

        <script>
            $('.selectpicker').selectpicker();
        </script>
    </body>
</html>