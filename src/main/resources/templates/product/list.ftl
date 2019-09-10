<html>
<head>
    <#include "../common/header.ftl">
    <style>
        table {
            font-size: 14px;
        }
        .operation {
            width: 60px;
        }
        .pageNum {
            display: block;
            width: 40px;
            text-align: center;
        }
        .dropdown-title {
            font-size: 22px;
        }
        .dropdown-parent {
            font-size: 15px;
        }
        .dropdown-item {
            font-size: 13px;
        }
    </style>
</head>
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Icon</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Create time</th>
                            <th>Update time</th>
                            <th colspan="2">Operations</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img width="60" height="60" src="${productInfo.productIcon}" alt="Oops!"></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td class="operation">
                                    <a href="/sell/seller/product/index?productId=${productInfo.productId}">Modify</a>
                                </td>
                                <td class="operation">
                                    <#if productInfo.getProductStatusEnum().message == "on sale">
                                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">Disable</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">Enable</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">Prev</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${currentPage - 1}&size=${size}">Prev</a></li>
                        </#if>
                        <#if startPage gt 1>
                            <li class="disabled"><a href="#">...</a></li>
                        </#if>
                        <#list startPage..endPage as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#" class="pageNum">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/product/list?page=${index}&size=${size}" class="pageNum">${index}</a></li>
                            </#if>
                        </#list>
                        <#if endPage lt productInfoPage.getTotalPages()>
                            <li class="disabled"><a href="#">...</a></li>
                        </#if>
                        <#if currentPage gte productInfoPage.getTotalPages()>
                            <li class="disabled"><a href="#">Next</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${currentPage + 1}&size=${size}">Next</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
