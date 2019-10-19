<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="site-wrap">
    <div class="site-section">
        <div class="container">
            <c:forEach var="msg" items="${messages}">
                <div class="row">
                    <div class="col-sm-8 col-12 offset-sm-2">
                        <a href="#">
                            <div class="card mt-4" style="max-height: 250px;">
                                <div class="row no-gutters">
                                    <div class="col-sm-1 col-2">
                                        <img src="${pageContext.request.contextPath}/resources/images/user.png"
                                             class="img-fluid" style=" width: 100%;height: auto;" alt="User Image"/>
                                    </div>
                                    <div class="desc col-sm-11 col-10" style="padding: 5px;height: 100%;">
                                        <div class="card-block px-2">
                                            <h5 class="ml-3 ">${msg.firstName} &nbsp; ${msg.lastName}</h5>
                                            <p class="text-right"> ${msg.sent_date}</p>

                                            <p class="text-justify">${msg.message}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>