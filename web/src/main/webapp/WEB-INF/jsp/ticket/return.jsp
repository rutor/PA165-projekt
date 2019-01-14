<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Ticket returning">
<jsp:attribute name="body_area">

    <form:form method="post" action="${pageContext.request.contextPath}/ticket/return/${barcode}" cssClass="form-horizontal">
        <p>Are you sure that you want to return ticket with barcode: <c:out value="${barcode}"/></p>
        <button class="btn btn-primary" type="submit">Return ticket</button>
    </form:form>

</jsp:attribute>
</my:administrationTemplate>