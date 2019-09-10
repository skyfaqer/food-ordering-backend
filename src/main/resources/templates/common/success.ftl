<html>
<head>
    <meta charset="utf-8">
    <title>Success</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 70px;
        }
        #time {
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    Success!
                </h4> <strong>${msg!""}</strong><br/><a href="${url}" class="alert-link">jump to ${urlName} page in <div id="time">5</div>s...</a>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    function countDown() {
        var t = parseInt(time.innerHTML);
        t--;
        if (t > 0) {
            time.innerHTML = t + "";
            setTimeout(countDown, 1000);
        } else {
            location.href="${url}";
        }
    }
    window.onload = function () {
        setTimeout(countDown, 1000);
    }
</script>
</html>
