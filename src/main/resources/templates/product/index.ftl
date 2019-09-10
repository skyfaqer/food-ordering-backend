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
        img {
            margin-bottom: 10px;
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
                    <form role="form" class="formBody" method="post" action="/sell/seller/product/save">
                        <div class="form-group formTitle">
                            <#if productInfo??>
                                <label>Product detail</label>
                            <#else>
                                <label>Add product</label>
                            </#if>
                        </div>
                        <div class="form-group">
                            <label>Product name</label>
                            <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!""}"
                                   onkeyup="if(isNaN(this.value)) execCommand('undo')" onpaste="if(isNaN(this.value)) execCommand('undo')"/>
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input name="productStock" type="number" min="0" max="999" class="form-control" value="${(productInfo.productStock)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Icon</label><br>
                            <img width="120" height="120" src="${(productInfo.productIcon)!""}" alt="No icon">
                            <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                    >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!""}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
