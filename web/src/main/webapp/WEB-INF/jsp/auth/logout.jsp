<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:administrationTemplate subtitle="Log out">
    <jsp:attribute name="body_area">
<div class="row">
    <div class="col-6 col-md-6 col-md-offset-3 col-offset-3">
        <form class="form" method="POST" action="${pageContext.request.contextPath}/auth/logout">
            <h2 class="form-heading">Logout?</h2>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
        </form>
    </div>
</div>
</jsp:attribute>
</my:administrationTemplate>
