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
        <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="library_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.library"/>
                </button>
            </div>
        </form>
        <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="user_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.account"/>
                </button>
            </div>
        </form>
    </div>
</nav>

<section id="about" class="registration-section text-center">
    <div class="masthead3">
        <div class="container-fluid  align-items-center">
            <div class="row justify-content-center ">
                <form method="get" action="${pageContext.request.contextPath}/controller">
                    <div class="jumbotron" style="margin-top: 0px;">
                        <div class="d-flex justify-content-around"><h2>Orders</h2></div>
                        <c:choose>
                            <c:when test="${orderType == 'user'}">
                                <table class="table table-bordered table-hover">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">
                                            <div class="d-flex justify-content-around"><fmt:message
                                                    key="submit.order.book.title"/></div>
                                        </th>
                                        <th scope="col">
                                            <div class="d-flex justify-content-around"><fmt:message
                                                    key="submit.order.date"/></div>
                                        </th>
                                        <th scope="col">
                                            <div class="d-flex justify-content-around"><fmt:message
                                                    key="submit.order.type"/></div>
                                        </th>
                                        <th scope="col">
                                            <div class="d-flex justify-content-around"><fmt:message
                                                    key="submit.order.status"/></div>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${allOrders}" var="orderItem">
                                        <tr class="table-success">
                                            <td>
                                                <div class="d-flex justify-content-around"><h4><span
                                                        class="badge badge-outline-primary"><c:out
                                                        value="${orderItem.book.title}"/></span></h4></div>
                                            </td>
                                            <td>
                                                <div class="d-flex justify-content-around"><h4><span
                                                        class="badge badge-outline-primary"> <c:out
                                                        value="${orderItem.orderDate}"/></span></h4></div>
                                            </td>
                                            <td>
                                                <div class="d-flex justify-content-around"><h4><span
                                                        class="badge badge-outline-primary"><c:out
                                                        value="${orderItem.orderType}"/> </span></h4></div>
                                            </td>
                                            <td>
                                                <div class="d-flex justify-content-around"><h4><span
                                                        class="badge badge-outline-primary"><c:out
                                                        value="${orderItem.orderStatus}"/> </span></h4></div>
                                            </td>
                                            <c:choose>
                                                <c:when test="${orderItem.orderStatus == 'NEW'}">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="post">
                                                        <input type="hidden" name="command" value="cancel_order"/>
                                                        <input type="hidden" name="orderId"
                                                               value="${orderItem.orderId}"/>
                                                        <input type="hidden" name="orderType" value="user"/>
                                                        <th scope="row">
                                                            <div class="d-flex justify-content-around">
                                                                <input style="background-color: red; color: white; line-height: 5px;"
                                                                       class="btn btn-outline-success my-2 my-sm-0"
                                                                       type="submit"
                                                                       name="id"
                                                                       value="<fmt:message key="submit.order.cancel"/>"/>
                                                            </div>
                                                        </th>
                                                    </form>
                                                </c:when>
                                                <c:when test="${orderItem.orderStatus == 'APPROVED'}">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="post">
                                                        <input type="hidden" name="command" value="return_order"/>
                                                        <input type="hidden" name="orderId"
                                                               value="${orderItem.orderId}"/>
                                                        <input type="hidden" name="orderType" value="user"/>
                                                        <th scope="row">
                                                            <div class="d-flex justify-content-around">
                                                                <input style="background-color: green; color: white; line-height: 5px;"
                                                                       class="btn btn-outline-success my-2 my-sm-0"
                                                                       type="submit"
                                                                       name="id"
                                                                       value="<fmt:message key="submit.order.return"/>"/>
                                                            </div>
                                                        </th>
                                                    </form>
                                                </c:when>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:when test="${orderType == '---'}">

                            </c:when>
                        </c:choose>
                    </div>
                </form>
            </div>
            <br>
        </div>
    </div>
</section>


<%--<ctg:end-page/>--%>

<script type="text/javascript">
    <%@include file="../../js/reloadProtection.js"%>
    <%@include file="../../js/xxsProtection.js"%>
</script>
</body>
</html>
