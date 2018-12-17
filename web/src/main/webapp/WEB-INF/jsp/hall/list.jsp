<%-- 
    Document   : list
    Created on : 15.12.2018, 18:44:19
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Hall listing">
    <jsp:attribute name="body_area">
        <!-- TODO: Show for admins only -->
        <my:a href="/hall/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add
        </my:a>
        <table class="table" summary="Hall listing">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Capacity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${halls}" var="hall">
                    <tr>
                        <td>
                            <my:a href="/hall/${hall.id}" class="btn btn-primary">
                                <c:out value="${hall.name}" />
                            </my:a>
                        </td>
                        <td>
                                <c:out value="${hall.address}" />
                        </td>
                        <td>
                                <c:out value="${hall.capacity}" />
                        </td>
                        <td>
                            <!-- TODO: show both only for admins -->
                            <my:a href="/hall/${hall.id}/edit" class="btn">Edit</my:a>
                            <my:a href="/hall/${hall.id}/delete" class="btn">Delete</my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:administrationTemplate>
