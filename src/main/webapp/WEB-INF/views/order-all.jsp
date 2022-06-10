<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--include head oraz górnego menu. Zawiera owarcie tagu <body>--%>
<%@include file="fragments/header.jsp"%>

<body>
<!-- ============================================================== -->
<!-- main wrapper -->
<!-- ============================================================== -->
<div class="dashboard-main-wrapper">
    <!-- ============================================================== -->
    <!-- navbar -->
    <!-- ============================================================== -->
    <div class="dashboard-header">
        <nav class="navbar navbar-expand-lg bg-white fixed-top">
            <a class="navbar-brand" href="/">MES Project</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <sec:authorize access="isAuthenticated()">
                You are logged as <sec:authentication property="name"/>
            </sec:authorize>

            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto navbar-right-top">
                    <li class="nav-item ml-4">
                        <form action="<c:url value="/logout"/>" method="post">
                        <input class="btn icon-logout" type="submit" value="Logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <!-- ============================================================== -->
    <!-- end navbar -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- wrapper  -->
    <!-- ============================================================== -->
    <div class="dashboard-ecommerce">
        <div class="container-fluid dashboard-content ">
            <!-- ============================================================== -->
            <!-- pageheader  -->
            <!-- ============================================================== -->
            <div class="row">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="page-header">
                        <h2 class="pageheader-title">Order List</h2>

                        <sec:authorize access="hasRole('OFFICE')">
                        <a href="/order/add" class="btn btn-primary float-lg-right">Add Order</a>
                        </sec:authorize>

                        <form method="post" action="/" style="border-radius: 0px;">
                                <label class="col-12 col-sm-2 col-form-label text-sm-left">Show orders in progress</label>
                                <div class="switch-button switch-button-sm">
                                <input type="checkbox"  ${inProgressOrders} name="inProgressOrders" id="inProgressOrders" value='checked=""'><span>
                                                    <label for="inProgressOrders"></label></span>
                                </div>
                        <div>
                            <label class="col-12 col-sm-2 col-form-label text-sm-left">Show ended orders</label>
                            <div class="switch-button switch-button-sm">
                                <input type="checkbox" ${endedOrders} name="endedOrders" id="endedOrders" value='checked=""'><span>
                                                    <label for="endedOrders"></label></span>
                            </div>
                         </div>
                            <div>
                                <label class="col-12 col-sm-2 text-sm-left"></label>
                                    <button class="btn btn-rounded btn-primary btn-xs mt-2 mb-3">Change view</button>
                            </div>


                        </form>

                        <div class="page-breadcrumb">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a class="breadcrumb-link">All orders</a></li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- end pageheader  -->
            <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- main table -->
                <!-- ============================================================== -->

            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="card">
                        <div class="card-body p-0">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="bg-light">
                                    <tr class="border-0">
                                        <th class="border-0">Number</th>
                                        <th class="border-0">Name</th>
                                        <th class="border-0">Value</th>
                                        <th class="border-0">Status</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${orders}" var="order">
                                        <tr>
                                            <td>${order.id}</td>
                                            <td>${order.name}</td>
                                            <td>${order.orderValue} zł</td>
                                            <td><c:choose>
                                                <c:when test="${order.orderFinished}">
                                                    <span class="badge-dot badge-success mr-1"></span>Order completed
                                                </c:when>
                                                <c:when test="${!order.orderFinished}">
                                                    <span class="badge-dot badge-brand mr-1"></span>Order in progress
                                                </c:when>
                                            </c:choose>
                                            </td>
                                            <td><a href="/order/get/${order.id}" class="btn btn-rounded btn-primary btn-sm">Order details</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end main table -->

            </div>
        <%--include footera i skryptów--%>
        <jsp:include page="fragments/footer.jsp"/>

</body>

</html>