<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Booking payment">
<jsp:attribute name="body_area">

    <form:form method="post" action="${pageContext.request.contextPath}/ticket/verify"
               modelAttribute="validationDTO" cssClass="form-horizontal">
        <div class="form-group ${barcode_error?'has-error':''}">
            <form:label path="barcode" cssClass="col-sm-2 control-label">Barcode</form:label>
            <div class="col-sm-10">
                <form:input path="barcode" cssClass="form-control"/>
                <form:errors path="barcode" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${performanceId_error?'has-error':''}">
            <form:label path="performanceId" cssClass="col-sm-2 control-label">Select performance</form:label>
            <div class="col-sm-10">
                <form:select path="performanceId" cssClass="form-control">
                    <c:forEach items="${performances}" var="performance">
                        <form:option value="${performance.id}" label="${performance.toString()}"/>
                    </c:forEach>
                </form:select>
                <form:errors path="performanceId" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Validate ticket</button>
    </form:form>

</jsp:attribute>
</my:administrationTemplate>