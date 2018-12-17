<%-- 
    Document   : view
    Created on : 16.12.2018, 20:19:57
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Performace">
<jsp:attribute name="body_area">
    <h1>Detail for <c:out value="${show.id}"/></h1>
    <p><c:out value="${show.name}"/></p>
    <p><c:out value="${show.description}"/></p>
    <p><c:out value="${show.duration}"/></p>
    <p><c:out value="${show.genre}"/></p>
</jsp:attribute>
</my:administrationTemplate>

