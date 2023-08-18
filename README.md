## Workflows

### stg_customers

The **stg_customers** model represents initial customer data before any processing or analysis is applied.

### stg_payments

The **stg_payments** model captures raw payment data as it is received, prior to any additional processing.

### customers

The **customers** model encompasses comprehensive customer information, along with insights derived from their order history.

### orders

The **orders** model includes essential order details and insights derived from payment information.

### stg_orders

The **stg_orders** model represents initial order data before further processing takes place.


## Datasets

1. **employee**
This dataset contains information about employees, including their names, salaries, and locations. It is useful for analyzing employee performance, identifying high-performing employees, and making informed decisions about employee compensation.

2. **department**
This dataset provides information about the different departments in the organization, including their department IDs, office locations, and manager IDs. It is useful for analyzing department performance, identifying areas for improvement, and making informed decisions about departmental restructuring.

3. **raw_customers**
This dataset contains raw customer data, including customer IDs, first names, and last names. It is useful for creating customer profiles, analyzing customer behavior, and making informed decisions about customer engagement.

4. **raw_orders**
This dataset contains raw order data, including order IDs, user IDs, order dates, and order statuses. It is useful for analyzing order trends, identifying popular products, and making informed decisions
## Custom Methods

1. **Add**

The **Add** custom method is designed to add two numbers and return the result. By executing this method, we can obtain the sum of two numbers in a single step. This can be particularly useful in scenarios where we need to perform multiple addition operations, such as in financial calculations or statistical analysis.

2. **squared**

The **squared** custom method offers a straightforward way to obtain the square of two numbers. By applying this method, we efficiently compute the square of the provided numbers. This can be useful in various applications, such as calculating areas or volumes of geometric shapes or analyzing data that requires squared values.

## Jobs

1. **EmployeeTableRefreshJob**

The **EmployeeTableRefreshJob** is a scheduled task operating within the "dev_sql_db" fabric. This job plays a critical role in maintaining the accuracy of the Employee table within Databricks. It takes care of updating the table to accommodate new employees as well as addressing any potential attrition. By executing this job, we ensure that our employee-related insights remain up-to-date, contributing to effective human resource management and informed decision-making.
