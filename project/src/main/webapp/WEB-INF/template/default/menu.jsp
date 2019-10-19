<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="site-navbar" role="banner">
    <div class="site-navbar-top">
        <div class="container">
            <nav class="site-navigation text-right text-md-center" role="navigation">

                <div class="row align-items-center">

                    <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
                        <div class="site-block-top-search">
                            <span class="icon icon-search2"></span>
                            <form id="search-form" action="${pageContext.request.contextPath}/product/search">
                                <input type="text" name="search-item" id="search-item" class="form-control border-0"
                                       placeholder="Search">
                            </form>

                        </div>
                    </div>

                    <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
                        <div class="site-logo">
                            <a href="${pageContext.request.contextPath}/home" class="js-logo-clone">Online-Kaufen</a>
                        </div>
                    </div>

                    <div class="col-6 col-md-4 order-3 order-md-3 text-right">
                        <div class="site-top-icons">
                            <ul class="site-menu js-clone-nav d-none d-md-block">
                                <li class="has-children">
                                    <a href="#"><span class="icon icon-person"></span></a>
                                    <ul class="dropdown">
                                        <sec:authorize access="isAnonymous()">
                                            <li><a data-toggle="modal" data-target="#loginModal">Login</a></li>
                                            <li><a data-toggle="modal" data-target="#registerModal">Register</a></li>
                                        </sec:authorize>
                                        <sec:authorize access="isAuthenticated()">
                                            <li><a href="${pageContext.request.contextPath}/user/profile">Profile</a>
                                            </li>
                                            <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
                                        </sec:authorize>

                                    </ul>
                                </li>
                                <%--<li><a class="icon icon-person" data-toggle="modal" data-target="#loginModal"></a></li>--%>
                                <%--<li><a href="#"><span class="icon icon-heart-o"></span></a></li>--%>
                                <li>
                                    <a href="${pageContext.request.contextPath}/cart/show" class="site-cart">
                                        <span class="icon icon-shopping_cart"></span>
                                        <span class="count">
                                        <c:choose>
                                            <c:when test="${sessionScope.get('cartItems') == null}">
                                                0
                                            </c:when>
                                            <c:otherwise>
                                                ${sessionScope.get('cartItems')}
                                            </c:otherwise>
                                        </c:choose>
                                    </span>
                                    </a>
                                </li>
                                <li class="d-inline-block d-md-none ml-md-0"><a href="#"
                                                                                class="site-menu-toggle js-menu-toggle"><span
                                        class="icon-menu"></span></a></li>
                            </ul>
                        </div>
                    </div>

                </div>

            </nav>
        </div>
    </div>
    <nav class="site-navigation text-right text-md-center" role="navigation">
        <div class="container">
            <ul class="site-menu js-clone-nav d-none d-md-block">
                <li>
                    <a href="${pageContext.request.contextPath}/home">Home</a>
                </li>

                <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>

                <%--<li><a href="#">Catalogue</a></li>
                <li><a href="#">New Arrivals</a></li>
                <li><a href="contact.html">Contact</a></li>--%>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="${pageContext.request.contextPath}/purchaseHistory">Purchase History</a></li>
                    <li><a href="${pageContext.request.contextPath}/salesHistory">Sales History</a></li>
                    <li><a href="${pageContext.request.contextPath}/product/myProducts">My Products</a></li>
                    <li><a href="${pageContext.request.contextPath}/product/productUpload">Upload New Product</a></li>
                </sec:authorize>


            </ul>
        </div>
    </nav>
</header>