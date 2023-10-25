## Unpack Arrays & Group Differently:

* Consider a MongoDB collection named "orders" that contains documents representing customer orders. My task is to write a MongoDB aggregation query to unpack the "products" array, group the documents by product names, and calculate the total revenue (quantity * price) for each product.

```javascript
use aggregation_db;
db.createCollection("orders")

db.orders.insertMany([
  {
    "customer": "John Doe",
    "products": [
      {
        "name": "Product A",
        "quantity": 2,
        "price": 10
      },
      {
        "name": "Product B",
        "quantity": 3,
        "price": 15
      }
    ],
    "orderDate": { "$date": "2022-01-01T00:00:00Z" }
  },
  {
    "customer": "Alice Smith",
    "products": [
      {
        "name": "Product C",
        "quantity": 1,
        "price": 20
      }
    ],
    "orderDate": { "$date": "2022-02-15T12:30:00Z" }
  },
  {
    "customer": "Bob Johnson",
    "products": [
      {
        "name": "Product D",
        "quantity": 4,
        "price": 8
      },
      {
        "name": "Product E",
        "quantity": 2,
        "price": 12
      }
    ],
    "orderDate": { "$date": "2022-03-20T10:15:00Z" }
  },
  {
    "customer": "Eva Brown",
    "products": [
      {
        "name": "Product F",
        "quantity": 2,
        "price": 25
      }
    ],
    "orderDate": { "$date": "2022-04-05T08:45:00Z" }
  },
  {
    "customer": "Charlie Wilson",
    "products": [
      {
        "name": "Product G",
        "quantity": 3,
        "price": 18
      }
    ],
    "orderDate": { "$date": "2022-05-10T16:20:00Z" }
  },
  {
    "customer": "Grace Davis",
    "products": [
      {
        "name": "Product H",
        "quantity": 1,
        "price": 30
      }
    ],
    "orderDate": { "$date": "2022-06-15T14:55:00Z" }
  },
  {
    "customer": "William Taylor",
    "products": [
      {
        "name": "Product I",
        "quantity": 2,
        "price": 22
      },
      {
        "name": "Product J",
        "quantity": 3,
        "price": 14
      }
    ],
    "orderDate": { "$date": "2022-07-20T09:10:00Z" }
  }
])

var pipeline = [
  { $unwind: "$products" },
  {
    $group: {
      _id: "$products.name",
      totalRevenue: { $sum: { $multiply: ["$products.quantity", "$products.price"] } }
    }
  }
];

db.orders.aggregate(pipeline);
```

