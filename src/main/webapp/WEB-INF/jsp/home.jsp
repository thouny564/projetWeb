<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="include/importTags.jsp" %>

<html>
<head>
    <title>${title}</title>
</head>
<body>

<div>

<header>
<div>
    <input type="checkbox" role="button" aria-label="Display the menu" class="menu">
    <span>Les categories</span>
    <nav id="menu" th:style="'display:none;'">



    </nav>
</div>

<script>
  const toggle = document.getElementById('menuToggle');
  const menu = document.getElementById('menu');

  toggle.addEventListener('change', () => {
    menu.style.display = toggle.checked ? 'block' : 'none';
  });
</script>

</header>

    <c:forEach var="product" items="${products}">
        <p>${product}</p>
    </c:forEach>
</div>

</body>
</html>
