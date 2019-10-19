<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="site-section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="h3 mb-3 text-black">Upload your product.</h2>
            </div>
            <div class="col-md-10">
                <form id="product_upload_form" enctype="multipart/form-data">
                    <div class="p-3 p-lg-5 border">
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label class="text-black" for="product_name">Product Name <span
                                        class="text-danger">*</span></label>
                                <input id="product_name" name="product_name" placeholder="Product Name"
                                       class="form-control"
                                       required type="text">

                            </div>
                            <div class="col-md-6">
                                <label class="text-black" for="product_slug">Slug <span
                                        class="text-danger">*</span></label>
                                <input id="product_slug" name="product_slug" placeholder="Slug" class="form-control"
                                       required
                                       type="text">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-6">
                                <label class="text-black" for="product_description">Product Description
                                    <span class="text-danger">*</span></label>
                                <textarea name="product_description" id="product_description" cols="30" rows="7"
                                          class="form-control"></textarea>
                            </div>
                            <div class="col-md-6">
                                <label class="text-black" for="product_category">Product Category<span
                                        class="text-danger">*</span></label>
                                <select id="product_category" name="product_category" class="form-control">
                                    <%--@elvariable id="categories" type="java.util.List<com.onlinekaufen.springframework.dto.CategoriesDTO>"--%>
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.categoryId}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-6">
                                <label class="text-black" for="available_quantity">Available Quantity<span
                                        class="text-danger">*</span></label>
                                <input id="available_quantity" name="available_quantity" placeholder="Quantity"
                                       class="form-control" required type="number">

                            </div>
                            <div class="col-md-6">
                                <label class="text-black" for="product_price">Price<span
                                        class="text-danger">*</span></label>
                                <input id="product_price" name="product_price" placeholder="Price" class="form-control"
                                       type="number">

                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-6">
                                <label class="text-black" for="product_type">Product Type<span
                                        class="text-danger">*</span></label>
                                <select id="product_type" name="product_type" class="form-control">
                                    <option value="1">Physical</option>
                                    <option value="2">Service</option>
                                    <option value="3">Digital</option>
                                </select>

                            </div>
                            <div class="col-md-6">
                                <label class="text-black" for="product_condition">Product Condition<span
                                        class="text-danger">*</span></label>
                                <select id="product_condition" name="product_condition" class="form-control">
                                    <option value="1">New</option>
                                    <option value="2">Refurbished</option>
                                    <option value="3">Little Use</option>
                                    <option value="4">Used</option>
                                    <option value="5">Worn out</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-12">
                                <input name="product_image" id="product_image" class="btn btn-default btn-primary"
                                       type="file"/>
                            </div>
                        </div>
                        <input class="btn btn-primary btn-lg btn-block" type="button" onclick="uploadProduct();"
                               value="Upload">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>