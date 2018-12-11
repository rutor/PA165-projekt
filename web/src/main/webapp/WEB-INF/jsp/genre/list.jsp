<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Genre listing">
<jsp:attribute name="body">
<!-- TODO: Show for admins only -->    
<my:a href="/genre/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add
    </my:a>
    <table class="table" summary="Genre listing">
        <thead>
        <tr>
                        <th>Name</th>
            <th>Description</th>
        <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${genres}" var="genre">
            <tr>
                <td>
                    <my:a href="/genre/${genre.id}" class="btn btn-primary"><c:out value="${genre.name}"/></my:a>                   
                </td>
                <td><c:out value="${genre.description}"/></td>
                <td>
                    <!-- TODO: show only for admins -->
                    <my:a href="/genre/${genre.id}/delete" class="btn">Delete</my:a>
                    <my:a href="/genre/${genre.id}/edit" class="btn">Edit</my:a>
                </td>
                                                </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>