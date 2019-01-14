<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Genre detail">
<jsp:attribute name="body_area">
<h1>Detail for <c:out value="${genre.name}"/></h1>
<p>Description: <c:out value="${genre.description}"/></p>
<h2>Shows of this genre</h2>

</jsp:attribute>
</my:administrationTemplate>