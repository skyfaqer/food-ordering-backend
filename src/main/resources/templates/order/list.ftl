<html>
<head>
    <#include "../common/header.ftl">
    <style>
        table {
            font-size: 14px;
        }
        .operation {
            width: 56px;
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
                            <th>Order ID</th>
                            <th>Buyer</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Amount</th>
                            <th>Order status</th>
                            <th>Payment status</th>
                            <th>Create time</th>
                            <th colspan="2">Operations</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum()}</td>
                                <td>${orderDTO.getPayStatusEnum()}</td>
                                <td>${orderDTO.createTime}</td>
                                <td class="operation">
                                    <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">Detail</a>
                                </td>
                                <td class="operation">
                                    <#if orderDTO.getOrderStatusEnum().message == "new order">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">Cancel</a>
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
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">Prev</a></li>
                        </#if>
                        <#if startPage gt 1>
                            <li class="disabled"><a href="#">...</a></li>
                        </#if>
                        <#list startPage..endPage as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#" class="pageNum">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}" class="pageNum">${index}</a></li>
                            </#if>
                        </#list>
                        <#if endPage lt orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">...</a></li>
                        </#if>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">Next</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">Next</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<#--Modal: new order-->
<div class="modal fade" id="messageModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">New message</h4>
            </div>
            <div class="modal-body">You have a new order.</div>
            <div class="modal-footer">
                <button onclick="document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">View new order</button>
            </div>
        </div>
    </div>
</div>
<#--Play notice audio on receiving new order-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket('ws://cgy.natapp1.cc/sell/webSocket');
    } else {
        alert('Your browser does not support WebSocket. You may not be able to receive order update messages!');
    }
    webSocket.onopen = function (event) {
        console.log('Starting connection...');
    }
    webSocket.onclose = function (event) {
        console.log('Closing connection...');
    }
    webSocket.onmessage = function (event) {
        console.log('Received a message: ' + event.data);
        $('#messageModal').modal('show');
        document.getElementById('notice').play();
    }
    webSocket.onerror = function () {
        alert('WebSocket error!');
    }
    window.onbeforeunload = function () {
        webSocket.close();
    }
</script>
</body>
</html>
