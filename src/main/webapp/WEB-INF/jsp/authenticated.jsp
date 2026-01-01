<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="include/importTags.jsp" %>




<form:form modelAttribute="currentUser"
           method="post"
           action="${pageContext.request.contextPath}/user/update"
           class="profile-form">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <table class="profile-table">
        <thead>
            <tr>
                <th><spring:message code="field"/></th>
                <th><spring:message code="value"/></th>
            </tr>
        </thead>
        <tbody>


        <tr>
            <td><spring:message code="firstName"/></td>
            <td>
                <form:input path="firstName" cssClass="profile-input"/>
                <form:errors path="firstName" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td><spring:message code="familyName"/></td>
            <td>
                <form:input path="familyName" cssClass="profile-input"/>
                <form:errors path="familyName" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td><spring:message code="email"/></td>
            <td>
                <form:input path="mailAddress" type="email" cssClass="profile-input"/>
                <form:errors path="mailAddress" cssClass="error"/>
            </td>
        </tr>



        <tr>
    <td><spring:message code="birthdate"/></td>
    <td>
        <form:input path="birthdate" type="date" cssClass="profile-input"/>
        <form:errors path="birthdate" cssClass="error"/>
    </td>
</tr>


        <tr>
            <td><spring:message code="phoneNumber"/></td>
            <td>
                <form:input path="phoneNumber" cssClass="profile-input"/>
                <form:errors path="phoneNumber" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td><spring:message code="street"/></td>
            <td>
                <form:input path="street" cssClass="profile-input"/>
                <form:errors path="street" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td><spring:message code="streetNumber"/></td>
            <td>
                <form:input path="streetNumber" type="number" cssClass="profile-input"/>
                <form:errors path="streetNumber" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td><spring:message code="postalCode"/></td>
            <td>
                <form:input path="postalCode" type="number" cssClass="profile-input"/>
                <form:errors path="postalCode" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td><spring:message code="city"/></td>
            <td>
                <form:input path="city" cssClass="profile-input"/>
                <form:errors path="city" cssClass="error"/>
            </td>
        </tr>

        </tbody>
    </table>

    <div class="form-buttons">
        <button class="btn-submit" type="submit"><spring:message code="updateForm" /></button>
    </div>

</form:form>


<c:if test="${not empty successMessage}">
    <div class="success-message">${successMessage}</div>
</c:if>
