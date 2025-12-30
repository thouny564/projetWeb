<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul>
    <c:forEach var="category" items="${categories}">
        <li>
            <c:choose>
                <c:when test="${lang == 'fr'}">
                    ${category.nameFr}<br/>
                    ${category.descriptionFr}
                </c:when>
                <c:otherwise>
                    ${category.nameEn}<br/>
                    ${category.descriptionEn}
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>

<h2>Produits</h2>
<ul>
    <c:forEach var="product" items="${products}">
        <li>
            <strong>
                <c:choose>
                    <c:when test="${lang == 'fr'}">${product.nameFr}</c:when>
                    <c:otherwise>${product.nameEn}</c:otherwise>
                </c:choose>
            </strong><br/>
            <c:choose>
                <c:when test="${lang == 'fr'}">${product.descriptionFr}</c:when>
                <c:otherwise>${product.descriptionEn}</c:otherwise>
            </c:choose><br/>
            Prix: ${product.price} €<br/>
            Stock: ${product.stock}<br/>
            <img src="${pageContext.request.contextPath}/images/${product.imageUrl}" alt="${product.nameEn}" width="100"/><br/>
            Catégorie:
            <c:choose>
                <c:when test="${lang == 'fr'}">${product.category.nameFr}</c:when>
                <c:otherwise>${product.category.nameEn}</c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>
