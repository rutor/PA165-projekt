<%-- 
    Document   : administrationTemplate
    Created on : 15.12.2018, 13:25:19
    Author     : xtrnkal
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="body_area" fragment="true" %>
<%@ attribute name="subtitle" required="false" %>

<my:pagetemplate title="Administration">
    <jsp:attribute name="body">
        <div class="btn-group btn-group-justified">
            <a href="${pageContext.request.contextPath}/show/" class="btn btn-primary">Shows</a>
            <a href="${pageContext.request.contextPath}/performance/" class="btn btn-primary">Performances</a>
            <a href="${pageContext.request.contextPath}/booking/" class="btn btn-primary">Bookings</a>
            <a href="${pageContext.request.contextPath}/genre/" class="btn btn-primary">Genres</a>
            <a href="${pageContext.request.contextPath}/hall/" class="btn btn-primary">Halls</a>
            <a href="${pageContext.request.contextPath}/user/" class="btn btn-primary">Users</a>
        </div>
        <!-- page title -->
            <c:if test="${not empty subtitle}">
                <div class="page-header">
                    <h2><c:out value="${subtitle}"/></h2>
                </div>
            </c:if>
        <jsp:invoke fragment="body_area"/>
    </jsp:attribute>
</my:pagetemplate>