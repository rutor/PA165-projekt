<%-- 
    Document   : bookings_tickets
    Created on : 17.12.2018, 22:19:02
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="My tickets">
    <jsp:attribute name="body">
        
        <table class="table" summary="My tickets">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Show</th>
                    <th>Date</th>
                    <th>Buy date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tickets}" var="ticket">
                    <tr>
                        <td><c:out value="${ticket.barcode}"/></td>
                        <td><c:out value="${ticket.performance}"/></td>
                        <td><c:out value="${ticket.performance}"/></td>
                        <td>${ticket.createdAd}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
                
    </jsp:attribute>
</my:pagetemplate>
