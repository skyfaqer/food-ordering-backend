<html>
<head>
    <#include "../common/header.ftl">
    <style>
        .dropdown-title {
            font-size: 22px;
        }
        .dropdown-parent {
            font-size: 15px;
        }
        .dropdown-item {
            font-size: 13px;
        }
        .formTitle {
            font-size: 20px;
        }
        .formBody {
            margin-bottom: 60px;
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
                    <form role="form" class="formBody" method="post" action="/sell/seller/category/save">
                        <div class="form-group formTitle">
                            <#if category??>
                                <label>Category info</label>
                            <#else>
                                <label>Add category</label>
                            </#if>
                        </div>
                        <div class="form-group">
                            <label>Category name</label>
                            <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Type</label>
                            <input name="categoryType" type="number" min="1" max="999" class="form-control" value="${(category.categoryType)!""}"/>
                        </div>
                        <input hidden type="text" name="categoryId" value="${(category.categoryId)!""}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
