<%--@elvariable id="user" type="com.onlinekaufen.springframework.dto.UserDTO"--%>
<div class="site-section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="h3 mb-3 text-black">Update your profile.</h2>
            </div>
            <div class="col-md-10">

                <form action="${pageContext.request.contextPath}/user/updateProfile" method="post">

                    <div class="p-3 p-lg-5 border">
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="first_name" class="text-black">First Name <span
                                        class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="first_name" name="first_name"
                                       value="${user.firstName}" required/>
                            </div>
                            <div class="col-md-6">
                                <label for="last_name" class="text-black">Last Name <span
                                        class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="last_name" name="last_name"
                                       value="${user.lastName}" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="email_id" class="text-black">Email <span
                                        class="text-danger">*</span></label>
                                <input type="email" class="form-control" id="email_id" name="email_id"
                                       value="${user.emailId}"
                                       disabled/>
                            </div>
                            <div class="col-md-6">
                                <label for="phone_no" class="text-black">Phone No <span
                                        class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="phone_no" name="phone_no"
                                       value="${user.phoneNo}"
                                       required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="address" class="text-black">Address <span
                                        class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="address" name="address"
                                       value="${user.address}"/>
                            </div>
                            <div class="col-md-6">
                                <label for="city" class="text-black">City <span
                                        class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="city" name="city" value="${user.city}"
                                       required/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="state" class="text-black">State <span
                                        class="text-danger">*</span></label>
                                <input type="text" name="state" id="state" value="${user.state}"
                                       class="form-control" required/>
                            </div>
                            <div class="col-md-6">
                                <label for="country" class="text-black">Country <span
                                        class="text-danger">*</span></label>
                                <input name="country" id="country" value="${user.country}"
                                       class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="post_code" class="text-black">Post Code <span
                                        class="text-danger">*</span></label>
                                <input name="post_code" id="post_code" value="${user.postCode}"
                                       class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-lg-12">
                                <input type="submit" class="btn btn-primary btn-lg btn-block"
                                       value="Update Information">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>