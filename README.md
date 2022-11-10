# eshop

REST API for order administration

## Functionality

### By product:

+ Creating a product
+ Editing the product
+ Removing the product. You can't delete a product in the IN_STOCK and RUNNING_LOW statuses.
+ A list of all products, sorted from more orders to less. Return a list of values with the following fields (product
  name, price, number of orders by product).

### By order:

+ Creating an order. You cannot create an order without a list of products, with products in the OUT_OF_STOCK status and
  without a user Id.
+ Get a list of all orders for a period of time, sorted by user Id and order creation date.

## DB schema

<p align="center">
  <img src="https://github.com/Bobrv3/eshop/blob/main/src/main/resources/db/db_schema.png" width="500" title="db schema">
</p>
