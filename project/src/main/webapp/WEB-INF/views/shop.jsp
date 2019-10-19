<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prashant
  Date: 12/12/2018
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>

<div class="site-section">
    <div class="container">

        <div class="row mb-5">
            <div class="col-md-9 order-2">

                <div class="row">
                    <div class="col-md-12 mb-5">
                        <div class="float-md-left mb-4"><h2 class="text-black h5">Shop All</h2></div>
                        <div class="d-flex">

                            <div class="dropdown mr-1 ml-md-auto btn-group">
                                <button type="button" class="btn btn-secondary btn-sm dropdown-toggle"
                                        id="dropdownMenuReference" data-toggle="dropdown">Reference
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                    <a class="dropdown-item" href="#">Relevance</a>
                                    <a class="dropdown-item" href="javascript:sortProduct('name', 'ASC')">Name, A to
                                        Z</a>
                                    <a class="dropdown-item" href="javascript:sortProduct('name', 'DESC')">Name, Z to
                                        A</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="javascript:sortProduct('price', 'ASC')">Price, low to
                                        high</a>
                                    <a class="dropdown-item" href="javascript:sortProduct('price', 'DESC')">Price, high
                                        to low</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mb-5">
                    <%--@elvariable id="products" type="java.util.List<com.onlinekaufen.springframework.dto.ProductsDTO>"--%>
                    <c:forEach var="product" items="${products}">
                        <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
                            <div class="block-4 text-center border">
                                <figure class="block-4-image">
                                    <a href="${pageContext.request.contextPath}/product/detail/${product.id}"><img
                                            src="${product.imgPath}" alt="Image placeholder"
                                            class="img-fluid"></a>
                                </figure>
                                <div class="block-4-text p-4">
                                    <h3>
                                        <a href="${pageContext.request.contextPath}/product/detail/${product.id}">${product.productName}</a>
                                    </h3>
                                    <p class="mb-0">${product.categoryName}</p>
                                    <p class="text-primary font-weight-bold">&euro; ${product.price}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <%--<div class="row" data-aos="fade-up">
                    <div class="col-md-12 text-center">
                        <div class="site-block-27">
                            <ul>
                                <li><a href="#">&lt;</a></li>
                                <li class="active"><span>1</span></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">&gt;</a></li>
                            </ul>
                        </div>
                    </div>
                </div>--%>
            </div>

            <div class="col-md-3 order-1 mb-5 mb-md-0">
                <div class="border p-4 rounded mb-4">
                    <h3 class="mb-3 h6 text-uppercase text-black d-block">Categories</h3>
                    <ul class="list-unstyled mb-0">
                        <%--@elvariable id="categoryCount" type="java.util.List<com.onlinekaufen.springframework.dto.CategoryCountDTO>"--%>
                        <c:forEach items="${categoryCount}" var="category">
                            <li class="mb-1"><a href="javascript:filterProduct(${category.categoryId})"
                                                class="d-flex"><span>${category.categoryName}</span>
                                <span
                                        class="text-black ml-auto">(${category.categoryCount})</span></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <%-- <div class="row">
             <div class="col-md-12">
                 <div class="site-section site-blocks-2">
                     <div class="row justify-content-center text-center mb-5">
                         <div class="col-md-7 site-section-heading pt-4">
                             <h2>Categories</h2>
                         </div>
                     </div>
                     <div class="row">
                         <div class="col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0" data-aos="fade" data-aos-delay="">
                             <a class="block-2-item" href="#">
                                 <figure class="image">
                                     <img src="images/women.jpg" alt="" class="img-fluid">
                                 </figure>
                                 <div class="text">
                                     <span class="text-uppercase">Collections</span>
                                     <h3>Women</h3>
                                 </div>
                             </a>
                         </div>
                         <div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0" data-aos="fade" data-aos-delay="100">
                             <a class="block-2-item" href="#">
                                 <figure class="image">
                                     <img src="images/children.jpg" alt="" class="img-fluid">
                                 </figure>
                                 <div class="text">
                                     <span class="text-uppercase">Collections</span>
                                     <h3>Children</h3>
                                 </div>
                             </a>
                         </div>
                         <div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0" data-aos="fade" data-aos-delay="200">
                             <a class="block-2-item" href="#">
                                 <figure class="image">
                                     <img src="images/men.jpg" alt="" class="img-fluid">
                                 </figure>
                                 <div class="text">
                                     <span class="text-uppercase">Collections</span>
                                     <h3>Men</h3>
                                 </div>
                             </a>
                         </div>
                     </div>

                 </div>
             </div>
         </div>--%>

    </div>
</div>
