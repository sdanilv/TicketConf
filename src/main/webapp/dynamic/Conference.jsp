<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${conference.name}</title>
    <link rel = stylesheet href=/static/AdaptivBlock.css >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul id="groupList" class="nav navbar-nav">
                <li>
                    <a href="/"><img height="50" width="55" src="<c:url value="/static/logo.png"/>"/></a>
                </li>
                <li>
                    <button type="button" id="add_contact" class="btn btn-default navbar-btn">
                        Мои билеты
                    </button>
                </li>
                <li>
                    <button type="button" id="add_group" class="btn btn-default navbar-btn">Найти мероприятия</button>
                </li>
                <li>
                    <button type="button" id="delete_contact" class="btn btn-default navbar-btn">Создать мероприятие
                    </button>
                </li>
                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"--%>
                       <%--aria-expanded="false">Groups <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="/">Default</a></li>--%>
                        <%--<c:forEach items="${groups}" var="group">--%>
                            <%--<li><a href="/group/${group.id}">${group.name}</a></li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="pattern" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="wrapper">

    <img src="${conference.image}" >
    <div class="panel panel-default">
        <div class="panel-body">
            <h1>Описание:</h1>
        </div>
        <div class="panel-footer">
            <br> ${conference.description}
        </div>
    </div>





</div>
</body>
</html>
