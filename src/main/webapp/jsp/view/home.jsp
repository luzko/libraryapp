<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<%--<%@ taglib prefix="ctg" uri="customtags"%>--%>
<fmt:setBundle basename="prop.pagecontent"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>libraryapp</title>
    <style>
        <%@include file="../../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../../css/grayscale.min.css"%>
        <%@include file="../../vendor/fontawesome-free/css/all.min.css"%>
        <%@include file="../../css/button.css"%>
        html {
            overflow-x: hidden;
        }
    </style>

    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
</head>
<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">

        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="library_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.library"/>
                </button>
            </div>
        </form>

        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="locale"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.locale"/>
                </button>
            </div>
        </form>

        <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
            <c:if test="${login != null}">
                <c:choose>
                    <c:when test="${userRole == 'ADMIN'}">
                        <input type="hidden" name="command" value="admin_page"/>
                        <div>
                            <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                                <fmt:message key="submit.account"/>
                            </button>
                        </div>
                    </c:when>

                    <c:when test="${userRole == 'LIBRARIAN' or userRole == 'READER'}">
                        <input type="hidden" name="command" value="user_page"/>
                        <div>
                            <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                                <fmt:message key="submit.account"/>
                            </button>
                        </div>
                    </c:when>
                </c:choose>
            </c:if>
            <c:if test="${login == null}">
                <input type="hidden" name="command" value="login_page"/>
                <div>
                    <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                        <fmt:message key="submit.logIn"/>
                    </button>
                </div>
            </c:if>
        </form>
    </div>
</nav>

<header class="masthead">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center ">
            <h1 class="mx-auto my-0 text-uppercase"><fmt:message key="text.site.name"/></h1>
        </div>
    </div>
</header>

<section id="about" class="about-section text-center">
    <div class="container">
        <div class="row">
            <div class="container">
                <div class="row justify-content-center no-gutters mb-5 mb-lg-0">
                    <div class="col-lg-6">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/img/mainPagePh3.png" alt="">
                    </div>
                    <div class="col-lg-6">
                        <div class="bg-black text-center h-100 project">
                            <div class="d-flex h-100">
                                <div class="project-text w-100 my-auto text-center text-lg-left">
                                    <h4 class="text-white" style="margin-left: 10px"><fmt:message
                                            key="text.site.why"/></h4>
                                    <p class="mb-0 text-white-50" style="margin-left: 10px"><fmt:message
                                            key="text.site.why.description"/></p>
                                    <hr class="d-none d-lg-block mb-0 ml-0">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row justify-content-center no-gutters">
                    <div class="col-lg-6">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/img/mainPagePh2.png" alt="">
                    </div>
                    <div class="col-lg-6 order-lg-first">
                        <div class="bg-black text-center h-100 project">
                            <div class="d-flex h-100">
                                <div class="project-text w-100 my-auto text-center text-lg-right">
                                    <h4 class="text-white" style="margin-right: 10px"><fmt:message
                                            key="text.site.why.second"/></h4>
                                    <p class="mb-0 text-white-50" style="margin-right: 10px"><fmt:message
                                            key="text.site.why.second.description"/></p>
                                    <hr class="d-none d-lg-block mb-0 mr-0">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%-- <ctg:end-page/> --%>

<script type="text/javascript">
    <%@include file="../../js/reloadProtection.js"%>
    <%@include file="../../js/xxsProtection.js"%>
</script>
</body>
</html>
