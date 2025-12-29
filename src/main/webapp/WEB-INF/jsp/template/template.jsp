
<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../include/importTags.jsp" %>

<html>
<head>
    <link type="text/css" href="<spring:url value='/css/header.css' />" rel="stylesheet">

</head>
<body>



      <nav id="sidebar">
                <div>
                <a href="${pageContext.request.contextPath}/welcome" title="accueil">
                    <img src="${pageContext.request.contextPath}/images/jeux.png" alt="Accueil" width="40" height="40" style="margin-left: 20px;">
                </div>
                <div id="optionSite">
                        <a  id="shopDescription" href="descriptionMagasin" title="magasin" style="margin-right: 20px; ">Notre magasin</a>



                            <div class="dropdown">
                                <button class="dropbtn">FR<span class="arrow">∨</span></button>
                                <ul class="dropdown-content">
                                <li>
                                    <img src="${pageContext.request.contextPath}/images/drapeau.png" width="16px" height="16px">
                                    <a href="#">Français</a>
                                </li>

                                <li>
                                    <img src="${pageContext.request.contextPath}/images/royaume-uni.png" width="16px" height="16px">
                                    <a href="#">Anglais</a>
                                </li>
                                </ul>

                            </div>




                <a href="profil.html" title="Mon compte">
                    <img src="${pageContext.request.contextPath}/images/utilisateur.png" alt="Profil" class="nav-icon" width="30" height="30" style="filter: brightness(0) invert(1);">
                </a>

                <a href="panier.html" title="Mon panier">
                    <img src="${pageContext.request.contextPath}/images/ajouter-au-panier.png" alt="Panier" class="nav-icon" width="30" height="30" style="filter: brightness(0) invert(1);">
                </a>

            </div>

        </nav>







    <tiles:insertAttribute name="main-content" />


</body>
</html>
