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
        <form class="form-inline"
              method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="home_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.home"/>
                </button>
            </div>
        </form>

        <c:choose>
            <c:when test="${userRole == null}">
                <form class="form-inline"
                      method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="login_page"/>
                    <div>
                        <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                            <fmt:message key="submit.logIn"/>
                        </button>
                    </div>
                </form>
            </c:when>

            <c:when test="${userRole == 'ADMIN'}">
                <form class="form-inline"
                      method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="admin_page"/>
                    <div>
                        <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                            <fmt:message key="submit.users"/>
                        </button>
                    </div>
                </form>
            </c:when>
        </c:choose>
    </div>
</nav>


<section id="about" class="registration-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <div class="form-group row">
                        <input type="hidden" name="command" value="registration"/>
                        <label class="col-sm-4 col-form-label float-sm-right" for="login" style="color: white">
                            <fmt:message key="label.login"/>
                        </label>
                        <div class="col-sm-8 ">
                            <input type="text" name="login" id="login" class="float-sm-left" style="width: 220px"
                                   pattern="[A-Za-zА-Яа-я\d\-\_]{0,45}"
                                   maxlength="20"
                                   title="<fmt:message key="invalid.login"/>"
                                   required
                                   value="${registrationParameters.get("login")}"
                            />
                            <%--<c:if test="${loginError}">
                                <label style="color: #9fcdff"><fmt:message key="invalid.registration.login"/></label>
                            </c:if>--%>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="password" style="color: white">
                            <fmt:message key="label.password"/>
                        </label>
                        <div class="col-sm-8">
                            <input type="password" name="password" id="password" class="float-sm-left"
                                   style="width: 220px"
                                   pattern="(?=.*[a-z])(?=.*\d)([A-Za-z\d]{8,20})"
                                   maxlength="20"
                                   title="<fmt:message key="invalid.registration.password"/>"
                                   required
                                   value="${registrationParameters.get("password")}"
                            />
                            <%--<c:if test="${passwordError}">
                                <label style="color: #9fcdff"><fmt:message key="invalid.registration.password"/></label>
                            </c:if>--%>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="email" style="color: white">
                            <fmt:message key="label.email"/>
                        </label>
                        <div class="col-sm-8">
                            <input type="text" name="email" id="email" class="float-sm-left" style="width: 220px"
                                   pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}"
                                   maxlength="40"
                                   title="<fmt:message key="invalid.registration.email"/>"
                                   required
                                   value="${registrationParameters.get("email")}"
                            />
                            <%--<c:if test="${emailError}">
                                <label style="color: #9fcdff"><fmt:message key="invalid.registration.email"/></label>
                            </c:if>--%>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="name" style="color: white">
                            <fmt:message key="label.name"/>
                        </label>
                        <div class="col-sm-8">
                            <input type="text" name="name" id="name" class="float-sm-left" style="width: 220px"
                                   pattern="^([А-Я]{1}[а-яё]{1,20}|[A-Z]{1}[a-z]{3,20})$"
                                   maxlength="45"
                                   title="<fmt:message key="invalid.name"/>"
                                   required
                                   value="${registrationParameters.get("name")}"
                            />
                            <%--<c:if test="${nameError}">
                                <label style="color: #9fcdff"><fmt:message key="invalid.name"/></label>
                            </c:if>--%>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="surname" style="color: white">
                            <fmt:message key="label.surname"/>
                        </label>
                        <div class="col-sm-8">
                            <input type="text" name="surname" id="surname" class="float-sm-left" style="width: 220px"
                                   pattern="^([А-Я]{1}[а-яё]{1,20}|[A-Z]{1}[a-z]{3,20})$"
                                   maxlength="45"
                                   title="<fmt:message key="invalid.name"/>"
                                   required
                                   value="${registrationParameters.get("surname")}"
                            />
                            <%--<c:if test="${surnameError}">
                                <label style="color: #9fcdff"><fmt:message key="label.invalid.surname"/></label>
                            </c:if>--%>
                        </div>

                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                                <fmt:message key="submit.registration"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <br>
        </div>
    </div>
</section>

<%--<ctg:end-page/>--%>

</body>
</html>