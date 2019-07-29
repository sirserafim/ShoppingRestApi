Documentation

# 0. Intro

Description of the exercise (in Greek).

Περιγραφή Εργασίας
Έστω μια αλυσίδα με καταστήματα ηλεκτρικών ειδών. Η αλυσίδα διαθέτει καταστήματα λιανικής πώλησης και μια κεντρική αποθήκη. Κάθε κατάστημα στελεχώνεται από υπαλλήλους, οι οποίοι ανήκουν σε διαφορετικά τμήματα. Τα διαθέσιμα τμήματα περιλαμβάνουν: τμήμα λευκών συσκευών, τμήμα μικροσυσκευών και τμήμα ηλεκτρονικών ειδών.
Για την πραγματοποίηση μιας πώλησης αναλόγως το τμήμα που πραγματοποιείται, ο εκάστοτε υπάλληλος ενημερώνεται σχετικά με τη διαθεσιμότητα του είδους της κεντρικής αποθήκης και εφόσον είναι διαθέσιμο τη στιγμή εκείνη προχωρά στη δέσμευση και πώληση του είδους.
Να μοντελοποιηθεί η παραπάνω περιγραφή σε μια βάση δεδομένων και να δημιουργηθούν τα κατάλληλα APIs για να παρέχουν τις απαραίτητες πληροφορίες μέσω REST πρωτοκόλλου. Τέλος να αναπτυχθεί ένα UI (επιθυμητή η χρήση της γλώσσας Angular) η οποία να επιδεικνύει την πραγματοποίηση μιας πώλησης από την πλευρά του υπαλλήλου καθώς και τη λίστα και αναζήτηση στα διαθέσιμα προϊόντα του καταστήματος.

In order to deliver the required functionality we break the implementation into 3 main components:
Database (Mysql)
REST-API (Java, Spring boot framework)
Front-end (Angular, HTML, CSS)
 
The idea behind the implementation is that the end user will use the Front-End Gui to do some actions. The FE GUI will be used for UI navigation while it will issue some HTTP requests towards our REST-API. Then the REST-API controls the business logic and is used to interfere with the Database.

# 1. Environment and account setup

Development of the Java REST API is done via Workspace-Spring-Tools-Suite. An integrated Apache Tomcat server is used to bring up the REST service.
Functional Testing of the REST API is done through Postman. You can download and import a collection.
Development of the Angular FE GUI is done via Visual Studio Code.

# 2. Database

After analysis of the requirements and modeling the database you can import the [database](http://sir.serafeimsaratsis.me:8080/shop/retailshop.sql) with some random content or see the tables below:

```
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| retailshop         |
| sys                |
+--------------------+
```

```
mysql> show tables;
+----------------------+
| Tables_in_retailshop |
+----------------------+
| commited_product     |
| employee             |
| shop                 |
| stock                |
+----------------------+
```

```
mysql> describe commited_product;
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| created_at  | datetime     | NO   |     | NULL    |                |
| description | varchar(255) | NO   |     | NULL    |                |
| name        | varchar(255) | NO   |     | NULL    |                |
| price       | bigint(20)   | NO   |     | NULL    |                |
| quantity    | bigint(20)   | NO   |     | NULL    |                |
| type        | varchar(255) | NO   |     | NULL    |                |
| updated_at  | datetime     | NO   |     | NULL    |                |
| userid      | bigint(20)   | NO   |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
```

```
mysql> describe employee;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| age        | bigint(20)   | NO   |     | NULL    |                |
| created_at | datetime     | NO   |     | NULL    |                |
| gender     | varchar(255) | NO   |     | NULL    |                |
| name       | varchar(255) | NO   |     | NULL    |                |
| password   | bigint(20)   | NO   |     | NULL    |                |
| phone      | bigint(20)   | NO   |     | NULL    |                |
| shopid     | bigint(20)   | NO   |     | NULL    |                |
| surname    | varchar(255) | NO   |     | NULL    |                |
| type       | varchar(255) | NO   |     | NULL    |                |
| updated_at | datetime     | NO   |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
```

```
mysql> describe shop;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| address    | varchar(255) | NO   |     | NULL    |                |
| city       | varchar(255) | NO   |     | NULL    |                |
| country    | varchar(255) | NO   |     | NULL    |                |
| county     | varchar(255) | NO   |     | NULL    |                |
| created_at | datetime     | NO   |     | NULL    |                |
| name       | varchar(255) | NO   |     | NULL    |                |
| phone      | bigint(20)   | NO   |     | NULL    |                |
| updated_at | datetime     | NO   |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
```

```
mysql> describe stock;
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| created_at  | datetime     | NO   |     | NULL    |                |
| description | varchar(255) | NO   |     | NULL    |                |
| name        | varchar(255) | NO   |     | NULL    |                |
| price       | bigint(20)   | NO   |     | NULL    |                |
| quantity    | bigint(20)   | NO   |     | NULL    |                |
| type        | varchar(255) | NO   |     | NULL    |                |
| updated_at  | datetime     | NO   |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
```

The REST service directly connects and interacts with the database through the Java Hibernate framework.
I modelled all necessary tables, some will be used in further enchancements.

# 3. REST API

The [rest api code can be found under](https://github.com/sirserafim/ShoppingRestApi).
Some REST requests are not used currently but they will be used in future enchancements.

| HTTP verb | path | Description  | Result | Postman Request |
| --- | ----- | ------------ |-----|------ |
| GET | /emp/allemployees | Returns all employees | 200(OK) | ReturnAllEmployees |
| POST | /emp/employees | Creates an employee | 200(OK) | CreateEmployee |
| GET | /emp/employee/{id} | Returns an employee with an id | 200(OK), 404(NOT_FOUND) | ReturnEmployee |
| PUT | /emp/employee/{id} | Update an employee with an id | 200(OK), 404(NOT_FOUND) | UpdateEmployee |
| DELETE | /emp/employee/{id} | Deletes an employee with an id  | 200(OK), 404(NOT_FOUND) | DeleteEmployee |
| GET | /sh/allshops | Returns all shops | 200(OK) | ReturnAllShops |
| POST | /sh/shop | Create a shop | 200(OK) | CreateShop |
| GET | /sh/shop{id} | Returns a shop with an id | 200(OK), 404(NOT_FOUND) | ReturnShop |
| PUT | /sh/shop{id} | Update a shop with an id | 200(OK), 404(NOT_FOUND) | UpdateShop |
| DELETE | /sh/shop{id} | Deletes a shop with an id | 200(OK), 404(NOT_FOUND) | DeleteShop |
| GET | /st/allstock | Returns all Stock Items | 200(OK) | ReturnAllStock |
| POST | /st/stock | Creates an item of Stock | 200(OK) | CreateStockItem|
| GET | /st/stock/{type} | Returns all Stock Items with type of | 200(OK), 404(NOT_FOUND) | ReturnAllTypeStock |
| GET | /st/stock/{id} | Returns an item of Stock with an id | 200(OK), 404(NOT_FOUND) | ReturnItemOfStock |
| PUT | /st/stock/{id} | Update an item of Stock with an id| 200(OK), 404(NOT_FOUND) | UpdateItemOfStock |
| PUT | /st/stock/{userid}/{id}/{quantity} |Ckeck if you can buy an item in stock| 200(OK), 404(NOT_FOUND) | CheckoutStock |
| DELETE | /st/stock/{id} | Deletes a Stock item with an id | 200(OK), 404(NOT_FOUND) | DeleteItemOfStock |
| GET | /comp/commitedproducts | Returns all commited Prducts | 200(OK) | ReturnAllCommitedPrducts |
| DELETE | /comp/commiteduserproducts/{id} | Deletes a commited Prduct that belonged to a user with that id | 200(OK) | DeleteCommitedUsersProducts |
| POST | /comp/commiteduserproduct | Creates a commited Product | 200(OK) | CreateCommitedProduct |
| GET | /comp/commiteduserproduct/{id} | Returns an commited Product with an id | 200(OK), 404(NOT_FOUND) | ReturnCommitedProduct |
| PUT | /comp/commitedproduct/{id} | Update a commitedproduct with an id| 200(OK), 404(NOT_FOUND) | UpdateCommitedProduct |
| DELETE | /comp/commitedproducts | Deletes a commited Prduct with an id | 200(OK), 404(NOT_FOUND) | DeleteCommitedPrduct |

Within the file you can find a collection of HTTP requests done with Postman

# 4. Front-End UI

The GUI and the front-end logic is done with Angular, HTML, CSS.
Goal is to create one search screen and one shopping basket that will include all the core functionality as described in the exersice. 

The GUI is sending HTTP requests to our REST API in order to complete the actions required and interact with the database. Having the FE GUI, the REST API and the Database separated (although all running under localhost on our example case) helps on having a scallable solution.

The [basic ui code can be found under](https://github.com/sirserafim/ShopUi.git).
Unfortunately limited functionality is available through the GUI as I am new to Angular. I wanted to use Angular because I would like to learn it from scratch. Selecting PHP as the main language would have been more fruitful in regards to results but not to knowledge.

# 5. Testing
Functional testing. Via Postman we covered the most prominent scenarios of the api calls of the REST service.

Backlog1: Unit testing

Backlog2: Stress testing. To stress-test the application we can use the [application "siege" to stress test our API.](https://github.com/JoeDog/siege)

## User guide:

I recommend to take a look at [Postman](https://www.getpostman.com/) in order to test the REST API.
Maybe you can import my [collection](http://sir.serafeimsaratsis.me:8080/shop/Shopping_Requests.postman_collection.json), change the IP/Hostname if you want to test against your own service IP.
It's pretty straight forward, I have preset the structure of the input elements, you can pass your own input. Always you can connect to the DB and check if the intended actions have been done successfully.

End to end testing can be done via browser as the GUI needs to be tested that way. Unfortunately due to time constraints the GUI functionality is only partly delivered.
