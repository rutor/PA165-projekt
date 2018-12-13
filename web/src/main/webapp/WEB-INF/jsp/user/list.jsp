<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="User listing">
    <jsp:attribute name="body">
        <my:a href="/user/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add
        </my:a>
        <table class="table" summary="User listing">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Operations</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${user}" var="user">
                    <tr>
                        <td>
                            <my:a href="/user/${user.id}" class="btn btn-primary">
                                <c:out value="${user.lastName} ${user.firstName}" />
                            </my:a>
                        </td>

                        <td>
                            <!-- TODO: show both only for admins -->
                            <my:a href="/user/${user.id}/edit" class="btn">Edit</my:a>
                            <my:a href="/user/${user.id}/delete" class="btn">Delete</my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>