## Group & Total

* Suppose I have a database named "orders" that contains information about orders in an online store. I want to calculate the total revenue for each month in a year and sort the results in ascending order of the month.

```javascript
use aggregation_db;
db.createCollection("orders")

db.orders.insertMany([
  {
    orderId: "O1001",
    customerId: "C001",
    products: [
      { productId: "P001", quantity: 5 },
      { productId: "P002", quantity: 2 },
      { productId: "P003", quantity: 1 }
    ],
    totalPrice: 99.95,
    orderDate: new Date('2023-03-24')
  },
  {
    orderId: "O1002",
    customerId: "C002",
    products: [
      { productId: "P004", quantity: 3 },
      { productId: "P005", quantity: 4 }
    ],
    totalPrice: 75.96,
    orderDate: new Date('2023-10-23')
  },
  {
    orderId: "O1003",
    customerId: "C003",
    products: [
      { productId: "P006", quantity: 2 },
      { productId: "P007", quantity: 7 }
    ],
    totalPrice: 49.93,
    orderDate: new Date('2023-05-22')
  },
  {
    orderId: "O1004",
    customerId: "C004",
    products: [
      { productId: "P008", quantity: 1 },
      { productId: "P009", quantity: 3 },
      { productId: "P010", quantity: 2 }
    ],
    totalPrice: 149.91,
    orderDate: new Date('2023-12-24')
  }
]);

var startDate = new Date("2023-01-01");
var endDate = new Date("2023-12-30");

var pipeline = [
  {
    $group: {
      _id: { $month: "$orderDate" },
      totalRevenue: { $sum: "$totalPrice" }
    }
  },
  {
    $sort: { _id: 1 }
  }
];

db.orders.aggregate(pipeline);
```