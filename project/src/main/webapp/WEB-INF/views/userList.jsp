<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="site-section site-blocks-2">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-7 site-section-heading text-center pt-4">
                <h2>Users</h2>
            </div>
        </div>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- <%--@elvariable id="users" type="java.util.List<com.onlinekaufen.springframework.dto.UserDTO>"--%> -->
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.phoneNo}</td>
                        <td>${user.emailId}</td>
                        <td>${user.address}</td>
                        <!-- <td>${user.enabled ? '<a href = "/ban/?id="${user.id} class="btn btn-danger">Ban</a>' : '<a href="/unban/?id=${user.id}" class="btn btn-success">Unban</a>'}</td> -->
                        <c:if test = "${user.enabled == true}">
                            <td> <a href = "${pageContext.request.contextPath}/admin/users/ban?id=${user.id}" class="btn btn-danger">Ban</a></td>
                        </c:if>
                        <c:if test = "${user.enabled == false}">
                            <td> <a href = "${pageContext.request.contextPath}/admin/users/unban?id=${user.id}" class="btn btn-success">Unban</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>