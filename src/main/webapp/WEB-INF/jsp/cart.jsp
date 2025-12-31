<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1>Mon Panier</h1>

<!-- Message de confirmation commande -->
<c:if test="${not empty successMessage}">
    <div class="success">
        <c:out value="${successMessage}" />
    </div>
</c:if>

<table id="cart-table" class="cart-table">
    <thead>
        <tr>
            <th><spring:message code="picture"/></th>
            <th><spring:message code="product"/></th>
            <th><spring:message code="price"/></th>
            <th><spring:message code="stock"/></th>
            <th><spring:message code="quantity"/></th>
            <th>Actions</th>
            <th>Total</th>
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
                    Catégorie : ${product.category.nameEn} (${product.category.nameFr})
                </td>
                <td class="cart-price">${product.price} €</td>
                <td class="cart-stock">${product.stock}</td>
                <td class="cart-quantity">
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
                    <form class="add-form" action="${pageContext.request.contextPath}/cart/add/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="add-btn">+1</button>
                    </form>

                    <form class="remove-form" action="${pageContext.request.contextPath}/cart/remove/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="remove-btn">-1</button>
                    </form>

                    <form class="delete-form" action="${pageContext.request.contextPath}/cart/delete/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="delete-btn">Supprimer</button>
                    </form>
                </td>
                <td class="cart-total">${product.price * quantities[product.id]} €</td>
            </tr>
        </c:forEach>
    </tbody>

    <tfoot>
        <tr>
            <td colspan="6" style="text-align: right;">
                <strong>Total panier :</strong>
            </td>
            <td><strong>${cartTotal} €</strong></td>
        </tr>
    </tfoot>
</table>

<!-- Bouton Passer commande -->
<form id="order-btn-form" action="${pageContext.request.contextPath}/order" method="post" onsubmit="return confirm('Confirmer la commande ?');">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" id="order-btn">Passer commande</button>
</form>

<c:if test="${not empty errorMessage}">
    <div class="emptyCartError">
        <c:out value="${errorMessage}" />
    </div>
</c:if>





<!-- Ajouter le produit ID 1 au panier -->
<form action="${pageContext.request.contextPath}/cart/add/1" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Ajouter produit ID 1 au panier</button>
</form>

<!-- Retirer le produit ID 1 du panier -->
<form action="${pageContext.request.contextPath}/cart/remove/1" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Retirer produit ID 1 du panier</button>
</form>
