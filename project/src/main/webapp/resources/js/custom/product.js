function buyProduct() {
    $.ajax({
        beforeSend: function () {
            $.LoadingOverlay("show");
        }, complete: function () {
            $.LoadingOverlay("hide");
        },
        url: $('#contextPath').val() + "/cart/buy",
        type: "GET",
        success: function (result) {
            if (result.status == 200) {
                toastr.success(result.message);
                window.location = $('#contextPath').val()
            } else {
                toastr.error(result.message);
            }

        }, error: function () {
            toastr.error("Error removing item from the basket. Please try again.");
        }
    });
}

function filterProduct(categoryId) {
    let urlParams = new URLSearchParams(window.location.search);
    urlParams.set('category', categoryId);
    window.location.search = urlParams.toString();
}

function sortProduct(sortBy, orderBy) {
    let urlParams = new URLSearchParams(window.location.search);
    urlParams.set('sortBy', sortBy);
    urlParams.set('orderBy', orderBy);
    window.location.search = urlParams.toString();
}

function uploadProduct() {
    let form = $('#product_upload_form')[0];
    let formData = new FormData(form);
    let productImg = $('input[name="product_image"]')[0].files[0];
    /*for (let pair of formData.entries()) {
        console.log(pair[0] + ', ' + pair[1]);
    }*/
    formData.append('productImg', productImg);
    $.ajax({
        beforeSend: function () {
            $.LoadingOverlay("show");
        }, complete: function () {
            $.LoadingOverlay("hide");
            $('#product_upload_form')[0].reset();
            console.log("The JSON data is : " + JSON.stringify(formData));
        },
        cache: false,
        processData: false,
        url: $('#contextPath').val() + "/product/productUpload",
        data: formData,
        type: "POST",
        contentType: false,
        success: function (result) {
            if (result.status == 200) {
                toastr.success(result.message);
                console.log("The data is : " + JSON.stringify(formData));
                //window.location = $('#contextPath').val()
            } else {
                toastr.error(result.message);
            }

        }, error: function () {
            toastr.error("Error uploading the product. Please try again.");
        }
    });
}

function removeItemFromCart(itemId) {
    $.ajax({
        url: $('#contextPath').val() + "/cart/removeItem/" + itemId,
        type: "GET",
        success: function (result) {
            location.reload();
        }, error: function () {
            toastr.error("Error removing item from the basket. Please try again.");
        }
    });
}

function addToCart() {
    $.ajax({
        beforeSend: function () {
            $.LoadingOverlay("show");
        }, complete: function () {
            $.LoadingOverlay("hide");
            $('#product_upload_form')[0].reset();
        },
        url: $('#contextPath').val() + "/cart/add/" + $('#prod-id').val() + "/" + $('#prod-qty').val(),
        type: "GET",
        success: function (result) {
            location.reload();
        }, error: function () {
            toastr.error("Error adding item to the basket. Please try again.");
        }
    });
}

$(document).ready(function () {
    $('#search-item').keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            $('#search-form').submit();
        }
    });

    /*$("#product_upload_form").on('submit', function (e) {

        //ajax call here
        let form = $('#product_upload_form')[0];
        let formData = new FormData(form);
        let productImg = $('input[name="product_image"]')[0].files[0];
        for (let pair of formData.entries()) {
            console.log(pair[0] + ', ' + pair[1]);
        }
        formData.append('productImg', productImg);
        $.ajax({
            beforeSend: function () {
                $.LoadingOverlay("show")
            }, complete: function () {
                $.LoadingOverlay("hide");
            },
            url: $('#contextPath').val() + "/product/productUpload",
            data: formData,
            type: "POST",
            success: function (result) {
                if (result.status == 200) {
                    toastr.success(result.message);
                    $('#product_upload_form').reset();
                    //window.location = $('#contextPath').val()
                } else {
                    toastr.error(result.message);
                }

            }, error: function () {
                toastr.error("Error uploading the product. Please try again.");
            }
        });
        //stop form submission
        e.preventDefault();
        return false;
    });*/
});