<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="ctg" uri="customtags" %>--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>libraryapp</title>
    <style>
        <%@include file="../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../css/grayscale2.css"%>
        <%@include file="../vendor/fontawesome-free/css/all.min.css"%>
    </style>

    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <link href="../css/grayscale2.css" rel="stylesheet">
</head>
<body id="page-top">


<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="home_page"/>
            <button type="submit" class="btn btn-primary js-scroll-trigger">
                <fmt:message key="submit.home"/>
            </button>
            </button>
        </form>
    </div>
</nav>

<header class="masthead2">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center "><h1 class="mx-auto my-0 text-uppercase"><fmt:message
                key="text.blocked.h3"/></h1>
            <h2 class="text-white-50 mx-auto mt-2 mb-5"><fmt:message key="text.blocked.h4"/></h2>
            <div>
                <div class="bd-example">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"
                    <%--data-whatever="@fat"--%>><fmt:message key="submit.mess.to.admin"/></button>
                    <div class="modal fade" id="exampleModal" tabindex="-1" <%--userRoleType="dialog"--%>
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <form class="form" action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="send_message_admin"/>
                            <div class="modal-dialog" <%--userRoleType="document"--%>>
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <div class="form-group"><label for="theme"
                                                                       class="form-control-label"><fmt:message
                                                key="text.blocked.theme"/></label> <input type="text"
                                                                                          class="form-control"
                                                                                          id="theme" name="theme"></div>
                                        <div class="form-group"><label for="message"
                                                                       class="form-control-label"><fmt:message
                                                key="text.blocked.message"/> </label> <textarea class="form-control"
                                                                                                id="message"
                                                                                                name="message"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            <fmt:message key="submit.close"/></button>
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="submit.send"/></button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<%--<ctg:end-page/>--%>

<script type="text/javascript">
    <%@include file="../vendor/jquery/jquery.min.js" %>
    <%@include file="../vendor/bootstrap/js/bootstrap.bundle.min.js" %>
    <%@include file="../vendor/jquery-easing/jquery.easing.min.js" %>
    <%@include file="../js/grayscale.min.js" %>
    <%@include file="../js/modal.js" %>
</script>
</body>
</html>