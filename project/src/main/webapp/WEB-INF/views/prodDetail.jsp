<%--@elvariable id="product" type="com.onlinekaufen.springframework.dto.ProductsDTO"--%>
<input type="hidden" id="prod-id" value="${product.id}"/>
<div class="site-section">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img src="${product.imgPath}" alt="Image"
                     class="img-fluid">
            </div>
            <div class="col-md-6">
                <h2 class="text-black">${product.productName}</h2>
                <p>${product.description}</p>
                <p><strong class="text-primary h4">&euro; ${product.price}</strong></p>
                <%--<div class="mb-1 d-flex">
                    <label for="option-sm" class="d-flex mr-3 mb-3">
                        <span class="d-inline-block mr-2" style="top:-2px; position: relative;">
                            <input type="radio" id="option-sm" name="shop-sizes"></span>
                        <span class="d-inline-block text-black">Small</span>
                    </label>
                    <label for="option-md" class="d-flex mr-3 mb-3">
                        <span class="d-inline-block mr-2" style="top:-2px; position: relative;">
                            <input type="radio" id="option-md" name="shop-sizes"></span>
                        <span class="d-inline-block text-black">Medium</span>
                    </label>
                    <label for="option-lg" class="d-flex mr-3 mb-3">
                        <span class="d-inline-block mr-2" style="top:-2px; position: relative;">
                            <input type="radio" id="option-lg" name="shop-sizes"></span>
                        <span class="d-inline-block text-black">Large</span>
                    </label>
                    <label for="option-xl" class="d-flex mr-3 mb-3">
                        <span class="d-inline-block mr-2" style="top:-2px; position: relative;">
                            <input type="radio" id="option-xl" name="shop-sizes"></span>
                        <span class="d-inline-block text-black"> Extra Large</span>
                    </label>
                </div>--%>
                <div class="mb-5">
                    <div class="input-group mb-3" style="max-width: 120px;">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary js-btn-minus" type="button">&minus;</button>
                        </div>
                        <input type="number" class="form-control text-center" value="1" placeholder="" id="prod-qty"
                               max="${product.stock}"
                               name="prod-qty"
                               aria-label="Example text with button addon" aria-describedby="button-addon1">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
                        </div>
                    </div>
                </div>
                <p><a href="#" onclick="addToCart()" class="buy-now btn btn-sm btn-primary">Add To Cart</a></p>
            </div>
        </div>
    </div>
</div>