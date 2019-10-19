<style>
    .modal-header, h4, .close {
        background-color: #7971ea;
        color: white !important;
        text-align: center;
        font-size: 30px;
    }

    .modal-footer {
        background-color: #f9f9f9;
    }
</style>

<div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding:35px 50px;">
                <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body" style="padding:40px 50px;">
                <form role="form" id="loginForm">
                    <div class="form-group">
                        <label for="username"><span class="glyphicon glyphicon-user"></span> Username</label>
                        <input type="email" class="form-control" name="username" id="username" placeholder="Enter email"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="pswd"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                        <input type="password" minlength="6" class="form-control" name="pswd" id="pswd"
                               placeholder="Enter password"
                               required>
                    </div>
                    <%--<div class="checkbox">
                        <label><input type="checkbox" value="" checked>Remember me</label>
                    </div>--%>
                    <button type="button" onclick="login();" class="btn btn-success btn-block"><span
                            class="glyphicon glyphicon-off"></span> Login
                    </button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-danger btn-default pull-left" onclick="$('#loginMsg').html('')"
                        data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel
                </button>
                <p>Not a member? <a href="#" data-toggle="modal" data-dismiss="#loginModal"
                                    data-target="#registerModal">Sign
                    Up</a></p>
                <%--<p>Forgot <a href="#">Password?</a></p>--%>
            </div>
            <div id="loginMsg"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="registerModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding:35px 50px;">
                <h4><span class="glyphicon glyphicon-lock"></span> Register</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>
            <div class="modal-body" style="padding:40px 50px;">
                <form role="form" id="registerForm" method="post"
                      action="javascript:registerUser()">
                    <div class="form-group">
                        <label for="registerUserName"><span class="glyphicon glyphicon-user"></span> Username</label>
                        <input type="email" class="form-control" name="registerUserName" id="registerUserName"
                               placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <label for="registerPassword"><span class="glyphicon glyphicon-eye-open"></span>
                            Password</label>
                        <input type="password" minlength="6" class="form-control" name="registerPassword"
                               id="registerPassword"
                               placeholder="Password" required>
                    </div>
                    <div class="form-group">
                        <label for="registerConfirmPassword"><span class="glyphicon glyphicon-eye-open"></span>Confirm
                            Password</label>
                        <input type="password" class="form-control" name="registerConfirmPassword" minlength="6"
                               id="registerConfirmPassword" placeholder="Confirm Password(same as above)" required>
                    </div>
                    <div class="form-group">
                        <label for="registerFirstName"><span class="glyphicon glyphicon-eye-open"></span>
                            First Name</label>
                        <input type="text" class="form-control" name="registerFirstName"
                               id="registerFirstName" placeholder="First name" required>
                    </div>
                    <div class="form-group">
                        <label for="registerLastName"><span class="glyphicon glyphicon-eye-open"></span>
                            Last Name</label>
                        <input type="text" class="form-control" name="registerLastName"
                               id="registerLastName" placeholder="Last Name" required>
                    </div>
                    <div class="form-group">
                        <label for="registerPhoneNo"><span class="glyphicon glyphicon-eye-open"></span> Phone
                            No.</label>
                        <input type="text" class="form-control" name="registerPhoneNo"
                               id="registerPhoneNo" placeholder="Enter phone number" required>
                    </div>
                    <div class="form-group">
                        <label for="registerCountry"><span class="glyphicon glyphicon-eye-open"></span> Country</label>
                        <input type="text" class="form-control" name="registerCountry"
                               id="registerCountry" placeholder="Country" required>
                    </div>
                    <div class="form-group">
                        <label for="registerState"><span class="glyphicon glyphicon-eye-open"></span> State</label>
                        <input type="text" class="form-control" name="registerState"
                               id="registerState" placeholder="State" required>
                    </div>
                    <div class="form-group">
                        <label for="registerCity"><span class="glyphicon glyphicon-eye-open"></span> City</label>
                        <input type="text" class="form-control" name="registerCity"
                               id="registerCity" placeholder="City" required>
                    </div>

                    <div class="form-group">
                        <label for="registerPostCode"><span class="glyphicon glyphicon-eye-open"></span> Post
                            Code</label>
                        <input type="text" class="form-control" name="registerPostCode"
                               id="registerPostCode" placeholder="Post Code" required>
                    </div>

                    <div class="form-group">
                        <label for="registerAddress"><span class="glyphicon glyphicon-eye-open"></span> Address</label>
                        <input type="text" class="form-control" name="registerAddress"
                               id="registerAddress" placeholder="Address" required>
                    </div>
                    <div class="form-group">
                        <label for="registerAlias"><span class="glyphicon glyphicon-eye-open"></span> Alias</label>
                        <input type="text" class="form-control" name="registerAlias"
                               id="registerAlias" placeholder="Alias" required>
                    </div>

                    <button type="submit" class="btn btn-success btn-block"><span
                            class="glyphicon glyphicon-off"></span> Register
                    </button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-danger btn-default pull-left"
                        data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel
                </button>
            </div>
        </div>
    </div>
</div>