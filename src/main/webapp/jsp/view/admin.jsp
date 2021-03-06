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
    <title>libraryapp</title>
    <style>
        <%@include file="../../vendor/bootstrap/css/bootstrap.min.css"%>
        <%@include file="../../css/grayscale3.css"%>
        <%@include file="../../vendor/fontawesome-free/css/all.min.css"%>
        <%@include file="../../css/userpage.css"%>
        <%@include file="../../css/grayscale.min.css"%>
        <%@include file="../../css/button.css"%>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

<section id="about" class="registration-section text-center"
         style="height: 1000px; background-color: rgba(22, 22, 22, 1);">
    <div class="masthead3">
        <div class="container-fluid  align-items-center">
            <div class="row justify-content-center ">
                <div class="jumbotron" style="margin-top: 0px;">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <div class="container">
                            <form class="form-inline" name="Simple"
                                  action="${pageContext.request.contextPath}/controller" method="GET">
                                <input type="hidden" name="command" value="registration_page"/>
                                <button type="submit" class="btn btn-outline-secondary" style="height: 50px;">
                                    <fmt:message
                                            key="submit.new.librarian"/>
                                </button>
                            </form>
                        </div>
                    </nav>
                    <div class="form-group row">
                        <div class="form-group row">
                            <form action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" value="find_user"/>
                                <div class="col-sm-8">
                                    <input type="text" name="userSearch" class="float-sm-left"
                                           style="width: 220px; margin-left: 15px; margin-top: 20px;"
                                           pattern="^[\w.]{0,20}$"
                                           maxlength="20"
                                           title="<fmt:message key="invalid.search"/>"
                                    />
                                </div>
                            </form>
                        </div>
                        <div style="color: red; margin-left: 10px; margin-top: 20px;">
                            ${notFoundUsers}
                        </div>
                    </div>
                    <div class="d-flex justify-content-around"><h2><fmt:message key="text.site.users"/></h2></div>
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">
                                <div class="d-flex justify-content-around"><fmt:message key="label.login"/></div>
                            </th>
                            <th scope="col">
                                <div class="d-flex justify-content-around"><fmt:message key="label.name"/></div>
                            </th>
                            <th scope="col">
                                <div class="d-flex justify-content-around"><fmt:message key="label.surname"/></div>
                            </th>
                            <th scope="col">
                                <div class="d-flex justify-content-around"><fmt:message key="label.email"/></div>
                            </th>
                            <th scope="col">
                                <div class="d-flex justify-content-around"><fmt:message
                                        key="label.userRoleType"/></div>
                            </th>
                            <th scope="col">
                                <div class="d-flex justify-content-around"><fmt:message key="label.status"/></div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${allUsers}" var="user">
                            <tr class="table-success">
                                <td>
                                    <div class="d-flex justify-content-around"><h4><span
                                            class="badge badge-outline-primary"><c:out
                                            value="${user.login}"/></span></h4></div>
                                </td>
                                <td>
                                    <div class="d-flex justify-content-around"><h4><span
                                            class="badge badge-outline-primary"> <c:out
                                            value="${user.name}"/></span></h4></div>
                                </td>
                                <td>
                                    <div class="d-flex justify-content-around"><h4><span
                                            class="badge badge-outline-primary"><c:out
                                            value="${user.surname}"/> </span></h4></div>
                                </td>
                                <td>
                                    <div class="d-flex justify-content-around"><h4><span
                                            class="badge badge-outline-primary"><c:out
                                            value="${user.email}"/> </span></h4></div>
                                </td>
                                <td>
                                    <div class="d-flex justify-content-around"><h4><span
                                            class="badge badge-outline-primary">
                                        <fmt:message key="${user.userRole.localeName}"/>
                                        </span></h4></div>
                                </td>
                                <td>
                                    <div class="d-flex justify-content-around"><h4><span
                                            class="badge badge-outline-primary">
                                        <fmt:message key="${user.userStatus.localeName}"/>
                                        </span></h4></div>
                                </td>
                                <c:choose>
                                    <c:when test="${user.userStatus == 'ACTIVE'}">
                                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                                            <input type="hidden" name="command" value="change_user_status"/>
                                            <input type="hidden" name="login" value="${user.login}"/>
                                            <input type="hidden" name="status" value="${user.userStatus}">
                                            <th scope="row">
                                                <div class="d-flex justify-content-around">
                                                    <input style="background-color: red; color: white; line-height: 5px;"
                                                           class="btn btn-outline-success my-2 my-sm-0"
                                                           type="submit"
                                                           name="id" value="<fmt:message key="label.block"/>"/>
                                                </div>
                                            </th>
                                        </form>
                                    </c:when>
                                    <c:when test="${user.userStatus == 'BLOCKED'}">
                                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                                            <input type="hidden" name="command" value="change_user_status"/>
                                            <input type="hidden" name="login" value="${user.login}"/>
                                            <input type="hidden" name="status" value="${user.userStatus}">
                                            <th scope="row">
                                                <div class="d-flex justify-content-around">
                                                    <input style="background-color: green; color: white; line-height: 5px;"
                                                           class="btn btn-outline-success my-2 my-sm-0"
                                                           type="submit"
                                                           name="id" value="<fmt:message key="label.unlock"/>"/>
                                                </div>
                                            </th>
                                        </form>
                                    </c:when>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div style="margin-left: 450px; margin-right: 450px;">
                        <ul class="pagination">
                            <c:if test="${countPage > 1}">
                                <c:if test="${currentPage != 1}">
                                    <li class="page-item">
                                        <form class="form-inline" method="POST"
                                              action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" value="admin_page"/>
                                            <input type="hidden" name="currentPage" value="${currentPage-1}"/>
                                            <div>
                                                <button type="submit"
                                                        class="btn btn-primary js-scroll-trigger">
                                                    <
                                                </button>
                                            </div>
                                        </form>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${countPage}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active">
                                                <a class="page-link">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <form class="form-inline" method="POST"
                                                      action="${pageContext.request.contextPath}/controller">
                                                    <input type="hidden" name="command" value="admin_page"/>
                                                    <input type="hidden" name="currentPage" value="${i}"/>
                                                    <div>
                                                        <button type="submit"
                                                                class="btn btn-primary js-scroll-trigger">
                                                                ${i}
                                                        </button>
                                                    </div>
                                                </form>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <c:if test="${currentPage lt countPage}">
                                    <li class="page-item">
                                        <form class="form-inline" method="POST"
                                              action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" value="admin_page"/>
                                            <input type="hidden" name="currentPage" value="${currentPage+1}"/>
                                            <div>
                                                <button type="submit"
                                                        class="btn btn-primary js-scroll-trigger">
                                                    >
                                                </button>
                                            </div>
                                        </form>
                                    </li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
            <br>
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
