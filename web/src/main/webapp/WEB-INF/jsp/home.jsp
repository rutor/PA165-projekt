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
                <c:forEach items="${shows}" var="show">
                <div class="img-container column">
                    <a href="${pageContext.request.contextPath}/show_detail/${show.id}">
                    <img class="img" src="${pageContext.request.contextPath}/pictures/${show.id mod 3 + 1}.png">
                    </a>
                </div>
                </c:forEach>
            </div>
        </div>

        <div>
            <table class="table" summary="Show listing">
            <tbody>
                <c:forEach items="${shows}" var="show">
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
                                <my:a href="${pageContext.request.contextPath}/show_detail/${show.id}" class="btn btn-primary">
                                    More information
                                </my:a>
                                <my:a href="${pageContext.request.contextPath}/show_detail/${show.id}" class="btn btn-primary">
                                    Buy tickets
                                </my:a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            </table>
        </div>
    </jsp:attribute>
</my:pagetemplate>