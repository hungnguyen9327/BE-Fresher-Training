## Multi-Field Join & One-to-Many

* Consider a MongoDB database with two collections, "users" and "orders". The "users" collection contains documents representing users, while the "orders" collection contains documents representing customer orders. My task is to write a MongoDB aggregation query to perform a multi-field join and one-to-many operation.

```javascript
db.createCollection("orders")
db.createCollection("user")

db.orders.insertMany([
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b0"),
    "product": "Product A",
    "quantity": 2
  },
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b0"),
    "product": "Product B",
    "quantity": 3
  },
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b2"),
    "product": "Product C",
    "quantity": 1
  },
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b1"),
    "product": "Product D",
    "quantity": 4
  },
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b2"),
    "product": "Product E",
    "quantity": 2
  },
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b2"),
    "product": "Product F",
    "quantity": 5
  },
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b2"),
    "product": "Product G",
    "quantity": 3
  },
  {
    "user_id": ObjectId("65380412b5ad15e34a8471b1"),
    "product": "Product H",
    "quantity": 1
  }
])

db.users.insertMany([
  {
    "name": "John Doe",
    "age": 30
  },
  {
    "name": "Alice Smith",
    "age": 25
  },
  {
    "name": "Bob Johnson",
    "age": 35
  }
])

var pipeline = [
  {
    $lookup: {
      from: "orders",
      localField: "_id",
      foreignField: "user_id",
      as: "orders"
    }
  },
  {
    $unwind: "$orders"
  },
  {
    $group: {
      _id: "$_id",
      name: { $first: "$name" },
      age: { $first: "$age" },
      orders: {
        $push: {
          _id: "$orders._id",
          product: "$orders.product",
          quantity: "$orders.quantity"
        }
      }
    }
  }
];

db.users.aggregate(pipeline);
```