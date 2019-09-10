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
                            <th>Category ID</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Create time</th>
                            <th>Update time</th>
                            <th>Operation</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list categoryList as category>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td class="operation">
                                    <a href="/sell/seller/category/index?categoryId=${category.categoryId}">Modify</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
