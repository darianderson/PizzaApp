# PizzaApp
Simple app to order pizza online.
#### version: __0.2.0__

### Get started

1. Download or Clone project:
    ```
    git clone https://github.com/darianderson/PizzaApp.git
    ``` 
1. SetUp database with script in `db-scripts` folder.
1. Import project as Maven:
     ```
     Import -> Import as Maven project
     ```
1. Check database connection. Got to persistence-mysql.properties in resources and setup proper user and password for db connection.
1. Run PizzaAppApplication.class



### Default user

user:     admin <br>
password: fun123

### Credentials

I want to thank so much to my girlfriend for inspiring me for this project. <br>
Thank you Daria, you my beast open source angel. <br>
&copy; Dmitry

### Authors
[darianderson](https://github.com/darianderson) main creator <br>
[dmitryblackwell](https://github.com/dmitryblackwell) was here =)


### Development History

##### Tasks for version 0.2:
- [x] Create OrderDAOImpl
- [x] Extend users table in db (add more columns with data)
- [x] Create UserDAOImpl
- [x] Create User page
- [x] Add ability to edit users data on user page
- [x] Add order button to pizza and drinks table
- [x] Add ability to order pizza and drinks
- [x] Add order page