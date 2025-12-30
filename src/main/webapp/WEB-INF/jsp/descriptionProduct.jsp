<div class="product-container">


    <div class="product-left">
        <img src="${pageContext.request.contextPath}/images/${product.imageUrl}" alt="Nom du produit" >
        <p id="description">
            Voici la description du produit.
            Ce jeu propose une expérience immersive avec des graphismes exceptionnels,
            une histoire captivante et un gameplay intense.
        </p>
    </div>

    <div class="product-right">
        <h2>FF7 Rebirth ps5</h2>
        <p style="border-top: 2px solid#eee; padding-top: 10px;">Catégories : aventure, combat</p>
        <p class="price">59,99 €</p>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <select name="quantity" class="quantity-select">
            <c:if test="${product.stock > 0}">
                <c:forEach begin="1" end="${product.stock}" var="i">
                    <option value="${i}" ${i == 1 ? 'selected' : ''}>
                            ${i}
                    </option>
                </c:forEach>
            </c:if>
        </select>

        <div id="containerButton">
            <button class="buy-now">Acheter maintenant</button>
            <button class="add-cart">Ajouter au panier</button>
        </div>
        <p>Livraison
            - Offerte à partir de 39€
            - Livraison dans toute l'Europe
            - Traitement en 24h ouvrables</p>

    </div>

</div>
