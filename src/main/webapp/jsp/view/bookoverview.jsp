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
        <%@include file="../../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../../css/grayscale3.css"%>
        <%@include file="../../css/userpage.css"%>
        <%@include file="../../css/grayscale.min.css"%>
        <%@include file="../../vendor/fontawesome-free/css/all.min.css"%>
        <%@include file="../../css/button.css"%>
    </style>
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">%--%>
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
</head>

<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="home_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.home"/>
                </button>
            </div>
        </form>

        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="library_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.library"/>
                </button>
            </div>
        </form>

        <c:if test="${login == null}">
            <input type="hidden" name="command" value="login_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.logIn"/>
                </button>
            </div>
        </c:if>
        <c:if test="${login != null}">
            <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="logout"/>
                <div>
                    <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                        <fmt:message key="label.logout"/>
                    </button>
                </div>
            </form>
        </c:if>
    </div>
</nav>


<section id="about" class="about-section text-center">
    <div class="masthead3">
        <div class="container  align-items-center">
            <div class="row justify-content-center no-gutters">
                <c:if test="${ not empty typeOverview and typeOverview eq 'seeOverview' }">
                <div style="margin-top: 100px">
                    <h1 style="color: #9fcdff">${book.title}</h1>
                </div>
                <table width="100%" cellspacing="0" cellpadding="5">
                    <tr>
                        <td width="300" valign="top">
                            <div class="col-lg-8">
                                    <%--<img class="img-fluid" src="${pageContext.request.contextPath}/${userImage}"--%>
                                <img class="img-fluid" src="${pageContext.request.contextPath}/img/book.jpg"
                                     style="margin-top: 60px; border-radius: 30px" width="300" height="300"/>
                            </div>
                        </td>
                        <td valign="top">
                            <table style="width: 100%; margin-top: 60px; font-size: 15pt; color: white;">
                                <tr>
                                    <td style="width: 30%"><fmt:message key="table.name.library.category"/>:</td>
                                    <td>${book.category}</td>
                                </tr>
                                <tr>
                                    <td style="width: 30%"><fmt:message key="table.name.library.author"/>:</td>
                                    <td>${book.author}</td>
                                </tr>
                                <tr>
                                    <td style="width: 30%"><fmt:message key="table.name.library.pages"/>:</td>
                                    <td>${book.page}</td>
                                </tr>
                                <tr>
                                    <td style="width: 30%"><fmt:message key="table.name.library.year"/>:</td>
                                    <td>${book.year}</td>
                                </tr>
                                <tr>
                                    <td style="width: 30%"><fmt:message key="table.name.library.number.copies"/>:</td>
                                    <td>${book.numberCopy}</td>
                                </tr>
                                <tr>
                                    <td style="width: 30%"><fmt:message key="table.name.library.description"/>:</td>
                                    <td>${book.description}</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <div class="text-right">
                    <form class="form-inline" method="POST"
                          action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="reading_room_order"/>
                        <div>
                            <button type="submit"
                                    class="btn btn-primary js-scroll-trigger custom-button"
                                    style="width: 300px !important;">
                                <fmt:message key="submit.order.reading.room"/>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="text-right" style="margin-left: 100px;">
                    <form class="form-inline" method="POST"
                          action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="home_order"/>
                        <div>
                            <button type="submit"
                                    class="btn btn-primary js-scroll-trigger custom-button"
                                    style="width: 300px !important;">
                                <fmt:message key="submit.order.home"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </c:if>
    </div>
    </div>
    </div>
    </div>
</section>

<%--<ctg:end-page/>--%>

<script type="text/javascript">
    <%@include file="../../js/xxsProtection.js"%>
</script>
</body>
</html>