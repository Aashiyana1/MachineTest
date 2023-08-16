# Product-Catalog-API
A simple product-category API

# Technology stack

```bash
Java 8
Maven
RESTful API
SpringBoot Framework
Swagger
Hibernate
JPA
Json
H2-in memory Database
Junit4, Mockito and PowerMockito for Unit Tetsing
```

## Building And Running

Using maven for building the application

```bash
mvn clean install 
```
Using maven to package the jar

```bash
mvn package
```
Standalone executable jar will be created under target ProductCatalog-0.0.1-SNAPSHOT.jar. So you can run this jar:

```bash
java -jar .\target\ProductCatalog-0.0.1-SNAPSHOT.jar
```

## Available RESTful APIs

```python
Product APIs:

Post- Add Product
http://localhost:8080/api/v1/products

Get- Get all Products
http://localhost:8080/api/v1/products

Get- Get Product by Id
http://localhost:8080/api/v1/products/{productId}

Put- Update Product by Id
http://localhost:8080/api/v1/products/{productId}

Delete- Delete Product by Id
http://localhost:8080/api/v1/products/{productId}

Get- List of all products that belong to particular categoryId
http://localhost:8080/api/v1/products/categories/{categoryId}

Category APIs:

Post- Add Category
http://localhost:8080/api/v1/categories

Get- Get all categories
http://localhost:8080/api/v1/categories

Get- Get Category by Id
http://localhost:8080/api/v1/categories/{categoryId}

Put- Update Category by Id
http://localhost:8080/api/v1/categories/{categoryId}

Delete- Delete Category by Id
http://localhost:8080/api/v1/categories/{categoryId}
```

## Http Status
```python
200 OK: OK - The request has succeeded
201 OK: CREATED - New resource has been created
204 OK: NO_CONTENT - The resource has been deleted successfully
400 Bad Request: BD_REQUEST - The request was invalid or cannot be served
403 Forbidden - The user requestiong the request does not have the specified role
404 Not Found: NOT_FOUND - There is no resource behind the URL
500 Internal Server Error: The server encountered an unexpected condition
```
## Authentication
```python
Created two users
1. Admin - Role type: Admin
   admin: password
   YWRtaW46cGFzc3dvcmQ=
2. User - Role type: User
   user: password
   dXNlcjpwYXNzd29yZA==

All get requests can be performed by both users
Add/Update/Delete opeartions can be performed by user who have admin role.
```

## Swagger
```python
Swagget doc URL: http://localhost:8080/api/v2/api-docs
Swagger UI URL: http://localhost:8080/api/swagger-ui.html
```

## Note
Kindly please add categories before adding products
