<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:administrationTemplate subtitle="List of bookings">
    <jsp:attribute name="body_area">
        <!-- TODO: Show for admins only -->
        <my:a href="/booking/create" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add new booking
        </my:a>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Payment status</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listOfBookings}" var="booking">
                    <tr>
                        <td><my:a href="/booking/${booking.id}" class="btn btn-primary">
                                <c:out value="${booking.id}" />
                            </my:a></td>
                        <td><c:out value="${booking.paymentStatus}"/></td>
                        <td><c:out value="${booking.description}"/></td>
                        <td>
                            <!-- TODO: show both only for admins -->
                            <my:a href="/booking/pay/${booking.id}" class="btn">Pay</my:a>
                            <my:a href="/booking/delete/${booking.id}" class="btn">Delete</my:a>
                            </td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>



    </jsp:attribute>
</my:administrationTemplate>