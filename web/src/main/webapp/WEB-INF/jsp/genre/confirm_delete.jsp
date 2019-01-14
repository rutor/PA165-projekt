<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:administrationTemplate subtitle="Confirm deletion">
<jsp:attribute name="body_area">
<p>Do you really want to delete the genre <c:out value="${genre.name}"/>?</p>
<form method="post" action="${pageContext.request.contextPath}/genre/${genre.id}/delete">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary">Yes, delete it</button>
</form>
<my:a href="/genre">No, goback to the overview</my:a>
</jsp:attribute>
</my:administrationTemplate>