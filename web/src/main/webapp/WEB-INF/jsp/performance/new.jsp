<%-- 
    Document   : new
    Created on : 15.12.2018, 22:53:48
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Add a new performace">
<jsp:attribute name="body_area">
        <form:form method="post" action="${pageContext.request.contextPath}/performance/new"
               modelAttribute="performanceCreate" cssClass="form-horizontal">
        
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:input path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group ${price_error?'has-error':''}">
            <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
            <div class="col-sm-10">
                <form:input type="number" path="price" cssClass="form-control"/>
                <form:errors path="price" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group ${startDate_error?'has-error':''}">
            <form:label path="startDate" cssClass="col-sm-2 control-label">Start date</form:label>
            <div class="col-sm-10">
                <form:input type="datetime-local" path="startDate" cssClass="form-control"/>
                <form:errors path="startDate" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${showId_error?'has-error':''}">
            <form:label path="showId" cssClass="col-sm-2 control-label">Show</form:label>
            <div class="col-sm-10">
                <form:select path="showId" cssClass="form-control">
                     <c:forEach items="${shows}" var="show">
                       <form:option value="${show.id}" label="${show.name}"/>
                    </c:forEach>
                </form:select>      
                <form:errors path="showId" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group ${hallId_error?'has-error':''}">
            <form:label path="hallId" cssClass="col-sm-2 control-label">Hall</form:label>
            <div class="col-sm-10">
                <form:select path="hallId" cssClass="form-control">
                     <c:forEach items="${halls}" var="hall">
                       <form:option value="${hall.id}" label="${hall.name}"/>
                    </c:forEach>
                </form:select>      
                <form:errors path="hallId" cssClass="help-block"/>
            </div>
        </div>
       
        <button class="btn btn-primary" type="submit">Create performace</button>
    </form:form>
</jsp:attribute>
</my:administrationTemplate>
