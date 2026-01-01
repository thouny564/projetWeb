<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="include/importTags.jsp" %>

<div id="container-parent">
    <div class="signup-form">
        <h2><spring:message code="form"/></h2>

        <form:form id="signupForm" modelAttribute="currentUser" method="post"
                   action="/eshop/register/submitSignup">

            <!-- Token CSRF -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="form-group">
                <form:label path="username"><spring:message code="username"/>:</form:label>
                <form:input path="username" />
                <form:errors path="username" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="password"><spring:message code="password"/>:</form:label>
                <form:password path="password" />
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirmer le mot de passe :</label>
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" />
                <span id="passwordError" class="error" style="color:red; display:none;"></span>
            </div>

            <div class="form-group">
                <form:label path="phoneNumber"><spring:message code="phoneNumber"/>:</form:label>
                <form:input path="phoneNumber" />
                <form:errors path="phoneNumber" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="mailAddress"><spring:message code="email"/>:</form:label>
                <form:input path="mailAddress" type="email" />
                <form:errors path="mailAddress" cssClass="error" />
            </div>

            <form:hidden path="enabled" value="true"/>

            <div class="form-group">
                <form:label path="firstName"><spring:message code="firstName"/>:</form:label>
                <form:input path="firstName" />
                <form:errors path="firstName" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="familyName"><spring:message code="familyName"/>:</form:label>
                <form:input path="familyName" />
                <form:errors path="familyName" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="street"><spring:message code="street"/>:</form:label>
                <form:input path="street" />
                <form:errors path="street" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="streetNumber"><spring:message code="streetNumber"/>:</form:label>
                <form:input path="streetNumber" type="number"/>
                <form:errors path="streetNumber" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="postalCode"><spring:message code="postalCode"/>:</form:label>
                <form:input path="postalCode" type="number" min="0"/>
                <form:errors path="postalCode" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="city"><spring:message code="city"/>:</form:label>
                <form:input path="city" />
                <form:errors path="city" cssClass="error" />
            </div>

            <div id="container-buttonSubmit" class="form-group">
                <form:button type="submit"><spring:message code="submitBoutton"/></form:button>
            </div>
        </form:form>

        <script src="<spring:url value='/js/validationPasswordSame.js' />"></script>

    </div>
</div>
