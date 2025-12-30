<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>

<h1 id="nomSite">Tryhard</h1>
<div id="titleCarousel">
<p style="font-size: 30px; margin-left: 220px; font-weight: bold; ">Dernier jeu en vente</p>
</div>

<a id="principale">

<div id="containerImage">
    <div class="carousel-images" >
        <img  src="${pageContext.request.contextPath}/images/clairObscurAccueil.jpg"class="active">
        <img src="${pageContext.request.contextPath}/images/FF7Rebirth.jpg" alt="FF7Rebirth">
        <img src="${pageContext.request.contextPath}/images/gta6.jpg" alt="gta6">

    </div>

    <button class="prev-btn"><</button>
    <button class="next-btn">></button>

</div>
    <form action="${pageContext.request.contextPath}/catalog" method="get">
        <button id="buttonProducts" type="submit">
            Voir notre catalogue
            <img src="${pageContext.request.contextPath}/images/arrowRight.png" width="16" height="16" style="filter: brightness(0) invert(1);">
        </button>
    </form>

</a>
<div id="LicenseEnVente">

</div>


</div>




