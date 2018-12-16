<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Role detail">
<jsp:attribute name="body">
<h1>Detail for <c:out value="Name:${role.name}"/></h1>
<p>Description:<c:out value="${role.description}"/></p>
<h2>All users of this role</h2>
<c:forEach items="${user}" var="user">
    <p>User: ${user.lastName} ${user.firstName}</p><br/>

</c:forEach>
<my:a href="/role/" target="_parent"><button class="btn btn-primary">Go back</button></my:a>
</jsp:attribute>
</my:pagetemplate>