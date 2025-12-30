<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>


<%@ include file="include/importTags.jsp" %>

<html>
<head>
    <c:out value="${user.username}" />

</head>
<body>

${user.getUsername()}

<div>

</div>

</body>
</html>
