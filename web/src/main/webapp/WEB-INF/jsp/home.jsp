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
            Shows
            Zde bude vypis aktualnich shows s kratkym popisem, obrazkem a odkazem na detail a koupi vstupenek
        </div>
    </jsp:attribute>
</my:pagetemplate>