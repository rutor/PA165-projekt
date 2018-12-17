<%-- 
    Document   : list
    Created on : 15.12.2018, 22:53:16
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Performances">
    <jsp:attribute name="body_area">
        <!-- TODO: Show for admins only -->
        <my:a href="/performance/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add new performance
        </my:a>
        <table class="table" summary="Performances">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${performances}" var="performance">
                    <tr>
                        <td>
                            <my:a href="/performance/${performance.id}" class="btn btn-primary">
                                <c:out value="${performance.description}" />
                            </my:a>
                        </td>
                        <td>
                            <!-- TODO: show both only for admins -->
                            <my:a href="/performance/${performance.id}/edit" class="btn">Edit</my:a>
                            <my:a href="/performance/${performance.id}/delete" class="btn">Delete</my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:administrationTemplate>
