<%-- 
    Document   : confirm_delete
    Created on : 16.12.2018, 21:09:36
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Delete">
<jsp:attribute name="body_area">
        <p>Do you really want to delete the show <c:out value="${show.id}"/>?</p>
        <form method="post" action="${pageContext.request.contextPath}/show/${show.id}/delete">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Yes, delete it</button>
        </form>
        <my:a href="/show/">No, goback to the overview</my:a>
</jsp:attribute>
</my:administrationTemplate>
