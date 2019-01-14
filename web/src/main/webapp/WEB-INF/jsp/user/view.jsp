<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:administrationTemplate subtitle="User detail">
    <jsp:attribute name="body_area">

<h1>Detail for <c:out value="${user.lastName} ${user.firstName}"/></h1>
<p>Email:<c:out  value="${user.email}"/></p>
    <p>CreatAt:<c:out  value="${user.createdAt}"/></p>
    <p>Role:<c:out  value="${user.role.name}"/></p>

<my:a href="/user/" target="_parent"><button class="btn btn-primary">Go back</button></my:a>

</jsp:attribute>
</my:administrationTemplate>