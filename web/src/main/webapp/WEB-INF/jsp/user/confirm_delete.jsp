<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Confirm deletion">
    <jsp:attribute name="body_area">
<p>Do you really want to delete the user <c:out value="${user.lastName} ${user.firstName}"/>?</p>
<form method="post" action="${pageContext.request.contextPath}/user/${user.id}/delete">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary">Yes, delete it</button>

</form>
<my:a href="/user//" target="_parent"><button class="btn btn-primary">No, go back</button></my:a>

</jsp:attribute>
</my:administrationTemplate>