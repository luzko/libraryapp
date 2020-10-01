<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="ctg" uri="customtags" %>--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

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
        <%@include file="../vendor/fontawesome-free/css/all.min.css"%>

        input {
            height: 20px;
        }

        i.fa {
            width: 26px;
            height: 26px;
            line-height: 26px;
            text-align: center;
            margin-right: -26px;
            position: relative;
            z-index: 1;
            float: left;
        }

        i.fa + input {
            padding-left: 26px;
        }

        .field {
            clear: both;
            text-align: right;
            line-height: 25px;
        }

        label {
            float: left;
            padding-right: 10px;
        }

        .main {
            float: left
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
</head>

<body id="page-top">
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="home_page"/>
            <button type="submit" class="btn btn-outline-secondary"><fmt:message key="submit.home"/>
            </button>
        </form>
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="GET">
            <input type="hidden" name="command" value="library_page"/>
            <button type="submit" class="btn btn-outline-secondary"><fmt:message key="submit.library"/>
            </button>
        </form>
        <form class="form-inline" name="Simple" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit" class="btn btn-outline-secondary"><fmt:message key="label.logout"/>
            </button>
        </form>
    </div>
</nav>


<section id="about" class="about-section text-center">
    <div class="masthead3">
        <div class="container  align-items-center">
            <div class="row justify-content-center no-gutters">

                <%--<c:if test="${ not empty type and type eq 'see' }">--%>

                    <div class="col-lg-4">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/${userImage}" alt=""
                             style="border-radius: 30px" width="400" height="400"/>
                    </div>
                    <div class="col-lg-8 text-center">
                        <h1 style="color: #9fcdff">${userName} ${surname}</h1>
                        <div class="row">
                            <div class="col-lg-6 text-right">
                                <h4 style="color: rgba(138,136,137,0.99)">
                                    <br><br>
                                    <fmt:message key="label.email"/>
                                    <br>
                                    <fmt:message key="label.login"/>:
                                    <br>
                                    <fmt:message key="label.status"/>:
                                    <br>
                                    <fmt:message key="label.userRoleType"/>:
                                    <br>
                                    <fmt:message key="label.money.balance"/>:
                                    <br>
                                    <fmt:message key="label.book"/>:
                                    <br>
                                    <fmt:message key="label.reading.plan.name"/>:
                                </h4>
                                <br><br><br><br><br><br><br><br><br><br>

                                <c:if test="${userRoleType eq 'ADMIN'}">
                                    <div class="text-right">
                                        <form class="form-inline" name="Simple"
                                              action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="to_finance_list"/>
                                            <button class="btn btn-outline-success my-2" type="submit">
                                                    <fmt:message key="submit.Settings.check.list"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="text-right">
                                        <form class="form-inline" name="Simple"
                                              action="${pageContext.request.contextPath}/controller" method="post">
                                            <input type="hidden" name="command" value="go_to_all_users_list"/>
                                            <button class="btn btn-outline-success my-2" type="submit">
                                                    <fmt:message key="submit.all.users.list"/>
                                            </button>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-lg-6 text-left">
                                <h4 style="color: rgba(138,136,137,0.99)">
                                    <br><br>
                                        ${email}
                                    <br>
                                        ${login}
                                    <br>
                                        ${status}
                                    <br>
                                        ${userRoleType}
                                    <br>
                                        ${money}
                                    <br>
                                        ${bookName}
                                    <br>
                                        ${readingPlanName}
                                    <br><br><br>
                                </h4>
                                <div class="text-right">
                                    <form class="form-inline" name="Simple"
                                          action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="settings"/>
                                        <button class="btn btn-outline-success my-2" type="submit">
                                                <fmt:message key="submit.Settings.profile"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="text-right">
                                    <form class="form-inline" name="Simple"
                                          action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="to_list_complete_books"/>
                                        <button class="btn btn-outline-success my-2" type="submit">
                                                <fmt:message key="submit.to.list.completed.book"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="text-right">
                                    <div class="bd-example text-left">
                                        <button type="button" class="btn btn-outline-success my-2" data-toggle="modal"
                                                data-target="#exampleModal" data-whatever="@fat"><fmt:message
                                                key="submit.Settings.pay"/></button>
                                        <div class="modal fade" id="exampleModal" tabindex="-1" userRoleType="dialog"
                                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <form class="form" action="${pageContext.request.contextPath}/controller"
                                                  method="post">
                                                <input type="hidden" name="command" value="pay"/>
                                                <div class="modal-dialog" userRoleType="document">
                                                    <div class="modal-content">
                                                        <div class="row">
                                                            <div class="modal-body  col-lg-12">
                                                                <div class="form-group col-lg-8 text-left">
                                                                    <label for="count"
                                                                           class="form-control-label"><fmt:message
                                                                            key="text.blocked.theme"/></label>
                                                                    <input type="text" class="form-control" id="count"
                                                                           name="count"
                                                                           pattern="^(?!0+\.0+$)\d{1,3}(?:,\d{3})*\.\d{2}$">
                                                                </div>
                                                                <div class="form-group col-md-4">
                                                                    <label for="inputState"><fmt:message
                                                                            key="submit.currency"/></label>
                                                                    <select id="inputState" class="form-control"
                                                                            name="currency">
                                                                        <option selected>BYN</option>
                                                                        <option>USD</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary"
                                                                    data-dismiss="modal"><fmt:message
                                                                    key="submit.close"/></button>
                                                            <button type="submit" class="btn btn-primary"><fmt:message
                                                                    key="submit.send"/></button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
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
                                    <fmt:message key="submit.save"/>
                                </label>
                            </button>
                        </form>
                    </div>
                    <div class="col-lg-8">
                        <div class="main">
                            <br>
                                ${ChangedSave}
                            <br>
                            <div class="field">
                                <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                                    <br>
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
                                    <br>
                                        ${loginError}
                                    <br>
                                </form>
                            </div>
                            <div class="field">
                                <form class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                                    <br>
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
                                    <br>
                                        ${usernameError}
                                    <br>
                                </form>
                            </div>
                            <div class="field">
                                <form  class="form-inline" action="${pageContext.request.contextPath}/controller" method="post">
                                    <br>
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
                                    <br>
                                        ${surnameError}
                                    <br>
                                </form>
                            </div>
                        </div>
                        <c:if test="${userBookId != null}">
                            <div class="row">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <br>
                                    <input type="hidden" name="command" value="delete_user_book"/>
                                    <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                        <label>
                                            <fmt:message key="delete"/> ${bookName}
                                        </label>
                                    </button>
                                    <br>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${userPlanId != null}">
                            <div class="row">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <br>
                                    <input type="hidden" name="command" value="delete_reading_plan"/>
                                    <input type="submit" style="color: #9fcdff"  value="<fmt:message key="delete"/> ${readingPlanName} ">
                                    <br>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${userBookStatus == null and userBookId != null}">
                            <div class="row">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <br>
                                    <input type="hidden" name="command" value="new_completed_book"/>
                                    <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                        <label>
                                            <fmt:message key="submit.book.status"/>
                                        </label>
                                    </button>
                                    <br>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${ not (readingPlanName eq '--')}">
                            <div class="row">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <br>
                                    <input type="hidden" name="command" value="delete_user_reading_plan"/>
                                    <button class="btn btn-outline-success my-2 pull-right" type="submit">
                                        <label>
                                            <fmt:message key="delete"/> ${readingPlanName}
                                        </label>
                                    </button>
                                    <br>
                                </form>
                            </div>
                        </c:if>
                    </div>
                    <br>
                    <br/>
                    <br/>
                    <br/>
                </c:if>--%>
            </div>
        </div>
        <br><br><br> <br><br><br> <br><br><br> <br><br><br>
        <div class="row justify-content-center no-gutters mx-auto text-center">
            <h1 class="mx-auto my-0 text-uppercase" style="color: #1c7430"><fmt:message key="text.site.name"/></h1>
        </div>
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
