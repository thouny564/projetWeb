<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="locale" value="${param.locale != null ? param.locale : 'fr'}" />


<div class="product-container">


    <div class="product-left">
        <img src="${pageContext.request.contextPath}/images/${product.imageUrl}" alt="Nom du produit" >
        <p id="description">

            <c:choose>
                <c:when test="${locale== 'fr'}">
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
                <c:when test="${locale== 'fr'}">
                    ${product.nameFr}
                </c:when>

                <c:otherwise>
                    ${product.nameEn}
                </c:otherwise>

            </c:choose>

        </h2>
        <p style="border-top: 2px solid#eee; padding-top: 10px;"><spring:message code="categories"/>:

            <c:choose>
                <c:when test="${locale== 'fr'}">
                    ${product.category.nameFr}
                </c:when>

                <c:otherwise>
                    ${product.category.nameEn}
                </c:otherwise>

            </c:choose>

        </p>
        <p class="price">${product.price} €</p>

        <form method="post" action="${pageContext.request.contextPath}/cart/addCustomQuantity/${product.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div id="container-quantityStock">
                <select name="quantity" class="quantity-select">
                    <c:forEach begin="1" end="${product.stock}" var="i">
                        <option value="${i}" ${i == 1 ? "selected" : ""}>${i}</option>
                    </c:forEach>
                </select>
                <span>${product.stock} <spring:message code="stockIndicator"/></span>
            </div>

            <div id="containerButton">
                <button type="submit" class="add-cart">
                    <spring:message code="addCartButton"/>
                </button>
            </div>
        </form>


        <p><spring:message code="delivery"/></p>

    </div>

</div>
