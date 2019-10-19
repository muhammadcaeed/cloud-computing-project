<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="site-section">
    <div class="container">
        <div class="row mb-5">
            <form class="col-md-12" method="post">
                <div class="site-blocks-table">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th class="product-thumbnail">Image</th>
                            <th class="product-name">Product</th>
                            <th class="product-price">Price</th>
                            <th class="product-quantity">Quantity</th>
                            <th class="product-total">Total</th>
                            <th class="product-remove">Remove</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="total" value="${0}"/>
                        <%--@elvariable id="cartItems" type="java.util.List<com.onlinekaufen.springframework.dto.BuyingProductsDTO>"--%>
                        <c:forEach items="${cartItems}" var="item">

                            <c:set var="total" value="${total + (item.price * item.qty)}"/>
                            <tr>
                                <td class="product-thumbnail">
                                    <img src="${item.imgPath}"
                                         alt="Image" class="img-fluid">
                                </td>
                                <td class="product-name">
                                    <h2 class="h5 text-black">${item.productName}</h2>
                                </td>
                                <td>&euro; ${item.price}</td>
                                <td>
                                    <div class="input-group mb-3" style="max-width: 120px;">
                                        <div class="input-group-prepend">
                                            <button class="btn btn-outline-primary js-btn-minus" type="button">&minus;
                                            </button>
                                        </div>
                                        <input type="number" class="form-control text-center" value="${item.qty}"
                                               placeholder=""
                                               aria-label="Example text with button addon" max="${item.stock}"
                                               aria-describedby="button-addon1">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-primary js-btn-plus" type="button">&plus;
                                            </button>
                                        </div>
                                    </div>
                                </td>
                                <td>&euro; <fmt:formatNumber type="number" minFractionDigits="2"
                                                             value="${item.price * item.qty}"/></td>
                                <td><a href="#" onclick="removeItemFromCart(${item.id})" class="btn btn-primary btn-sm">X</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="row mb-5">
                    <div class="col-md-6 mb-3 mb-md-0">
                        <button class="btn btn-primary btn-sm btn-block">Update Cart</button>
                    </div>
                    <div class="col-md-6">
                        <a href="${pageContext.request.contextPath}/home"
                           class="btn btn-outline-primary btn-sm btn-block">Continue Shopping</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6 pl-5">
                <div class="row justify-content-end">
                    <div class="col-md-7">
                        <div class="row">
                            <div class="col-md-12 text-right border-bottom mb-5">
                                <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
                            </div>
                        </div>

                        <div class="row mb-5">
                            <div class="col-md-6">
                                <span class="text-black">Total</span>
                            </div>
                            <div class="col-md-6 text-right">
                                <strong class="text-black">&euro; <fmt:formatNumber type="number" minFractionDigits="2"
                                                                                    value="${total}"/></strong>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <a class="btn btn-primary btn-lg py-3 btn-block"
                                   href="${pageContext.request.contextPath}/cart/checkout">Proceed To Checkout
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>