<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="product-container">

    <div class="product-left">
        <img src="${pageContext.request.contextPath}/images/${product.imageUrl}"
             alt="${product.nameEn}">

        <p id="description">
            <c:choose>
                <c:when test="${lang == 'fr'}">
                    ${product.descriptionFr}
                </c:when>
                <c:otherwise>
                    ${product.descriptionEn}
                </c:otherwise>
            </c:choose>
        </p>
    </div>

    <div class="product-right">
        <h2>
            <c:choose>
                <c:when test="${lang == 'fr'}">
                    ${product.nameFr}
                </c:when>
                <c:otherwise>
                    ${product.nameEn}
                </c:otherwise>
            </c:choose>
        </h2>

        <p style="border-top: 2px solid #eee; padding-top: 10px;">
            <spring:message code="categories"/> :
            <c:choose>
                <c:when test="${lang == 'fr'}">
                    ${product.category.nameFr}
                </c:when>
                <c:otherwise>
                    ${product.category.nameEn}
                </c:otherwise>
            </c:choose>
        </p>

        <!-- PRIX -->
        <c:choose>
            <c:when test="${promoPrice < product.price}">
                <p class="price">
                    <span style="text-decoration: line-through; color: #999;">
                        ${product.price} €
                    </span>
                    <span style="color: red; font-weight: bold; margin-left: 8px;">
                        ${promoPrice} €
                    </span>
                </p>
            </c:when>

            <c:otherwise>
                <p class="price">
                    ${product.price} €
                </p>
            </c:otherwise>
        </c:choose>

        <!-- AJOUT PANIER -->
        <form method="post"
              action="${pageContext.request.contextPath}/cart/addCustomQuantity/${product.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div id="container-quantityStock">
                <select name="quantity" class="quantity-select">
                    <c:forEach begin="1" end="${product.stock}" var="i">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
                <span>${product.stock} en stock</span>
            </div>

            <button type="submit" class="add-cart">
                <spring:message code="addCartButton"/>
            </button>
        </form>

        <p><spring:message code="delivery"/></p>
    </div>

</div>
