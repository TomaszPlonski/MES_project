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
                        <h2 class="pageheader-title">Stages of production details of Product nr ${stages.id}</h2>
                        <div class="page-breadcrumb">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/" class="breadcrumb-link">All orders</a></li>
                                    <li class="breadcrumb-item"><a href="/order/get/${stages.orderId}" class="breadcrumb-link">Order nr ${stages.orderId}</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Stages of Product nr ${stages.id}</li>
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
                    <div class="card">
                        <h3 class="card-header">Product type: ${stages.productType}</h3>
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
                                    <c:forEach items="${stages.stagesDetailsPOJOS}" var="stage">
                                        <tr>
                                            <td>${stage.phaseName}</td>
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
                                                    <c:when test="${stage.delay==null}">

                                                        <sec:authorize access="hasRole('FACTORY')">
                                                        <c:choose>
                                                            <c:when test="${stage.actualStartOfStage != null}">
                                                                <form method="post" action="/product/stages/end/active">
                                                                    <button class="btn btn-warning btn-rounded btn-xs" name="productId" value="${stages.id}">End stage</button>
                                                                </form>
                                                            </c:when>
                                                        </c:choose>
                                                        </sec:authorize>


                                                    </c:when>
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