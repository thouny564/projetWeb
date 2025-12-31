
<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../include/importTags.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<html>
<head>
    <link type="text/css" href="<spring:url value='/css/header.css' />" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/login.css' />" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/accueil.css' />" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/cart.css' />" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/catalog.css' />" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/product.css' />" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/compagnyDescription.css' />" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/authenticated.css' />" rel="stylesheet">

    <spring:url var="localeFr" value="">
        <spring:param name="locale" value="fr"/>
    </spring:url>

    <spring:url var="localeEn" value="">
        <spring:param name="locale" value="en"/>
    </spring:url>


</head>
<body>



      <nav id="sidebar">
                <div>
                    <a href="${pageContext.request.contextPath}/welcome">
                        <img src="${pageContext.request.contextPath}/images/jeux.png" alt="<spring:message code='home'/>" width="40" height="40" style="margin-left: 20px;">
                    </a>
                </div>
                <div id="optionSite">
                        <c:choose>
                        <c:when test="${not empty  pageContext.request.userPrincipal}">
                         <p><spring:message code="welcomeUser"/> ${pageContext.request.userPrincipal.name} !</p>
                        </c:when>

                        </c:choose>
                        <a id="shopDescription" href="${pageContext.request.contextPath}/compagnyDescription" title="<spring:message code='shop'/>"  style="margin-right: 20px; "><spring:message code="shop"/></a>

                        <div class="dropdown">
                                <button class="dropbtn"><spring:message code="languageTitle"/><span class="arrow">∨</span></button>
                                <ul class="dropdown-content">
                                <li>
                                    <img src="${pageContext.request.contextPath}/images/drapeau.png" width="16px" height="16px">
                                    <a href="${localeFr}"><spring:message code="frenchChoose"/></a>
                                </li>

                                <li>
                                    <img src="${pageContext.request.contextPath}/images/royaume-uni.png" width="16px" height="16px">
                                    <a href="${localeEn}"><spring:message code="englishChoose"/></a>
                                </li>
                                </ul>

                        </div>
                    <c:choose>
                     <c:when test="${not empty  pageContext.request.userPrincipal}">
                    <form method="post" action="<c:url value='/logout' />" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button id="logout-form" type="submit"><spring:message code="deconnectText"/></button>
                    </form>

                    </c:when>

                    <c:otherwise>
                    <form action="${pageContext.request.contextPath}/register" method="get">
                        <button id="MyAccount"><spring:message code="inscription"/></button>
                    </form>
                    </c:otherwise>

                    </c:choose>


                <a href="${pageContext.request.contextPath}/authenticated" title="<spring:message code="hrefTitleAccount"/>">
                    <img src="${pageContext.request.contextPath}/images/utilisateur.png" alt="<spring:message code="profile"/>" class="nav-icon" width="30" height="30" style="filter: brightness(0) invert(1);">
                </a>

                <a href="${pageContext.request.contextPath}/cart">
                    <img src="${pageContext.request.contextPath}/images/ajouter-au-panier.png" alt="<spring:message code="cart"/>" class="nav-icon" width="30" height="30" style="filter: brightness(0) invert(1);">
                </a>

                </div>

        </nav>


    <div  class="main-content">
    <tiles:insertAttribute name="main-content" />
    </div>

<footer>
    <div class="footer-content">

    <div class="footer-links">
        <a href=${pageContext.request.contextPath}/welcome><spring:message code='home'/></a>
        <a href="${pageContext.request.contextPath}/authenticated"><spring:message code="hrefTitleAccount"/></a>
        <a href="${pageContext.request.contextPath}/compagnyDescription"><spring:message code="shop"/></a>

    </div>

    <p>© 2025 Tryhard — <spring:message code="rightReserved"/></p>





  </div>
</footer>

      <script src="<spring:url value='/js/carousel.js' />"></script>

</body>


</html>
