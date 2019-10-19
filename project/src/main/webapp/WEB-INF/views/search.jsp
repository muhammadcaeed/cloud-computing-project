<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="site-section site-blocks-2">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-7 site-section-heading text-center pt-4">
                <h2>Searched Products</h2>
            </div>
        </div>
        <div class="row" id="product-content">
            <%--@elvariable id="searchProducts" type="java.util.List<com.onlinekaufen.springframework.dto.ProductsDTO>"--%>
            <c:choose>
                <c:when test="${searchProducts.size() == 0}">
                    <div class="col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0 items-display" data-aos="fade"
                         data-aos-delay="">
                        <p>No search products</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="product" items="${searchProducts}">
                        <div class="col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0 items-display" data-aos="fade"
                             data-aos-delay="">
                            <a class="block-2-item"
                               href="${pageContext.request.contextPath}/product/detail/${product.id}">
                                <figure class="image">
                                    <img src="${product.imgPath}" alt=""
                                         class="img-fluid">
                                </figure>
                                <div class="text">
                                    <span class="text-uppercase">${product.productName}</span>
                                    <h3>${product.categoryName}</h3>
                                    <h6>&euro; ${product.price}</h6>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>

