<%-- 
    Document   : confirm_delete
    Created on : 15.12.2018, 22:54:06
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Delete">
<jsp:attribute name="body_area">
        <p>Do you really want to delete the hall <c:out value="${performance.id}"/>?</p>
        <form method="post" action="${pageContext.request.contextPath}/performance/${performance.id}/delete">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Yes, delete it</button>
        </form>
        <my:a href="/performance/">No, goback to the overview</my:a>
</jsp:attribute>
</my:administrationTemplate>