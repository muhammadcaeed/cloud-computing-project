function login() {
    let $form = $('#loginForm');
    if (!$form.valid()) return false;
    let $loginMsg = $('#loginMsg');
    $.ajax({
        url: $('#contextPath').val() + "/j_spring_security_check",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("X-Ajax-call",
                "true");
            $.LoadingOverlay("show");
        }, complete: function () {
            $.LoadingOverlay("hide");
        },
        data: {j_username: $('#username').val(), j_password: $('#pswd').val()},
        type: "POST",
        success: function (result) {
            let json = JSON.parse(result);
            if (json.status === 200) {
                status = true;
                $loginMsg.css("display", "none");
                $('#loginModal').remove();
                $('.modal-backdrop').remove();
                toastr.success("Login Successful.");
            } else {
                // $('#myModal').effect("shake");
                $loginMsg.css({
                    "display": "block",
                    "color": "rgb(255, 64, 55)",
                    "background - color": "#FFBABA"
                });
                $loginMsg.html(
                    "Username and Password do not match.");
                status = false;
            }
            location.reload();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

            // $('#myModal').effect("shake");
            $loginMsg.css({
                "display": "block",
                "color": "#D8000C",
                "background - color": "#FFBABA",
                "font-weight": "bold"
            });

            $loginMsg.html("Something went wrong while signing in.");
            status = false;
        }

    });
}

function registerUser() {
    let $form = $('#registerForm');
    if (!$form.validate({
        rules: {
            registerUserName: {
                required: true,
                email: true
            },
            registerPassword: {
                required: true,
                minlength: 6
            },
            registerConfirmPassword: {
                required: true,
                minlength: 6,
                equalTo: "#registerPassword"
            },
            registerFirstName: {
                required: true
            },
            registerLastName: {
                required: true
            },
            registerPhoneNo: {
                required: true,
                maxlength: 15
            },
            registerAddress: {
                required: true
            },
            registerAlias: {
                required: true
            },
            registerCountry: {
                required: true
            },
            registerState: {
                required: true
            },
            registerCity: {
                required: true
            },
            registerPostCode: {
                required: true
            }
        }
    })) {
        return false;
    } else {
        let $data = $form.serializeJSON();
        $.ajax({
            beforeSend: function () {
                $.LoadingOverlay("show")
            }, complete: function () {
                $.LoadingOverlay("hide");
            },
            url: $('#contextPath').val() + "/user/register",
            data: $data,
            type: "POST",
            dataType: 'json',
            success: function (result) {
                console.log("THe result is " + result);
                //var json = JSON.parse(result);
                if (result.status === 200) {
                    $('#registerModal').remove();
                    $('.modal-backdrop').remove();
                    toastr.success("Registration Successful.");
                } else {
                    // $('#myModal').effect("shake");
                    $('#registerModal').remove();
                    $('.modal-backdrop').remove();
                    toast.error("Error registering user. Please try again later");
                }
                return false;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                toastr.error("Error registering user.");
                $('#registerModal').remove();
                $('.modal-backdrop').remove();
                return false;
            }
        });
    }
}