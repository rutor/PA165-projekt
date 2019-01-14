<%-- 
    Document   : edit
    Created on : 16.12.2018, 20:56:16
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Edit Show">
<jsp:attribute name="body_area">
    <form:form method="post" action="${pageContext.request.contextPath}/show/edit"
               modelAttribute="showEdit" cssClass="form-horizontal">
        <form:hidden path="id"/>
        
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:textarea cols="80" rows="20" path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group ${duration_error?'has-error':''}">
            <form:label path="duration" cssClass="col-sm-2 control-label">Duration</form:label>
            <div class="col-sm-10">
                <form:input type="number" path="duration" cssClass="form-control"/>
                <form:errors path="duration" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group ${genre_error?'has-error':''}">
            <form:label path="genre" cssClass="col-sm-2 control-label">Genre</form:label>
            <div class="col-sm-10">
                <form:select path="genre" cssClass="form-control" items="${genres}" itemValue="id" itemLabel="name">
                </form:select>      
                <form:errors path="genre" cssClass="help-block"/>
            </div>
        </div>
       
        <button class="btn btn-primary" type="submit">Save</button>
        </form:form>
</jsp:attribute>
</my:administrationTemplate>
