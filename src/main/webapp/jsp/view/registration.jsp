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
                      method="GET" action="${pageContext.request.contextPath}/controller">
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
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <div class="form-group row">
                        <input type="hidden" name="command" value="registration"/>
                        <label class="col-sm-4 col-form-label float-sm-right" for="login" style="color: white">
                            <fmt:message key="label.login"/><span class="filed-required">*</span>
                        </label>
                        <div class="col-sm-8 ">
                            <input type="text" name="login" id="login" class="float-sm-left" style="width: 220px"
                                   pattern="^[\w.]{5,20}$"
                                   maxlength="20"
                                   title="<fmt:message key="invalid.login"/>"
                                   required
                                   value="${registrationParameters.get("login")}"
                            />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="password" style="color: white">
                            <fmt:message key="label.password"/><span class="filed-required">*</span>
                        </label>
                        <div class="col-sm-8">
                            <input type="password" name="password" id="password" class="float-sm-left"
                                   style="width: 220px"
                                   pattern="^(?=.*[\p{Lower}])(?=.*[\p{Upper}])(?=.*\d)[\w.]{8,20}$"
                                   maxlength="20"
                                   title="<fmt:message key="invalid.registration.password"/>"
                                   required
                                   value="${registrationParameters.get("password")}"
                            />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="email" style="color: white">
                            <fmt:message key="label.email"/><span class="filed-required">*</span>
                        </label>
                        <div class="col-sm-8">
                            <input type="text" name="email" id="email" class="float-sm-left" style="width: 220px"
                                   pattern="^[\w.+-]{3,30}@[\w.-]{2,15}\.[\p{Lower}]{2,4}$"
                                   maxlength="50"
                                   title="<fmt:message key="invalid.registration.email"/>"
                                   required
                                   value="${registrationParameters.get("email")}"
                            />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="name" style="color: white">
                            <fmt:message key="label.name"/><span class="filed-required">*</span>
                        </label>
                        <div class="col-sm-8">
                            <input type="text" name="name" id="name" class="float-sm-left" style="width: 220px"
                                   pattern="^[\p{L}]{3,25}$"
                                   maxlength="25"
                                   title="<fmt:message key="invalid.name"/>"
                                   required
                                   value="${registrationParameters.get("name")}"
                            />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label float-sm-right" for="surname" style="color: white">
                            <fmt:message key="label.surname"/><span class="filed-required">*</span>
                        </label>
                        <div class="col-sm-8">
                            <input type="text" name="surname" id="surname" class="float-sm-left" style="width: 220px"
                                   pattern="^[\p{L}]{3,25}$"
                                   maxlength="25"
                                   title="<fmt:message key="invalid.name"/>"
                                   required
                                   value="${registrationParameters.get("surname")}"
                            />
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <label style="color: red;">${errorDataMessage}</label>
                    <br/>
                    <br/>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                                <fmt:message key="submit.registration"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <br/>
        </div>
    </div>
</section>
<c-tag:footer/>
<script type="text/javascript">
    <%@include file="../../js/reloadProtection.js"%>
    <%@include file="../../js/xxsProtection.js"%>
</script>
</body>
</html>
