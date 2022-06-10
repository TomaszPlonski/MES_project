<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--include head oraz górnego menu. Zawiera owarcie tagu <body>--%>
<%@include file="../fragments/header.jsp"%>

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
                        <h2 class="pageheader-title">Add new order</h2>
                        <div class="page-breadcrumb">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/" class="breadcrumb-link">All orders</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Add new order</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- end pageheader  -->
            <!-- ============================================================== -->
            <div class="row">
                <!-- ============================================================== -->
                <!-- main table -->
                <!-- ============================================================== -->
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="section-block">
            <spring:form modelAttribute="order">
                    <div class="card">
                        <div class="card-body">
                            <div class="custom-control">
                                    <label class="col-form-label">Order Name:</br>
                                        <spring:input path="name"/>
                                        <spring:errors path="name" element="span" cssClass="alert-danger"/>
                                    </label>
                            </div>
                            <div class="custom-control">
                                <label class="col-form-label">Order Value:</br>
                                        <spring:input path="orderValue"/>
                                        <spring:errors path="orderValue" element="span" cssClass="alert-danger"/>
                                </label>


                            </div>
                            <div class="custom-control">
                                <label class="col-form-label">Add Product:</br>
                                    <select class="selectpicker" name="newProductType">
                                     <c:forEach items="${types}" var="type">
                                         <option value="${type.id}">${type.productType}</option>
                                     </c:forEach>
                                    </select>
                                    <button name="addProduct">Chose type</button>
                                    <a class ="btn btn-brand btn-rounded btn-sm" href="/type/add" >Add new type</a>
                                </label>
                            </div>
                            <c:choose>
                                <c:when test="${order.products.size()!=0}">
                                    <c:choose>
                                <c:when test="${order.products.get(order.products.size()-1).attributes.size() !=0}">
                                    <div class="custom-control">
                                        <label class="col-form-label">Attribute:</br>
                                                ${order.products.get(order.products.size()-1).attributes.get(0).name}<input type="text" name="value">
                                            <button name="addValue">Add attribute value</button>
                                        </label>
                                    </div>
                                </c:when>
                                    </c:choose>
                            </c:when>
                            </c:choose>

                            <div class="card-body border-top">
                                <label class="col-form-label">Added Products:</br>
                                <c:forEach items="${order.products}" var="product" varStatus="status">
                                    <div>Type: ${product.productType.productType}
                                        <button name="removeProduct" value="${status.index}">Remove Product</button>
                                        </br>
                                        <c:forEach items="${product.typeAttributeMap}" var="map">
                                            ${map.key.name}: ${map.value.value} </br>
                                        </c:forEach> </br>

                                    </div>
                                </c:forEach>
                                </label>
                            </div>
                            <c:choose>
                            <c:when test="${order.products.size()!=0}">
                                <c:choose>
                                <c:when test="${order.products.get(order.products.size()-1).attributes.size() == 0}">

                                <spring:button class="btn-primary" name="createOrder">Create order</spring:button>
                            </c:when>
                                </c:choose>
                            </c:when>
                            </c:choose>

                            </spring:form>
                        </div>
                    </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end main table -->

            </div>
        </div>
        <%--include footera i skryptów--%>
        <jsp:include page="../fragments/footer.jsp"/>

</body>

</html>