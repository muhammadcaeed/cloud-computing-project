<%--@elvariable id="total" type="java.lang.Float"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="site-section">
    <div class="container">

        <div class="row">
            <div class="col-md-9">

                <div class="row mb-5">
                    <div class="col-md-12">
                        <h2 class="h3 mb-3 text-black">Your Order History</h2>
                        <div class="p-3 p-lg-5 border">
                            <table class="table site-block-order-table mb-5">
                                <thead>
                                <tr>
                                    <th>Product Name</th>
                                    <th>Product Price</th>
                                    <th>Quantity</th>
                                    <th>Date</th>
                                    <th>Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--@elvariable id="sales" type="java.util.List<com.onlinekaufen.springframework.dto.SoldItemsDTO>"--%>
                                <c:forEach items="${sales}" var="item">
                                    <tr>
                                        <td>${item.productName}</td>
                                        <td>&euro; <fmt:formatNumber type="number" minFractionDigits="2"
                                                                     value="${item.price}"/></td>
                                        <td>${item.quantity}</td>
                                        <td>&euro; <fmt:formatNumber type="number" minFractionDigits="2"
                                                                     value="${item.price * item.quantity}"/></td>
                                        <td>${item.purchaseDate}</td>
                                    </tr>
                                </c:forEach>
                                <tr>

                                </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- </form> -->
    </div>
</div>