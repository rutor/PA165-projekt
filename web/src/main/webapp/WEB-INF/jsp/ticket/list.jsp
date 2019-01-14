<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="List of tickets">
<jsp:attribute name="body_area">

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Barcode</th>
                <th>Performance</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listOfTickets}" var="ticket">
            <tr>
                <td><c:out value="${ticket.barcode}"/></td>
                <td><c:out value="${ticket.performance}"/></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:administrationTemplate>