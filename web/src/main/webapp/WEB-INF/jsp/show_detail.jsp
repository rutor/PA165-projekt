<%-- 
    Document   : show
    Created on : 17.12.2018, 20:54:42
    Author     : xtrnkal
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="body">
            <table class="table" summary="Show listing">
            <tbody>
                <tr>
                        <td class="home">
                            <div class="img-container home column">
                                <a href="${pageContext.request.contextPath}/show_detail/${show.id}">
                                    <img class="img" src="${pageContext.request.contextPath}/pictures/${show.id mod 3 + 1}.png">
                                </a>
                            </div>
                        </td>
                        
                        <td>
                            <div class="row">
                            <h3>${show.name}</h3>
                            </div>
                            <div class="row genre">
                                <strong>${show.genre.getName()}</strong>
                            </div>
                            <div class="row description">
                                ${show.description}
                            </div>
                            <div class="row show-bottom">

                                
                            </div>
                        </td>
                </tr>
                </tbody>
                </table>
                <table class="table" summary="Performance listing">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Hall</th>
                    <th>Description</th>
                    <th>Buy ticket</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${performances}" var="performance">
                    <tr>
                        <td>
                            ${performance.startDate}
                        </td>
                        <td>
                            ${performance.hall}
                        </td>
                        <td>
                            ${performance.description}
                        </td>

                        <td>
                            <my:a href="${pageContext.request.contextPath}/buy_ticket/${performance.id}" class="btn btn-primary">
                                Buy ticket
                            </my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>