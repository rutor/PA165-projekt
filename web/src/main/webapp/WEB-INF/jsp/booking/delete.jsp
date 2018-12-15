<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Booking deletion">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/booking/delete/${id}" cssClass="form-horizontal">
        <p>Are you sure that you want to delete booking with id: <c:out value="${id}"/></p>
        <button class="btn btn-primary" type="submit">Delete booking</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>