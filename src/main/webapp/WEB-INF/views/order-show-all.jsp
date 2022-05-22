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
            <a class="navbar-brand" href="index.html">EXPANSION JOINTS FACTORY</a>
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
            <!-- ============================================================== -->
            <!-- search bar  -->
            <!-- ============================================================== -->
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <div class="card">
                    <div class="card-body">
                        <form>
                            <input class="form-control form-control-lg" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-primary search-btn" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- end search bar  -->
            <!-- ============================================================== -->
            <div class="row">
                <!-- ============================================================== -->
                <!-- main table -->
                <!-- ============================================================== -->
                <div class="col-xl-9 col-lg-12 col-md-6 col-sm-12 col-12">
                    <div class="card">
                        <h5 class="card-header">Orders</h5>
                        <div class="card-body p-0">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="bg-light">
                                    <tr class="border-0">
                                        <th class="border-0">Number</th>
                                        <th class="border-0">Handler</th>
                                        <th class="border-0">Name</th>
                                        <th class="border-0">Purchaser</th>
                                        <th class="border-0">Value</th>
                                        <th class="border-0">Status</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${orders}" var="order">
                                        <tr>
                                            <td>1</td>
                                            <td>${order.orderHandlerName}</td>
                                            <td>${order.name}</td>
                                            <td>${order.purchaserName}</td>
                                            <td>${order.orderValue} zł</td>
                                            <td>1</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td>1</td>
                                        <td>
                                            <div class="m-r-10"><img src="assets/images/product-pic.jpg" alt="user" class="rounded" width="45"></div>
                                        </td>
                                        <td>Product #1 </td>
                                        <td>id000001 </td>
                                        <td>20</td>
                                        <td>$80.00</td>
                                        <td>27-08-2018 01:22:12</td>
                                        <td>Patricia J. King </td>
                                        <td><span class="badge-dot badge-brand mr-1"></span>W produkcji</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>
                                            <div class="m-r-10"><img src="assets/images/product-pic-2.jpg" alt="user" class="rounded" width="45"></div>
                                        </td>
                                        <td>Product #2 </td>
                                        <td>id000002 </td>
                                        <td>12</td>
                                        <td>$180.00</td>
                                        <td>25-08-2018 21:12:56</td>
                                        <td>Rachel J. Wicker </td>
                                        <td><span class="badge-dot badge-success mr-1"></span>Zakończony etap </td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>
                                            <div class="m-r-10"><img src="assets/images/product-pic-3.jpg" alt="user" class="rounded" width="45"></div>
                                        </td>
                                        <td>Product #3 </td>
                                        <td>id000003 </td>
                                        <td>23</td>
                                        <td>$820.00</td>
                                        <td>24-08-2018 14:12:77</td>
                                        <td>Michael K. Ledford </td>
                                        <td><span class="badge-dot badge-danger mr-1"></span>Opóźniony termin </td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>
                                            <div class="m-r-10"><img src="assets/images/product-pic-4.jpg" alt="user" class="rounded" width="45"></div>
                                        </td>
                                        <td>Product #4 </td>
                                        <td>id000004 </td>
                                        <td>34</td>
                                        <td>$340.00</td>
                                        <td>23-08-2018 09:12:35</td>
                                        <td>Michael K. Ledford </td>
                                        <td><span class="badge-dot badge-primary mr-1"></span>Fioletowy znacznik </td>
                                    </tr>
                                    <tr>
                                        <td colspan="9"><a href="#" class="btn btn-outline-light float-right">View Details</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end main table -->

                <!-- search sidebar  -->
                <!-- ============================================================== -->
                <div class="col-xl-3 col-lg-4 col-md-4 col-sm-12 col-12">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="font-16">Sorting By</h3>
                            <select class="form-control">
                                <option>Followers</option>
                                <option>Followers</option>
                            </select>
                        </div>
                        <div class="card-body border-top">
                            <h3 class="font-16">Influncer by Rating</h3>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio1" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label" for="customRadio1"><i class="fas fa-star rating-color fa-xs"></i></label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio2" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label" for="customRadio2"><i class="fas fa-star rating-color fa-xs"></i><i class="fas fa-star rating-color fa-xs"></i></label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio3" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label" for="customRadio3"><i class="fas fa-star rating-color fa-xs"></i><i class="fas fa-star rating-color fa-xs"></i><i class="fas fa-star rating-color fa-xs"></i></label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio4" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label" for="customRadio4"><i class="fas fa-star rating-color fa-xs"></i><i class="fas fa-star rating-color fa-xs"></i><i class="fas fa-star rating-color fa-xs"></i><i class="fas fa-star rating-color fa-xs"></i></label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio5" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label" for="customRadio5"><i class="fas fa-star rating-color fa-xs"></i><i class="fas fa-star rating-color fa-xs fa-xs"></i><i class="fas fa-star rating-color fa-xs fa-xs"></i><i class="fas fa-star rating-color fa-xs fa-xs"></i><i class="fas fa-star rating-color fa-xs fa-xs"></i></label>
                            </div>
                        </div>
                        <div class="card-body border-top">
                            <h3 class="font-16">Social Media Platform</h3>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck10">
                                <label class="custom-control-label" for="customCheck10">Facebook</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck11">
                                <label class="custom-control-label" for="customCheck11">Instagram</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck12">
                                <label class="custom-control-label" for="customCheck12">Pinterest</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck13">
                                <label class="custom-control-label" for="customCheck13">Twitter</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck14">
                                <label class="custom-control-label" for="customCheck14">Youtube</label>
                            </div>
                        </div>
                        <div class="card-body border-top">
                            <h3 class="font-16">Influncer Category</h3>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck15">
                                <label class="custom-control-label" for="customCheck15">Business</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck16">
                                <label class="custom-control-label" for="customCheck16">Lifestyle</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck17">
                                <label class="custom-control-label" for="customCheck17">Fitness</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck18">
                                <label class="custom-control-label" for="customCheck18">Sports</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck19">
                                <label class="custom-control-label" for="customCheck19">Clothing</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck20">
                                <label class="custom-control-label" for="customCheck20">Pets & Animals</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck21">
                                <label class="custom-control-label" for="customCheck21">Beauty & Makeup</label>
                            </div>
                        </div>
                        <div class="card-body border-top">
                            <h3 class="font-16">Age Demographics</h3>
                            <select class="form-control">
                                <option selected>Select the Age</option>
                                <option value="20-30">20-30</option>
                                <option value="30-40">30-40</option>
                                <option value="40-50">40-50</option>
                            </select>
                        </div>
                        <div class="card-body border-top">
                            <a href="#" class="btn btn-secondary btn-lg btn-block">Submit</a>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end influencer sidebar  -->
                <!-- ============================================================== -->
            </div>
        </div>
        <%--include footera i skryptów--%>
        <jsp:include page="fragments/footer.jsp"/>

</body>

</html>