<%-- 
    Document   : edit
    Created on : 15.12.2018, 19:06:09
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Edit hall">
    <jsp:attribute name="body_area">
        <form:form method="post" action="${pageContext.request.contextPath}/hall/${hall.id}/edit"
                   modelAttribute="hallEdit" cssClass="form-horizontal">
            <form:hidden path="id"/>
            <div class="form-group">
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                    <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group ${address_error?'has-error':''}">
                    <form:label path="address" cssClass="col-sm-2 control-label">Address</form:label>
                    <div class="col-sm-10">
                        <form:input path="address" cssClass="form-control"/>
                        <form:errors path="address" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group ${address_error?'has-error':''}">
                    <form:label path="capacity" cssClass="col-sm-2 control-label">Capacity</form:label>
                    <div class="col-sm-10">
                        <form:input path="capacity" cssClass="form-control"/>
                        <form:errors path="capacity" cssClass="help-block"/>
                    </div>
                </div>    
                <button class="btn btn-primary" type="submit">Save</button>
            </div>
        </form:form>
    </jsp:attribute>
</my:administrationTemplate>