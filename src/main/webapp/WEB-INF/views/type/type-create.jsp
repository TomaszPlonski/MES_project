<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="errors" uri="http://www.springframework.org/tags" %>
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
                        <h2 class="pageheader-title">Create new product type</h2>
                        <div class="page-breadcrumb">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/" class="breadcrumb-link">All orders</a></li>
                                    <li class="breadcrumb-item"><a href="/order/add" class="breadcrumb-link">Add new order</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Create new product type</li>
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
            <spring:form modelAttribute="typeForm">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-body">
                                    <label class="col-form-label">Product type:</br>
                                        <spring:input path="productType"/></br>
                                        <spring:errors path="productType" element="span" cssClass="alert-danger"/>
                                    </label>
                            </div>
                                <div class="card-body border-top">
                                    <label class="col-form-label">Product attribute<br>
                                        <label>Name of attribute: </br><input name="attribute"></label>
                                        <button name="addAttribute">Add attribute<br></button>
                                        <div>Attributes:
                                        </div>
                                    <c:forEach items="${typeForm.attributes}" var="attribute" varStatus="status">
                                        <div>
                                            <input type="text" name="attributes[${status.index}].attribute"
                                                   value="${typeForm.attributes[status.index].attribute}"/>
                                            <button name="removeAttribute" value="${status.index}">Remove</button>
                                        </div>
                                    </c:forEach>
                                    </label>
                                </div>
                                    <div class="card-body border-top">
                                        <label class="col-form-label">Product phases</br>
                                            <label>Name of phase: </br><input name="phaseName"></label>
                                            <label>Default Duration in days: </br><input name="defaultDuration" type="number" class="form-control"></label>
                                            <button name="addPhase">Add phase</button>
                                        </label>
                                        <div> Phases:
                                        </div>
                                        <c:forEach items="${typeForm.phases}" var="phase" varStatus="status">
                                            <div>
                                                <input type="text" name="phases[${status.index}].phaseName"
                                                       value="${typeForm.phases[status.index].phaseName}"/>
                                                <input type="number" name="phases[${status.index}].defaultDuration"
                                                       value="${typeForm.phases[status.index].defaultDuration}"/>
                                                <button name="removePhase" value="${status.index}">Remove</button>

                                            </div>
                                        </c:forEach>
<%--                                        <errors:hasBindErrors name="typeForm">--%>
<%--                                            <c:forEach var="error" items="${errors.allErrors}">--%>
<%--                                                <b class=alert-danger"><errors:message  message="${error}" /></b>--%>
<%--                                                <br/>--%>
<%--                                            </c:forEach>--%>
<%--                                        </errors:hasBindErrors>--%>

                                        </label>
                                    </div>

                                <spring:button class="btn-primary" name="createType">Create product Type</spring:button>
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