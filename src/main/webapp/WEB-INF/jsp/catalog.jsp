<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">

    <div id ="search_filters">
        <p style="margin-left: 10px; ">Filtrer par</p>
        <section>
            <p style="margin-left: 25px; ">Categories</p>
            <ul id="checkboxChoices" style="list-style: none;">
                <c:forEach var="category" items="${categories}">
                    <li style="margin-bottom: 8px">
                        <input type="checkbox" id="${category.id}" value="${category.id}">
                        <label for="${category.id}">${category.nameFr}</label>

                    </li>
                </c:forEach>
            </ul>

        </section>

    </div>
    <div id="productsContainer">
        <c:forEach var="product" items="${products}">
            <form action="${pageContext.request.contextPath}/cart/${product.id}" method="get">
            <div  class="card" onclick="window.location.href='${pageContext.request.contextPath}/cart'">
                    <img src="${pageContext.request.contextPath}/images/${product.imageUrl}" alt="${product.nameEn}" width=200 height=220>
                    <h2>${product.nameFr}</h2>
                    <p>Categorie: ${product.category.nameEn} (${product.category.nameFr})</p>
                    <p>${product.price} €</p>
                    <button>Ajouter au panier</button>
            </div>
            </form>
        </c:forEach>
    </div>
</div>