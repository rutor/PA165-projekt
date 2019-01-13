<%-- 
    Document   : view
    Created on : 15.12.2018, 22:53:25
    Author     : Kupker
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Performace">
<jsp:attribute name="body_area">
    <h1>Detail for <c:out value="${performance.id}"/></h1>
    <p>Show: <c:out value="${performance.show.name}"/></p>
    <p>Description: <c:out value="${performance.description}"/></p>
    <p>Price: <c:out value="${performance.price}"/></p>
    <p>StartDate: <c:out value="${performance.startDate}"/></p>
    <p>Hall: <c:out value="${performance.hall.address}, ${performance.hall.name}"/></p>
</jsp:attribute>
</my:administrationTemplate>
