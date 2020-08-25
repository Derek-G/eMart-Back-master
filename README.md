## Guid
- 1、open Redis and MySQL Serve

- 2、Start each microservice separately：EurekaServerApplication、ApiGatewayApplication、ConfigServerApplication、CategoryApplication、OrderApplication、UserApplication、WebApplication

- 3、Service call

  - Method 1：Run Unit Test[WebAppTest](https://github.com/Derek-G/eMart-Back-master/tree/master/web-app/src/test/java/com/kedacom/keda/WebAppTest.java)

  - Method 2：By Postman Run REST URL

    Commodity Service-View Product Details Function[GET]：http://localhost:8080/web-app/category/introduction/1
  
    Return Json
    
    ```json
    {
        "code": 0,
        "msg": "success",
        "data": {
            "id": 1,
            "name": "seafood",
            "price": 29.99,
            "detail": ""
        }
    }
    ```
    
    User service-login function[POST]：http://localhost:8080/web-app/users/login?name=admin&password=123456
    
    Return Json
    
    ```json
      {
        "code": 0,
        "msg": "success",
        "data": null
      }
    ```
