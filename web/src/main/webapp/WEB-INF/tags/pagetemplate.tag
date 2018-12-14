<!DOCTYPE html>
<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
        <!-- bootstrap loaded from content delivery network -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link rel="stylesheet" href="app.css">
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Theatres&Cinemas</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><my:a href="/show/">Shows</my:a></li>
                        <li><my:a href="/administration/">Administration</my:a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <!-- authenticated user info -->
                        <!-- TODO - prizpusobit skutecne implementaci -->
                        <c:choose>
                            <c:when test="${authenticatedUser}">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">user <c:out value="${authenticatedUser.givenName} ${authenticatedUser.surname}"/><b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <c:choose>
                                            <c:when test="${authenticatedUser.role == 'admin'}">
                                                <li><my:a href="/order/list/all">Administration</my:a>
                                                    </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><my:a href="/order/list/all">My orders</my:a></li>
                                                <li><my:a href="/order/list/all">My account</my:a></li>
                                                </c:otherwise>
                                            </c:choose>
                                    </ul>
                                </li>

                            </c:when>
                            <c:otherwise>
                                <li><a href="/registration/"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                                <li><a href="/login/"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

                            </c:otherwise>
                        </c:choose>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container page">

            <!-- page title -->
            <c:if test="${not empty title}">
                <div class="page-header">
                    <h1><c:out value="${title}"/></h1>
                </div>
            </c:if>

            <!-- alerts -->
            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <c:out value="${alert_danger}"/></div>
                </c:if>
                <c:if test="${not empty alert_info}">
                <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
            </c:if>

            <!-- page body -->
            <jsp:invoke fragment="body"/>

            <!-- footer -->
            <footer class="footer">
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Masaryk University</p>
            </footer>
        </div>
        <!-- javascripts placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>