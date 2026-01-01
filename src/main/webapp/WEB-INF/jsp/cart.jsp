<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="locale" value="${param.locale != null ? param.locale : 'fr'}" />
<h1><spring:message code="cart"/></h1>


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
            <th><spring:message code="actions"/></th>
            <th><spring:message code="total"/></th>
        </tr>
    </thead>

    <tbody>
        <c:forEach var="product" items="${products}">
            <c:set var="promoPrice" value="${promoPrices[product.id]}" />

            <tr class="cart-row" id="product-${product.id}">
                <td class="cart-image">
                    <img src="${pageContext.request.contextPath}/images/${product.imageUrl}"
                         alt="${product.nameEn}" width="100"/>
                </td>
                <td class="cart-product">
                    <c:choose>
                        <c:when test="${locale='fr'}">
                            <strong>${product.nameFr}</strong>
                            <br>${product.descriptionFr}<br/>
                            (${product.category.nameFr})
                        </c:when>
                        <c:otherwise>
                            <strong>${product.nameEn}</strong>
                            <br>${product.descriptionEn}<br/>
                            ${product.category.nameEn}
                        </c:otherwise>
                    </c:choose>
                    <spring:message code="categories"/>:
                </td>
                <td class="cart-price">
                    <c:choose>
                        <c:when test="${promoPrice < product.price}">
                            <span style="text-decoration: line-through; color:#999;">${product.price} €</span>
                            <span style="color:red; font-weight:bold; margin-left:8px;">${promoPrice} €</span>
                        </c:when>
                        <c:otherwise>
                            ${product.price} €
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="cart-stock">${product.stock}</td>
                <td class="cart-quantity">
                    <form class="update-form" action="${pageContext.request.contextPath}/cart/update/${product.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <select name="quantity" class="quantity-select">
                            <c:forEach begin="0" end="${product.stock}" var="i">
                                <option value="${i}" <c:if test="${i == quantities[product.id]}">selected</c:if>>${i}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="update-btn"><spring:message code="updateForm"/></button>
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
                        <button type="submit" class="delete-btn"><spring:message code="delete"/></button>
                    </form>
                </td>
                <td class="cart-total">
                    ${promoPrice * quantities[product.id]} €
                </td>
            </tr>
        </c:forEach>
    </tbody>

    <tfoot>
        <tr>
            <td colspan="6" style="text-align: right;">
                <strong><spring:message code="total"/> <spring:message code="totalCartText"/>:</strong>
            </td>
            <td><strong>${cartTotal} €</strong></td>
        </tr>
    </tfoot>
</table>


<form id="order-btn-form" action="${pageContext.request.contextPath}/order" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" id="order-btn" title=""><spring:message code="placeOrder"/></button>
</form>

<script src="<spring:url value='/js/confirmAlert.js' />"></script>

<c:if test="${not empty errorMessage}">
    <div class="emptyCartError">
        <c:out value="${errorMessage}" />
    </div>
</c:if>


<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">
        <spring:message code="${errorMessage}" />
    </div>
</c:if>



