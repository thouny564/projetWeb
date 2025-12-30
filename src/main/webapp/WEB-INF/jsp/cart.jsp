<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Mon Panier</h1>

<table id="cart-table" class="cart-table">
    <thead>
        <tr>
            <th>Image</th>
            <th>Produit</th>
            <th>Prix</th>
            <th>Stock</th>
            <th>Quantité</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${products}">
            <tr class="cart-row" id="product-${product.id}">
                <td class="cart-image">
                    <img src="${pageContext.request.contextPath}/images/${product.imageUrl}" alt="${product.nameEn}" width="100"/>
                </td>
                <td class="cart-product">
                    <strong>${product.nameEn} (${product.nameFr})</strong><br/>
                    ${product.descriptionEn}<br/>
                    ${product.descriptionFr}<br/>
                    Catégorie: ${product.category.nameEn} (${product.category.nameFr})
                </td>
                <td class="cart-price">${product.price} €</td>
                <td class="cart-stock">${product.stock}</td>
                <td class="cart-quantity">
                    <!-- Formulaire select pour mettre à jour la quantité -->
                    <form class="update-form" action="${pageContext.request.contextPath}/cart/update/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <select name="quantity" class="quantity-select">
                            <c:forEach begin="0" end="${product.stock}" var="i">
                                <option value="${i}" <c:if test="${i == quantities[product.id]}">selected</c:if>>${i}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="update-btn">Mettre à jour</button>
                    </form>
                </td>
                <td class="cart-actions">


                    <!-- Ajouter 1 -->
                    <form class="add-form" action="${pageContext.request.contextPath}/cart/add/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="add-btn">+1</button>
                    </form>

                    <!-- Retirer 1 -->
                    <form class="remove-form" action="${pageContext.request.contextPath}/cart/remove/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="remove-btn">-1</button>
                    </form>

                    <!-- Supprimer -->
                    <form class="delete-form" action="${pageContext.request.contextPath}/cart/delete/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="delete-btn">Supprimer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>



<!-- Formulaire de test avec produit ID 1 --> <form action="${pageContext.request.contextPath}/cart/add/1" method="post"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <button type="submit">Ajouter produit ID 1 au panier</button> </form>


<!-- Formulaire de test pour retirer produit ID 1 -->
<form action="${pageContext.request.contextPath}/cart/remove/1" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Retirer produit ID 1 du panier</button>
</form>
