<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Booking payment">
<jsp:attribute name="body_area">

    <form:form method="post" action="${pageContext.request.contextPath}/booking/pay"
               modelAttribute="payDTO" cssClass="form-horizontal">
        <form:hidden path="id" value="${payDTO.id}"/>
        <div class="form-group ${paymentMethod_error?'has-error':''}">
            <form:label path="paymentMethod" cssClass="col-sm-2 control-label">Payment method</form:label>
            <div class="col-sm-10">
                <form:select path="paymentMethod" cssClass="form-control" items="${paymentMethods}"/>
                <form:errors path="paymentMethod" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Pay booking</button>
    </form:form>

</jsp:attribute>
</my:administrationTemplate>