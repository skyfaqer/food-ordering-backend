# Food-ordering-backend
 
## RESTful APIs

### Product list

```
GET /sell/buyer/product/list
```

Params

```
None
```

Return

```
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "name": "热榜",
            "type": 1,
            "foods": [
                {
                    "id": "123456",
                    "name": "皮蛋粥",
                    "price": 1.2,
                    "description": "好吃的皮蛋粥",
                    "icon": "http://xxx.com",
                }
            ]
        },
        {
            "name": "好吃的",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "慕斯蛋糕",
                    "price": 10.9,
                    "description": "美味爽口",
                    "icon": "http://xxx.com",
                }
            ]
        }
    ]
}
```

### Create order

```
POST /sell/buyer/order/create
```

Params

```
name: "张三"
phone: "18868822111"
address: "somewhere"
id: "ew3euwhd7sjw9diwkq"
items: [{
    productId: "1423113435324",
    productQuantity: 2 // amount of product purchased
}]

```

Return

```
{
  "code": 0,
  "msg": "success",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

### Order list

```
GET /sell/buyer/order/list
```

Params

```
id: 18eu2jwk2kse3r42e2e
page: 0 // page number
size: 10 // page size
```

Return

```
{
  "code": 0,
  "msg": "success",
  "data": [
    {
      "orderId": "161873371171128075",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "somewhere",
      "buyerId": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    },
    {
      "orderId": "161873371171128076",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "somewhere",
      "buyerId": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    }
  ]
}
```

### Order detail

```
GET /sell/buyer/order/detail
```

Params

```
id: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

Return

```
{
    "code": 0,
    "msg": "success",
    "data": {
          "orderId": "161899085773669363",
          "buyerName": "李四",
          "buyerPhone": "18868877111",
          "buyerAddress": "somewhere",
          "buyerId": "18eu2jwk2kse3r42e2e",
          "orderAmount": 18,
          "orderStatus": 0,
          "payStatus": 0,
          "createTime": 1490177352,
          "updateTime": 1490177352,
          "orderDetailList": [
            {
                "detailId": "161899085974995851",
                "orderId": "161899085773669363",
                "productId": "157875196362360019",
                "productName": "招牌奶茶",
                "productPrice": 9,
                "productQuantity": 2,
                "productIcon": "http://xxx.com",
                "productImage": "http://xxx.com"
            }
         ]
    }
}
```

### Cancel order

```
POST /sell/buyer/order/cancel
```

Params

```
id: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

Return

```
{
    "code": 0,
    "msg": "success",
    "data": null
}
```

