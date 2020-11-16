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
        <form class="form-inline"
              method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="library_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.library"/>
                </button>
            </div>
        </form>
        <form class="form-inline"
              method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="admin_page"/>
            <div>
                <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                    <fmt:message key="submit.users"/>
                </button>
            </div>
        </form>
    </div>
</nav>

<section id="about" class="registration-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <form action="${pageContext.request.contextPath}/controller" method="POST">
                    <c:choose>
                        <c:when test="${createType == 'author'}">
                            <input type="hidden" name="createType" value="author">
                            <input type="hidden" name="command" value="create_author"/>
                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="authorName"
                                       style="color: white">
                                    <fmt:message key="label.name"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="authorName" id="authorName" class="float-sm-left"
                                           style="width: 220px"
                                           pattern="^[\p{L} ]{3,25}$"
                                           maxlength="25"
                                           title="<fmt:message key="invalid.name"/>"
                                           required
                                    />
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${createType == 'book'}">
                            <input type="hidden" name="command" value="create_book"/>
                            <input type="hidden" name="createType" value="book">
                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="title" style="color: white">
                                    <fmt:message key="label.title"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8 ">
                                    <input type="text" name="title" id="title" class="float-sm-left"
                                           style="width: 220px"
                                           pattern="^[\p{L} ]{5,25}$"
                                           maxlength="25"
                                           title="<fmt:message key="label.invalid.title"/>"
                                           required
                                           value="${bookParameter.get("title")}"
                                    />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="year" style="color: white">
                                    <fmt:message key="label.year"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8 ">
                                    <input type="text" name="year" id="year" class="float-sm-left"
                                           style="width: 220px"
                                           pattern="^[0-9]{4}$"
                                           maxlength="4"
                                           title="<fmt:message key="label.invalid.year"/>"
                                           required
                                           value="${bookParameter.get("year")}"
                                    />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="pages" style="color: white">
                                    <fmt:message key="label.pages"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8 ">
                                    <input type="text" name="pages" id="pages" class="float-sm-left"
                                           style="width: 220px"
                                           pattern="^[0-9]{1-4}$"
                                           maxlength="4"
                                           title="<fmt:message key="label.invalid.pages"/>"
                                           required
                                           value="${bookParameter.get("pages")}"
                                    />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="pages" style="color: white">
                                    <fmt:message key="label.number"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8 ">
                                    <input type="text" name="number" id="number" class="float-sm-left"
                                           style="width: 220px"
                                           pattern="^[0-9]{1-2}$"
                                           maxlength="2"
                                           title="<fmt:message key="label.invalid.number"/>"
                                           required
                                           value="${bookParameter.get("number")}"
                                    />
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="dropdownCategory"
                                       style="color: white">
                                    <fmt:message key="label.category"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8 ">
                                    <select name="category" id="dropdownCategory" class="float-sm-left"
                                            style="width: 220px">
                                        <c:forEach var="categoryItem" items="${allCategories}">
                                            <option value="${categoryItem.toString()}">
                                                    ${categoryItem.toString()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="dropdownAuthor"
                                       style="color: white">
                                    <fmt:message key="label.author"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8 ">
                                    <select name="author" id="dropdownAuthor" class="float-sm-left"
                                            style="width: 220px">
                                        <c:forEach var="authorItem" items="${allAuthors}">
                                            <option value="${authorItem.authorId}">
                                                    ${authorItem.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-4 col-form-label float-sm-right" for="description"
                                       style="color: white">
                                    <fmt:message key="label.description"/><span class="filed-required">*</span>
                                </label>
                                <div class="col-sm-8 ">
                                    <textarea type="text" name="description" id="description" class="float-sm-left"
                                              style="width: 220px"
                                              pattern="^[\p{L} ]{5,250}$"
                                              maxlength="250"
                                              title="<fmt:message key="label.invalid.description"/>"
                                              required
                                              value="${bookParameter.get("description")}"></textarea>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                    <br/>
                    <br/>
                    <div style="color: red;">
                        ${errorDataMessage}
                    </div>
                    <div style="color: green;">
                        ${createAuthorSuccess}
                    </div>
                    <br/>
                    <br/>
                    <div class="form-group row">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary js-scroll-trigger custom-button">
                                <fmt:message key="submit.create"/>
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
