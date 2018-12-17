<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:administrationTemplate subtitle="Role listing">
    <jsp:attribute name="body_area">
        <!-- TODO: Show for admins only -->
        <my:a href="/role/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add
        </my:a>
        <table class="table" summary="Role listing">
            <thead>
            <tr>
                <th>Name</th>
                <th>Operations</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${roles}" var="roles">
                    <tr>
                        <td>
                            <my:a href="/role/${roles.id}" class="btn btn-primary">
                                <c:out value="${roles.name}" />
                            </my:a>
                        </td>
                        <td>
                            <!-- TODO: show both only for admins -->
                            <my:a href="/role/${roles.id}/edit" class="btn">Edit</my:a>
                            <my:a href="/role/${roles.id}/delete" class="btn">Delete</my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </jsp:attribute>
</my:administrationTemplate>