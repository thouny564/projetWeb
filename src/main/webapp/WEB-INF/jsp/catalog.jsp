<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul>
    <c:forEach var="category" items="${categories}">
        <li>
            ${category.nameEn} (${category.nameFr})<br/>
            ${category.descriptionEn}<br/>
            ${category.descriptionFr}
        </li>
    </c:forEach>
</ul>

<h2>Produits</h2>
<ul>
    <c:forEach var="product" items="${products}">
        <li>
            <strong>${product.nameEn} (${product.nameFr})</strong><br/>
            ${product.descriptionEn}<br/>
            ${product.descriptionFr}<br/>
            Prix: ${product.price} €<br/>
            Stock: ${product.stock}<br/>
            <img src="${pageContext.request.contextPath}/images/${product.imageUrl}" alt="${product.nameEn}" width="100"/><br/>
            Catégorie: ${product.category.nameEn} (${product.category.nameFr})<br/>
        </li>
    </c:forEach>
</ul>