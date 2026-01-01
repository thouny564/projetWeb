<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div id="container-parent">
<div class="login-container">
    <h2 class="login-title">Connexion</h2>

    <c:if test="${param.error != null}">
        <div class="error-message">
            <spring:message code="login.error"/>
        </div>
    </c:if>

    <form class="login-form" method="post" action="${pageContext.request.contextPath}/login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <label class="login-label" for="username"><spring:message code="username"/></label>
        <input class="login-input" type="text" name="username" id="username" required />

        <label class="login-label" for="password"><spring:message code="password"/></label>
        <input class="login-input" type="password" name="password" id="password" required />

        <button class="login-button" type="submit"><spring:message code="logIn"/></button>
    </form>
</div>
</div>