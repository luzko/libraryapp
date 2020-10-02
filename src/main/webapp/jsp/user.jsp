<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="ctg" uri="customtags" %>--%>
<fmt:setLocale value="${locale}"/>
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
        <%@include file="../css/grayscale3.css"%>
        <%@include file="../css/userpage.css"%>
        <%@include file="../css/grayscale.min.css"%>
        <%@include file="../vendor/fontawesome-free/css/all.min.css"%>
        <%@include file="../css/button.css"%>
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

        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="logout"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="label.logout"/>
                </button>
            </div>
        </form>
    </div>
</nav>


<section id="about" class="about-section text-center">
    <div class="masthead3">
        <div class="container  align-items-center">
            <div class="row justify-content-center no-gutters">

                <%--<c:if test="${ not empty type and type eq 'see' }">--%>

                <div style="margin-top: 100px">
                    <h1 style="color: #9fcdff">${userName} ${userSurname}</h1>
                </div>

                <div class="text_block">
                    <div class="col-lg-4">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/${userImage}"
                             style="margin-top: 60px; border-radius: 30px" width="300" height="300"/>
                    </div>
                    <div class="col-lg-8 text-center">
                        <div class="row">
                            <div class="col-lg-6 text-right">
                                <h4 style="color: #fff">
                                    <br/><br/>
                                    <fmt:message key="label.login"/>:
                                    <br/>
                                    <fmt:message key="label.userRoleType"/>:
                                    <br/>
                                    <fmt:message key="label.email"/>
                                    <br/>
                                </h4>
                                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                            </div>
                            <div class="col-lg-6 text-left">
                                <h4 style="color: #fff">
                                    <br/><br/>
                                    ${login}
                                    <br/>
                                    ${userRole}
                                    <br/>
                                    ${email}
                                    <br/><br/><br/>
                                </h4>
                                <div class="text-right">
                                    <form class="form-inline" method="POST"
                                          action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" value="settings"/>
                                        <div>
                                            <button type="submit"
                                                    class="btn btn-primary js-scroll-trigger custom-button">
                                                <fmt:message key="submit.settings.profile"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <div class="text-right">
                                    <form class="form-inline" method="POST"
                                          action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" value=""/>
                                        <div>
                                            <button type="submit"
                                                    class="btn btn-primary js-scroll-trigger custom-button">
                                                <fmt:message key="submit.order.book"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--</c:if>--%>
                    <%--<c:if test="${not empty type and type eq 'change'}" >
                        <div class="col-lg-4" style="color: red">
                            <form class="form-inline" action="${pageContext.request.contextPath}/controller"  enctype="multipart/form-data" method="post">
                                <input type="hidden" name="command" value="change_profile_image"/>
                                <input type="file" name="image" accept="image/jpeg,image/png" class="btn btn-outline-secondary" required/>
                                <button class="btn btn-outline-success my-0" type="submit">
                                    <label>
                                        <шfmt:message key="submit.save"/>
                                    </label>
                                </button>
                            </form>
                        </div>
                        <div class="col-lg-8">
                            <div class="main">
                                <br/>
                                    ${ChangedSave}
                                <br/>
                                <div class="field">
                                    <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                                        <br/>
                                        <input type="hidden" name="command" value="change_profile_login"/>
                                        <div class="col-lg-2 text-left">
                                            <label for="login" style="color: #9fcdff;"><fmt:message key="label.login"/>:</label>
                                        </div>
                                        <div class="col-lg-8 text-left">
                                            <input type="text" name="login" id="login" placeholder="${login}"/>
                                        </div>
                                        <div class="col-lg-2 float-right">
                                            <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                                <label>
                                                    <fmt:message key="submit.save"/>
                                                </label>
                                            </button>
                                        </div>
                                        <br/>
                                            ${loginError}
                                        <br/>
                                    </form>
                                </div>
                                <div class="field">
                                    <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                                        <br/>
                                        <input type="hidden" name="command" value="change_profile_name"/>
                                        <div class="col-lg-2 text-left">
                                            <label style="color: #9fcdff;text-align: right"><fmt:message key="label.name"/>:</label>
                                        </div>
                                        <div class="col-lg-8 text-left">
                                            <input type="text" name="name" id="name" placeholder="${userName}"/>
                                        </div>
                                        <div class="col-lg-2 float-right">
                                            <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                                <label>
                                                    <fmt:message key="submit.save"/>
                                                </label>
                                            </button>
                                        </div>
                                        <br/>
                                            ${usernameError}
                                        <br/>
                                    </form>
                                </div>
                                <div class="field">
                                    <form  class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                                        <br/>
                                        <input type="hidden" name="command" value="change_profile_surname"/>
                                        <div class="col-lg-2 text-right">
                                            <label style="color: #9fcdff; text-align: right"><fmt:message key="label.surname"/>:</label>
                                        </div>
                                        <div class="col-lg-8 text-left">
                                            <input type="text" name="surname" id="surname" placeholder="${surname}"/>
                                        </div>
                                        <div class="col-lg-2 float-right">
                                            <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                                <label>
                                                    <fmt:message key="submit.save"/>
                                                </label>
                                            </button>
                                        </div>
                                        <br/>
                                            ${surnameError}
                                        <br/>
                                    </form>
                                </div>
                            </div>
                            <c:if test="${userBookId != null}">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <br/>
                                        <input type="hidden" name="command" value="delete_user_book"/>
                                        <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                            <label>
                                                <fmt:message key="delete"/> ${bookName}
                                            </label>
                                        </button>
                                        <br/>
                                    </form>
                                </div>
                            </c:if>
                            <c:if test="${userPlanId != null}">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <br/>
                                        <input type="hidden" name="command" value="delete_reading_plan"/>
                                        <input type="submit" style="color: #9fcdff"  value="<fmt:message key="delete"/> ${readingPlanName} ">
                                        <br/>
                                    </form>
                                </div>
                            </c:if>
                            <c:if test="${userBookStatus == null and userBookId != null}">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <br/>
                                        <input type="hidden" name="command" value="new_completed_book"/>
                                        <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                            <label>
                                                <fmt:message key="submit.book.status"/>
                                            </label>
                                        </button>
                                        <br/>
                                    </form>
                                </div>
                            </c:if>
                            <c:if test="${ not (readingPlanName eq '--')}">
                                <div class="row">
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <br/>
                                        <input type="hidden" name="command" value="delete_user_reading_plan"/>
                                        <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                            <label>
                                                <fmt:message key="delete"/> ${readingPlanName}
                                            </label>
                                        </button>
                                        <br/>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                    </c:if>--%>
                </div>

            </div>
        </div>
        <br/><br/><br/> <br/><br/><br/> <br/><br/><br/> <br/><br/><br/>
    </div>
</section>


<%--<ctg:end-page/>--%>

<script type="text/javascript">
    <%@include file="../vendor/jquery/jquery.min.js"%>
    <%@include file="../vendor/bootstrap/js/bootstrap.bundle.min.js"%>
    <%@include file="../vendor/jquery-easing/jquery.easing.min.js"%>
    <%@include file="../js/grayscale.min.js"%>
    <%@include file="../js/modal.js" %>
</script>

</body>
</html>
