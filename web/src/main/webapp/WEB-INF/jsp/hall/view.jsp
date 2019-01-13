<%-- 
    Document   : view
    Created on : 15.12.2018, 19:09:50
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Hall detail">
<jsp:attribute name="body_area">
    <h1>Detail for <c:out value="${hall.name}"/></h1>
    <p>Address: <c:out value="${hall.address}"/></p>
    <p>Capacity: <c:out value="${hall.capacity}"/></p>
</jsp:attribute>
</my:administrationTemplate>
