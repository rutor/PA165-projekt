<%-- 
    Document   : list
    Created on : 16.12.2018, 20:19:51
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="Shows">
    <jsp:attribute name="body_area">
        <!-- TODO: Show for admins only -->
        <my:a href="/show/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add new show
        </my:a>
        <table class="table" summary="Show listing">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${shows}" var="show">
                    <tr>
                        <td>
                            <my:a href="/show/${show.id}" class="btn btn-primary">
                                <c:out value="${show.name}" />
                            </my:a>
                        </td>
                        <td>
                            <!-- TODO: show both only for admins -->
                            <my:a href="/show/${show.id}/edit" class="btn">Edit</my:a>
                            <my:a href="/show/${show.id}/delete" class="btn">Delete</my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:administrationTemplate>