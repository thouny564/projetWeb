<%@ page pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8"%>


<%@ include file="include/importTags.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<spring:url value='/css/accueil.css' />">
    <script src="https://unpkg.com/@ant-design/icons@5.5.0/dist/umd/index.min.js"></script>
</head>
<body>

<header>
<h1 id="nomSite">Tryhard</h1>
</header>
<div id="titleCarousel">
<p style="font-size: 30px; margin-left: 220px; font-weight: bold; ">Dernier jeu en vente</p>
</div>

<div id="principale">

<div id="containerImage">
    <div class="carousel-images" >
        <img  src="${pageContext.request.contextPath}/images/clairObscurAccueil.jpg"class="active">
        <img src="${pageContext.request.contextPath}/images/FF7Rebirth.jpg" alt="FF7Rebirth">
        <img src="${pageContext.request.contextPath}/images/gta6.jpg" alt="gta6">

    </div>

    <button class="prev-btn">❮</button>
    <button class="next-btn">❯</button>

</div>


    <button  id="buttonProducts">
Voir notre catalogue
    <img src="${pageContext.request.contextPath}/images/109617.png" width: 16px height="16px" style="filter: brightness(0) invert(1); ">

    </button>
<div id="LicenseEnVente">




    <script>

    const images = document.querySelectorAll(".carousel-images img");
    const nextBtn = document.querySelector(".next-btn");
    const prevBtn = document.querySelector(".prev-btn");

    let index = 0;

// Affiche l'image courante
    function showImage(i) {
    images.forEach(img => img.classList.remove("active"));
    images[i].classList.add("active");
    }

// Clic flèche droite
    nextBtn.addEventListener("click", () => {
        index++;
        if (index >= images.length) {
        index = 0;
        }
        showImage(index);
    });

// Clic flèche gauche
    prevBtn.addEventListener("click", () => {
        index--;
        if (index < 0) {
        index = images.length - 1;
        }
     showImage(index);
    });



    </script>

</div>


</div>


<footer>
    <div class="footer-content">


    <div class="footer-links">
      <a href="#">Mon compte</a>
      <button id="MyAccount"href="#MyAccount">S'inscrire</button>
      <a href="#">À propos de nous</a>
    </div>

    <p>© 2025 Tryhard — Tous droits réservés</p>
  </div>
</footer>

</body>
</html>
