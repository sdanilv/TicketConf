<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>TicketConf</title>
    <link rel = stylesheet href=/static/AdaptivBlock.css >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">


    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <a href="/"><img height="80" width="85" src="<c:url value="/static/logo.png"/>"/></a>
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
                    <%--</li>--%>
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

        <c:forEach items="${conferences}" var="conference">
            <div class="masonry">
                <div class="item">
                    <h3> ${conference.name}</h3>
                    <a href="/conf?id=${conference.getId()}">
                        <img src="${conference.image}" ></a>
                    <br/>${conference.getShotDescription()}
                    <br/>Стоимость: ${conference.price} грн
                </div>
            </div>
        </c:forEach>

    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${pages}">
                <li><a href="/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
            </c:forEach>
        </ul>
    </nav>
</div>

<script>
    $('.dropdown-toggle').dropdown();
    $('#add_contact').click(function () {
        window.location.href = '/contact_add_page';
    });
    $('#add_group').click(function () {
        window.location.href = '/group_add_page';
    });
    $('#delete_contact').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/conference/delete", data, function (data, status) {
            window.location.reload();
        });
    });
</script>
</body>
</html>