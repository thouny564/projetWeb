<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">

    <!-- Filtre par catégorie -->
    <div id="search_filters">
        <p style="margin-left: 10px;"><spring:message code="filterTextContainer"/></p>
        <section>
            <p style="margin-left: 25px;">
                <spring:message code="categories"/>
                <span style="font-size: 10px">
                    <sup>*<spring:message code="warningMessageFilter"/></sup>
                </span>
            </p>

            <form method="post" action="${pageContext.request.contextPath}/product/category">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <ul id="categoryChoices" style="list-style: none;">
                    <c:forEach var="category" items="${categories}">
                        <li style="margin-bottom: 8px">
                            <input type="radio" name="categoryId" value="${category.id}" id="cat${category.id}"
                                <c:if test="${category.id == selectedCategoryId}">checked</c:if> >

                            <c:choose>
                                <c:when test="${lang =='fr'}">
                                    <label for="cat${category.id}">${category.nameFr}</label>
                                </c:when>
                                <c:otherwise>
                                    <label for="cat${category.id}">${category.nameEn}</label>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
                <button type="submit">Filtrer</button>
            </form>

        </section>
    </div>

    <!-- Liste des produits -->
    <div id="productsContainer">
        <c:forEach var="product" items="${products}">
            <div class="card">

                <!-- Lien vers la fiche produit -->
                <a style="text-decoration: none"
                   href="${pageContext.request.contextPath}/product/${product.id}">
                    <img src="${pageContext.request.contextPath}/images/${product.imageUrl}"
                         alt="${product.nameEn}" width="180" height="220">

                    <c:choose>
                        <c:when test="${lang=='fr'}">
                            <h2>${product.nameFr}</h2>
                            <p>
                                <spring:message code="categoryText"/> :
                                ${product.category.nameEn} (${product.category.nameFr})
                            </p>
                        </c:when>
                        <c:otherwise>
                            <h2>${product.nameEn}</h2>
                            <p>
                                <spring:message code="categoryText"/> :
                                ${product.category.nameEn} (${product.category.nameEn})
                            </p>
                        </c:otherwise>
                    </c:choose>

                    <p>
                        <c:choose>

                            <c:when test="${promoPrices[product.id] < product.price}">
                                <span style="text-decoration: line-through; color: gray;">
                                    ${product.price} €
                                </span>
                                <span style="color: red; font-weight: bold; margin-left: 5px;">
                                    ${promoPrices[product.id]} €
                                </span>
                            </c:when>


                            <c:otherwise>
                                ${product.price} €
                            </c:otherwise>
                        </c:choose>
                    </p>

                </a>

                <!-- Bouton ajouter au panier -->
                <form method="post" action="${pageContext.request.contextPath}/cart/add/${product.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button type="submit">
                        <spring:message code="addCartButton"/>
                    </button>
                </form>

            </div>
        </c:forEach>
    </div>
</div>
