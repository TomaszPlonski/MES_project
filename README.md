# MES_Project

This is simple MES (manufacturing execution system) application.
I used: Java, Spring Boot with (JPA, Validation, Web and Security starters), MySQL and lombock.

# What is my purpose?
The application allows you to manage the production by adding new orders, products, types of products and controlling at what stage of production a given product is.

The main functionality is the division of product production into stages. Each stage has an assumed production time, thanks to which the assumed production end time is known. However, if any of the stages is delayed, the application will provide a new estimated production end time.

# Bacis functionalites
Available to all logged users:
- List of all orders, with the option to filter only active orders or only completed orders
![alt text](https://github.com/TomaszPlonski/MES_project/blob/main/images_for_Readme/order_list.jpg?raw=true)
- List of all products for a given order
- List of attributes of given product
- List of stages of production of given product. Showing the completed dates of stages and their delays
![alt text](https://github.com/TomaszPlonski/MES_project/blob/main/images_for_Readme/product_details.jpg?raw=true)

Available to only factory role(login:factory pass:factory):
- Possibility of ending the active production stage. End date: now.

Available to only office role(login:office pass:office):
- Ability to add a type of products. Giving it production stages and attributes.


## Testing:
Only working days are included in the calculation (not including Saturdays and Sundays) but production dates in the data.sql do not include weekends. Therefore, when testing the application on different days of the week, it may turn out that the assumed end date of production is strange :)

# Scopes that can be developed in the future
- sending e-mails with notifications
- search for orders and products
- financial and efficiency charts of production<br>

<b>Therefore any comments and advice will be appreciated</b>
