<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
            <!--                css/1235-->
            <a class="navbar-brand" href="index.html">MES Project</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto navbar-right-top">
                    <li class="nav-item ml-4">
                        <a class ="nav-link" href="/link">Spis zleceń</a>
                    </li>
                    <li class="nav-item ml-4">
                        <a class ="nav-link" href="/link">Wyszukaj</a>
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
                        <h2 class="pageheader-title">E-commerce Dashboard Template </h2>
                        <p class="pageheader-text">Nulla euismod urna eros, sit amet scelerisque torton lectus vel mauris facilisis faucibus at enim quis massa lobortis rutrum.</p>
                        <div class="page-breadcrumb">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">Dashboard</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">E-Commerce Dashboard Template</li>
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
                <div class="col-xl-9 col-lg-12 col-md-6 col-sm-12 col-12">
                    <div class="card">
                        <h3 class="card-header">Product number: ${product.id}</h3>
                        <h3 class="card-header">Product type: ${product.productType.productType}</h3>
                        <div class="card-body p-0">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="bg-light">
                                    <tr class="border-0">
                                        <th class="border-0">Stage</th>
                                        <th class="border-0">Duration</th>
                                        <th class="border-0">Start</th>
                                        <th class="border-0">Finish</th>
                                        <th class="border-0">Delay</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${stages}" var="stage">
                                        <tr>
                                            <td>${stage.productionPhaseName}</td>
                                            <td>${stage.duration}</td>
                                            <td>
                                                <c:choose>
                                                <c:when test="${stage.actualStartOfStage != null}">
                                                    ${stage.actualStartOfStage}
                                                </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${stage.actualEndOfStage != null}">
                                                        ${stage.actualEndOfStage}
                                                    </c:when>
                                                    <c:when test="${stage.actualStartOfStage != null}">
                                                        In progress...
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${stage.delay==null}"></c:when>
                                                    <c:when test="${stage.delay>0}">
                                                        <span class="badge-dot badge-danger mr-1"></span>Delay
                                                        <c:choose>
                                                            <c:when test="${stage.delay==1}">
                                                                1 day
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${stage.delay} days
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:when test="${stage.delay==0}">
                                                        <span class="badge-dot badge-success mr-1"></span>According to plan
                                                    </c:when>
                                                    <c:when test="${stage.delay<1}">
                                                        <span class="badge-dot badge-primary mr-1"></span>Time reduction ${stage.delay} days
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <table class="table">
                                    <thead class="bg-light">
                                    <tbody>
                                    <tr>
                                        <c:choose>
                                            <c:when test="${product.delay == 0}">
                                                <td class="alert-success">Production is going according to plan. Planned completion date: ${product.plannedEndOfProduction}</td>
                                            </c:when>
                                            <c:when test="${product.delay < 0}">
                                                <td class="alert-success">Production goes faster than expected. Planned completion date: ${product.predictedEndOfProduction}. ${product.delay} days ahead of schedule</td>
                                            </c:when>
                                            <c:when test="${product.delay > 0}">
                                                <td class="alert-danger">The production date is at stake. The delay is ${product.delay}. Planned completion date: ${product.predictedEndOfProduction}</td>
                                            </c:when>
                                        </c:choose>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end main table -->

            </div>
        </div>
        <%--include footera i skryptów--%>
        <jsp:include page="fragments/footer.jsp"/>

</body>

</html>