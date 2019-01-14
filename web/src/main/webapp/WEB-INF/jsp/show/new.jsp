<%-- 
    Document   : new
    Created on : 16.12.2018, 20:19:44
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Add a new show">
<jsp:attribute name="body_area">
        <form:form method="post" action="${pageContext.request.contextPath}/show/new"
               modelAttribute="showCreate" cssClass="form-horizontal">
        
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
            
        <div class="form-group ${genreId_error?'has-error':''}">
            <form:label path="genreId" cssClass="col-sm-2 control-label">Genre</form:label>
            <div class="col-sm-10">
                <form:select path="genreId" cssClass="form-control">
                     <c:forEach items="${genres}" var="genre">
                       <form:option value="${genre.id}" label="${genre.name}"/>
                    </c:forEach>
                </form:select>      
                <form:errors path="genreId" cssClass="help-block"/>
            </div>
        </div>
      
        <button class="btn btn-primary" type="submit">Create show</button>
    </form:form>
</jsp:attribute>
</my:administrationTemplate>

