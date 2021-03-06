<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<%@ taglib prefix="c-tag" uri="custom-tag" %>
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
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
</head>

<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">


        <c:if test="${not empty type and type eq 'see'}">
            <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="home_page"/>
                <div>
                    <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                        <fmt:message key="submit.home"/>
                    </button>
                </div>
            </form>
        </c:if>

        <c:if test="${not empty type and type eq 'change'}">
            <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="user_page"/>
                <div>
                    <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                        <fmt:message key="submit.account"/>
                    </button>
                </div>
            </form>
        </c:if>

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

<div class="modal fade" id="exampleModal" tabindex="-1"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <form class="form" action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="send_message_admin"/>
        <input type="hidden" name="typeMessage" value="active"/>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="form-group"><label for="subject"
                                                   class="form-control-label"><fmt:message
                            key="text.blocked.theme"/></label> <input type="text"
                                                                      class="form-control"
                                                                      id="subject" name="subject"
                                                                      pattern="^[\p{L}\d ?!,.']{5,35}$"
                                                                      maxlength="35">
                    </div>
                    <div class="form-group"><label for="message"
                                                   class="form-control-label"><fmt:message
                            key="text.blocked.message"/> </label> <textarea class="form-control"
                                                                            id="message"
                                                                            name="message"
                                                                            pattern="^[\p{L}\d ?!,.']{5,300}$"
                                                                            maxlength="300"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="submit.close"/></button>
                    <button type="submit" class="btn btn-primary" style="background-color: green">
                        <fmt:message
                                key="submit.send"/></button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="modal fade" id="modalPassword" tabindex="-1"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <form class="form" action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_password"/>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="form-group"><label for="old"
                                                   class="form-control-label"><fmt:message
                            key="password.old"/></label>
                        <input type="text"
                               class="form-control"
                               id="old"
                               name="oldPassword"
                               pattern="^(?=.*[\p{Lower}])(?=.*[\p{Upper}])(?=.*\d)[\w.]{8,20}$"
                               maxlength="20"
                               title="<fmt:message key="invalid.registration.password"/>"
                        >
                    </div>
                    <div class="form-group"><label for="new"
                                                   class="form-control-label"><fmt:message
                            key="password.new"/></label>
                        <input type="text"
                               class="form-control"
                               id="new"
                               name="newPassword"
                               pattern="^(?=.*[\p{Lower}])(?=.*[\p{Upper}])(?=.*\d)[\w.]{8,20}$"
                               maxlength="20"
                               title="<fmt:message key="invalid.registration.password"/>"
                        >
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="submit.close"/></button>
                    <button type="submit" class="btn btn-primary" style="background-color: green">
                        <fmt:message
                                key="submit.save"/></button>
                </div>
            </div>
        </div>
    </form>
</div>

<section id="about" class="about-section text-center">
    <div class="masthead3">
        <div class="container  align-items-center">
            <div class="row justify-content-center no-gutters">

                <c:if test="${ not empty type and type eq 'see' }">
                    <div style="margin-top: 100px">
                        <h1 style="color: #9fcdff">${userName} ${userSurname}</h1>
                    </div>

                    <div class="text_block">
                        <div class="col-lg-8">
                            <img class="img-fluid" src="/images/${avatar}"
                                 style="margin-top: 100px; border-radius: 30px" width="300" height="300"/>
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
                                    <c:choose>
                                        <c:when test="${userRole == 'READER'}">
                                            <div class="text-right">
                                                <form class="form-inline" method="POST"
                                                      action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" value="settings"/>
                                                    <div>
                                                        <button type="submit"
                                                                style="width: 250px !important;"
                                                                class="btn btn-primary js-scroll-trigger custom-button">
                                                            <fmt:message key="submit.settings.profile"/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="text-right">
                                                <form class="form-inline" method="POST"
                                                      action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" value="orders_page"/>
                                                    <input type="hidden" name="orderType" value="user"/>
                                                    <div>
                                                        <button type="submit"
                                                                style="width: 250px !important;"
                                                                class="btn btn-primary js-scroll-trigger custom-button">
                                                            <fmt:message key="submit.order.book"/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="text-right">
                                                <div>
                                                    <div class="bd-example">
                                                        <button type="submit"
                                                                class="btn btn-primary js-scroll-trigger custom-button"
                                                                style="width: 250px !important;"
                                                                data-toggle="modal" data-target="#exampleModal">
                                                            <fmt:message key="submit.mess.to.admin"/>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${userRole == 'LIBRARIAN'}">
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
                                                    <input type="hidden" name="command" value="orders_page"/>
                                                    <input type="hidden" name="orderType" value="new"/>
                                                    <div>
                                                        <button type="submit"
                                                                class="btn btn-primary js-scroll-trigger custom-button">
                                                            <fmt:message key="submit.order.new"/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="text-right">
                                                <form class="form-inline" method="POST"
                                                      action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" value="orders_page"/>
                                                    <input type="hidden" name="orderType" value="all"/>
                                                    <div>
                                                        <button type="submit"
                                                                class="btn btn-primary js-scroll-trigger custom-button">
                                                            <fmt:message key="submit.order.all"/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div style="color: red;">
                                        ${notFoundOrders}
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty type and type eq 'change'}">
                    <div style="margin-top: 100px">
                        <h1 style="color: #9fcdff"><fmt:message key="submit.settings.profile"/></h1>
                    </div>

                    <div class="text_block">

                        <div class="col-lg-8" style="margin-bottom: 100px;">
                            <div class="col-lg-8">
                                <img class="img-fluid" src="/images/${avatar}"
                                     style="margin-top: 50px; border-radius: 30px" width="300" height="300"/>
                            </div>

                            <form class="form-inline" action="${pageContext.request.contextPath}/images"
                                  enctype="multipart/form-data" method="POST">
                                <input type="hidden" name="command" value="change_profile_image"/>
                                <input type="file" name="image" accept="image/jpeg,image/png"
                                       class="btn btn-outline-secondary" required/>
                                <div>
                                    <button type="submit" class="btn btn-primary js-scroll-trigger custom-button"
                                            style="margin-top: 50px !important; margin-left: 80px;">
                                        <fmt:message key="submit.save"/>
                                    </button>
                                </div>
                            </form>

                            <div style="color: red;">
                                    ${errorAvatar}
                            </div>

                        </div>
                        <div class="col-lg-8">
                            <div class="main">
                                <br/>
                                <div style="color: green;">
                                        ${ChangedSave}
                                </div>
                                <br/>
                                <div>
                                    <div class="col-lg-2 float-right">
                                        <button type="submit"
                                                class="btn btn-primary js-scroll-trigger custom-button"
                                                style="margin-bottom: 15px;"
                                                data-toggle="modal" data-target="#modalPassword">
                                            <fmt:message key="submit.password"/>
                                        </button>
                                    </div>
                                </div>

                                <div style="color: red;">
                                        ${errorPassword}
                                </div>


                                <div class="field">
                                    <form class="form-inline" action="${pageContext.request.contextPath}/controller"
                                          method="POST" style="margin-top: 5px;">
                                        <br/>
                                        <input type="hidden" name="command" value="change_profile_login"/>

                                        <div class="col-lg-2 text-left">
                                            <label class="col-sm-4 col-form-label float-sm-right" for="login"
                                                   style="color: white">
                                                <fmt:message key="label.login"/>
                                            </label>
                                        </div>
                                        <div class="col-sm-8 text-left">

                                            <input type="text" name="login" id="login" placeholder="${login}"
                                                   class="float-sm-left" style="width: 180px; height: 30px;"
                                                   pattern="^[\w.]{5,20}$"
                                                   maxlength="20"
                                                   required
                                            />
                                        </div>
                                        <div class="col-lg-2 float-right">
                                            <button type="submit"
                                                    class="btn btn-primary js-scroll-trigger custom-button">
                                                <fmt:message key="submit.save"/>
                                            </button>
                                        </div>
                                        <br/>
                                        <div style="color: red;">
                                                ${loginError}
                                        </div>
                                        <br/>
                                    </form>
                                </div>
                                <div class="field">
                                    <form class="form-inline" action="${pageContext.request.contextPath}/controller"
                                          method="POST">
                                        <br/>
                                        <input type="hidden" name="command" value="change_profile_name"/>

                                        <div class="col-lg-2 text-left">
                                            <label class="col-sm-4 col-form-label float-sm-right" for="login"
                                                   style="color: white">
                                                <fmt:message key="label.name"/>
                                            </label>
                                        </div>
                                        <div class="col-sm-8 text-left">

                                            <input type="text" name="name" id="name" placeholder="${userName}"
                                                   class="float-sm-left" style="width: 180px; height: 30px;"
                                                   pattern="^[\p{L}]{3,25}$"
                                                   maxlength="20"
                                                   required
                                            />
                                        </div>

                                        <div class="col-lg-2 float-right">
                                            <button type="submit"
                                                    class="btn btn-primary js-scroll-trigger custom-button">
                                                <fmt:message key="submit.save"/>
                                            </button>
                                        </div>
                                        <br/>
                                        <div style="color: red;">
                                                ${usernameError}
                                        </div>
                                        <br/>
                                    </form>
                                </div>
                                <div class="field">
                                    <form class="form-inline" action="${pageContext.request.contextPath}/controller"
                                          method="POST">
                                        <br/>
                                        <input type="hidden" name="command" value="change_profile_surname"/>

                                        <div class="col-lg-2 text-left">
                                            <label class="col-sm-4 col-form-label float-sm-right" for="login"
                                                   style="color: white">
                                                <fmt:message key="label.surname"/>
                                            </label>
                                        </div>
                                        <div class="col-sm-8 text-left">

                                            <input type="text" name="surname" id="surname" placeholder="${userSurname}"
                                                   class="float-sm-left" style="width: 180px; height: 30px;"
                                                   pattern="^[\p{L}]{3,25}$"
                                                   maxlength="20"
                                                   required
                                            />
                                        </div>

                                        <div class="col-lg-2 float-right">
                                            <button type="submit"
                                                    class="btn btn-primary js-scroll-trigger custom-button">
                                                <fmt:message key="submit.save"/>
                                            </button>
                                        </div>
                                        <br/>
                                        <div style="color: red;">
                                                ${surnameError}
                                        </div>
                                        <br/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</section>
<c-tag:footer/>
<script type="text/javascript">
    <%@include file="../../js/reloadProtection.js"%>
    <%@include file="../../js/xxsProtection.js"%>
    <%@include file="../../vendor/jquery/jquery.min.js" %>
    <%@include file="../../vendor/bootstrap/js/bootstrap.bundle.min.js" %>
    <%@include file="../../vendor/jquery-easing/jquery.easing.min.js" %>
    <%@include file="../../js/grayscale.min.js" %>
    <%@include file="../../js/modal.js" %>
</script>
</body>
</html>
