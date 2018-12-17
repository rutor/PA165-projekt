<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:administrationTemplate subtitle="Edit a user">
    <jsp:attribute name="body_area">
    <form:form method="post" action="${pageContext.request.contextPath}/user/${user.id}/edit"
               modelAttribute="userEdit"  cssClass="form-horizontal">
        <form:hidden path="id"/>
               <div class="form-group">
        <div class="form-group ${lastName_error?'has-error':''}">
            <form:label path="lastName" cssClass="col-sm-2 control-label">LastName</form:label>
            <div class="col-sm-10">
                <form:input path="lastName" cssClass="form-control"/>
                <form:errors path="lastName" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${firstName_error?'has-error':''}">
            <form:label path="firstName" cssClass="col-sm-2 control-label">FirstName</form:label>
            <div class="col-sm-10">
                <form:input path="firstName" cssClass="form-control"/>
                <form:errors path="firstName" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${email_error?'has-error':''}">
            <form:label path="email" cssClass="col-sm-2 control-label">Email</form:label>
            <div class="col-sm-10">
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${password_error?'has-error':''}">
              <form:label path="password" cssClass="col-sm-2 control-label">Password</form:label>
              <div class="col-sm-10">
                  <form:password path="password" cssClass="form-control"/>
                 <form:errors type="password" path="password" cssClass="help-block"/>
             </div>
         </div>

        <div class="form-group ${role_error?'has-error':''}">
            <form:label path="role" cssClass="col-sm-2 control-label">Role</form:label>
            <div class="col-sm-10">
            <form:select path="role" cssClass="form-control" items="${roles}" itemValue="id" itemLabel="name">
            </form:select>
                <form:errors path="role" cssClass="help-block"/>
            </div>
        </div>

         <%--<div class="form-group ${role_error?'has-error':''}">--%>
             <%--<form:label path="role" cssClass="col-sm-2 control-label">Role</form:label>--%>
             <%--<div class="col-sm-10">--%>
                <%--<form:select path="role" cssClass="form-control">--%>
                     <%--<c:forEach items="${roles}" var="role">--%>
                       <%--<form:option value="${role.id}" label="${role.name}"/>--%>
                    <%--</c:forEach>--%>
                <%--</form:select>--%>
                 <%--<form:errors path="role" cssClass="help-block"/>--%>
             <%--</div>--%>
         <%--</div>--%>




        <button class="btn btn-primary" type="submit">Save</button>
    </form:form>
<my:a href="/user/" target="_parent"><button class="btn btn-primary">No, go back</button></my:a>
</jsp:attribute>
</my:administrationTemplate>