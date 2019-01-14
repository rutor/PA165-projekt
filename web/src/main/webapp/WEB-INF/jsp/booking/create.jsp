<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Booking creation">
<jsp:attribute name="body_area">

    <form:form method="post" action="${pageContext.request.contextPath}/booking/create"
               modelAttribute="bookingCreate" cssClass="form-horizontal">
               <!-- Performance -->
        <div class="form-group ${performance_error?'has-error':''}">
            <form:label path="performance" cssClass="col-sm-2 control-label">Performance</form:label>
            <div class="col-sm-10">
                <form:select path="performance" cssClass="form-control">
                    <c:forEach items="${performances}" var="performance">
                        <c:choose>
                            <c:when test="${selectedPerformance == performance.id}">
                                <form:option selected="selected" value="${performance.id}" label="${performance.getDisplayableFormat()}"/>
                            </c:when>
                            <c:otherwise>
                                <form:option value="${performance.id}" label="${performance.getDisplayableFormat()}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
                <form:errors path="performance" cssClass="help-block"/>
            </div>
        </div>
        <!-- Description -->
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:input path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create booking</button>
    </form:form>

</jsp:attribute>
</my:administrationTemplate>