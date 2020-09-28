<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<%--<%@ taglib prefix="ctg" uri="customtags"%>--%>
<fmt:setBundle basename="prop.pagecontent"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>libraryapp</title>
    <style>
        <%@include file="../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../css/grayscale.min.css"%>
        <%@include file="../vendor/fontawesome-free/css/all.min.css"%>
        html {
            overflow-x: hidden;
        }
    </style>

    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <link href="css/grayscale.min.css" rel="stylesheet">
</head>
<body id="page-top">

<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="locale"/>
            <button type="submit" class="btn btn-outline-secondary">
                <label>Locale</label>
            </button>
        </form>

        <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
            <c:if test="${login != null}">

                <%--<input type="hidden" name="command" value=""/>
                <button type="submit" class="btn btn-outline-secondary">
                    <label>Account</label>
                </button>--%>
                <c:choose>

                    <c:when test="${userRole == 'ADMIN'}">
                        <input type="hidden" name="command" value="admin_page"/>
                        <button type="submit" class="btn btn-outline-secondary">
                            <label>Account</label>
                        </button>
                    </c:when>

                    <c:when test="${userRole == 'LIBRARIAN'}">
                        <input type="hidden" name="command" value="librarian_page"/>
                        <button type="submit" class="btn btn-outline-secondary">
                            <label>Account</label>
                        </button>
                    </c:when>

                    <c:when test="${userRole == 'READER'}">
                        <input type="hidden" name="command" value="reader_page"/>
                        <button type="submit" class="btn btn-outline-secondary">
                            <label>Account</label>
                        </button>
                    </c:when>

                </c:choose>


            </c:if>
            <c:if test="${login == null}">
                <input type="hidden" name="command" value="login_page"/>
                <button type="submit" class="btn btn-outline-secondary">
                    <label>Log In</label>
                </button>
            </c:if>
        </form>

        <%--<div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="#about">ABOUT</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link js-scroll-trigger" href="#contact">CONTACT</a>
                </li>
            </ul>
        </div>--%>
    </div>
</nav>

<header class="masthead">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center ">
            <h1 class="mx-auto my-0 text-uppercase"><fmt:message key="text.site.name"/></h1>
            <h2 class="text-white-50 mx-auto mt-2 mb-5"><fmt:message key="text.site.description"/></h2>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="to_library"/>
                <div>
                    <button class="btn btn-primary js-scroll-trigger" style="width: 80%; background-color: darkred">
                        <fmt:message key="submit.library"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</header>

<section id="about" class="about-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2 class="text-white mb-4"><fmt:message key="text.site.about"/></h2>
                <p class="text-white-50"><fmt:message key="text.site.about.description"/></p>
            </div>
        </div>
        <img src="img/PH4.png" class="img-fluid" alt="">
    </div>
</section>

<section id="projects" class="projects-section bg-light">
    <div class="container">

        <div class="row justify-content-center no-gutters mb-5 mb-lg-0">
            <div class="col-lg-6">
                <img class="img-fluid" src="img/mainPagePh3.png" alt="">
            </div>
            <div class="col-lg-6">
                <div class="bg-black text-center h-100 project">
                    <div class="d-flex h-100">
                        <div class="project-text w-100 my-auto text-center text-lg-left">
                            <h4 class="text-white"><fmt:message key="text.site.why"/></h4>
                            <p class="mb-0 text-white-50"><fmt:message key="text.site.why.description"/></p>
                            <hr class="d-none d-lg-block mb-0 ml-0">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row justify-content-center no-gutters">
            <div class="col-lg-6">
                <img class="img-fluid" src="img/mainPagePh2.png" alt="">
            </div>
            <div class="col-lg-6 order-lg-first">
                <div class="bg-black text-center h-100 project">
                    <div class="d-flex h-100">
                        <div class="project-text w-100 my-auto text-center text-lg-right">
                            <h4 class="text-white"><fmt:message key="text.site.why.second"/></h4>
                            <p class="mb-0 text-white-50"><fmt:message key="text.site.why.second.description"/></p>
                            <hr class="d-none d-lg-block mb-0 mr-0">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%-- <ctg:end-page/> --%>

<script type="text/javascript">
    <%@include file="../vendor/jquery/jquery.min.js"%>
    <%@include file="../vendor/bootstrap/js/bootstrap.bundle.min.js"%>
    <%@include file="../vendor/jquery-easing/jquery.easing.min.js"%>
    <%@include file="../js/grayscale.min.js"%>
</script>
</body>
</html>
