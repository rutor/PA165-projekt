<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="body">

        <div class="current-shows-container">
            <div class="current-shows-panel row up-to-large-5">
                <div class="img-container column">
                    <img class="img" src="pictures/ballet.png">
                </div>
                <div class="img-container column">
                    <img class="img" src="pictures/smetana.jpg">
                </div>
                <div class="img-container column">
                    <img class="img" src="pictures/zelary.jpg">
                </div>
                <div class="img-container column">
                    <img class="img" src="pictures/ballet.png">
                </div>
                <div class="img-container column">
                    <img class="img" src="pictures/smetana.jpg">
                </div>
            </div>
        </div>

        <div>
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
                                <c:out value="${show.description}" />
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
        </div>
    </jsp:attribute>
</my:pagetemplate>