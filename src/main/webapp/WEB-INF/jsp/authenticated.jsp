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
                <th>Champ</th>
                <th>Valeur</th>
            </tr>
        </thead>
        <tbody>


        <tr>
            <td>Prénom</td>
            <td>
                <form:input path="firstName" cssClass="profile-input"/>
                <form:errors path="firstName" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>Nom de famille</td>
            <td>
                <form:input path="familyName" cssClass="profile-input"/>
                <form:errors path="familyName" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>Email</td>
            <td>
                <form:input path="mailAddress" type="email" cssClass="profile-input"/>
                <form:errors path="mailAddress" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>Téléphone</td>
            <td>
                <form:input path="phoneNumber" cssClass="profile-input"/>
                <form:errors path="phoneNumber" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>Rue</td>
            <td>
                <form:input path="street" cssClass="profile-input"/>
                <form:errors path="street" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>Numéro</td>
            <td>
                <form:input path="streetNumber" type="number" cssClass="profile-input"/>
                <form:errors path="streetNumber" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>Code postal</td>
            <td>
                <form:input path="postalCode" type="number" cssClass="profile-input"/>
                <form:errors path="postalCode" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>Ville</td>
            <td>
                <form:input path="city" cssClass="profile-input"/>
                <form:errors path="city" cssClass="error"/>
            </td>
        </tr>

        </tbody>
    </table>

    <div class="form-buttons">
        <button class="btn-submit" type="submit">Mettre à jour</button>
    </div>

</form:form>


<c:if test="${not empty successMessage}">
    <div class="success-message">${successMessage}</div>
</c:if>
