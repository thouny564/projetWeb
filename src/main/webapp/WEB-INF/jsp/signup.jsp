<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="include/importTags.jsp" %>



<html>
<head>
    <title>${title}</title>
    <link type="text/css" href="<spring:url value='/css/signup.css' />" rel="stylesheet">
    <style>
        .error {
            color: red;
            font-size: 0.9em;
            margin-left: 10px;
        }
    </style>
</head>
<body>

<div class="signup-form">
    <h2>Signup Form</h2>

    <form:form modelAttribute="currentUser" method="post" action="/eshop/register/submitSignup">

        <!-- Token CSRF -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


        <div class="form-group">
            <form:label path="username">Username:</form:label>
            <form:input path="username" />
            <form:errors path="username" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="password">Password:</form:label>
            <form:password path="password" />
            <form:errors path="password" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="phoneNumber">Phone Number:</form:label>
            <form:input path="phoneNumber" />
            <form:errors path="phoneNumber" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="mailAddress">Email Address:</form:label>
            <form:input path="mailAddress" type="email" />
            <form:errors path="mailAddress" cssClass="error" />
        </div>


        <form:hidden path="enabled" value="true"/>


        <div class="form-group">
            <form:label path="firstName">First Name:</form:label>
            <form:input path="firstName" />
            <form:errors path="firstName" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="familyName">Family Name:</form:label>
            <form:input path="familyName" />
            <form:errors path="familyName" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="street">Street:</form:label>
            <form:input path="street" />
            <form:errors path="street" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="streetNumber">Street Number:</form:label>
            <form:input path="streetNumber" type="number"/>
            <form:errors path="streetNumber" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="postalCode">Postal Code:</form:label>
            <form:input path="postalCode" type="number" min="0"/>
            <form:errors path="postalCode" cssClass="error" />
        </div>

        <div class="form-group">
            <form:label path="city">City:</form:label>
            <form:input path="city" />
            <form:errors path="city" cssClass="error" />
        </div>



        <div class="form-group">
            <form:button type="submit">Submit</form:button>

        </div>
    </form:form>

</div>

</body>
</html>
