## Pivot Array Items By A Key:

* Assume I have a dataset containing information about sales transactions in a MongoDB collection. My task is to write an aggregation query to transform this dataset into a new document that contains each product as a separate field, with its corresponding quantity, for each transaction. 

```javascript
db.createCollection("transactions")

db.transactions.insertMany([
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5a"),
    "transaction_id": "T001",
    "items": [
      { "product": "A", "quantity": 5 },
      { "product": "B", "quantity": 3 },
      { "product": "C", "quantity": 2 }
    ]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5b"),
    "transaction_id": "T002",
    "items": [
      { "product": "D", "quantity": 4 },
      { "product": "E", "quantity": 1 }
    ]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5c"),
    "transaction_id": "T003",
    "items": [
      { "product": "F", "quantity": 2 },
      { "product": "G", "quantity": 3 },
      { "product": "H", "quantity": 1 }
    ]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5d"),
    "transaction_id": "T004",
    "items": [
      { "product": "I", "quantity": 2 },
      { "product": "J", "quantity": 2 },
      { "product": "K", "quantity": 3 }
    ]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5e"),
    "transaction_id": "T005",
    "items": [
      { "product": "L", "quantity": 4 },
      { "product": "M", "quantity": 5 }
    ]
  }
])

var pipeline = [
  {
    $unwind: "$items"
  },
  {
    $group: {
      _id: {
        _id: "$_id",
        transaction_id: "$transaction_id"
      },
      products: {
        $push: {
          k: "$items.product",
          v: "$items.quantity"
        }
      }
    }
  },
  {
    $project: {
      _id: "$_id._id",
      transaction_id: "$_id.transaction_id",
      products: {
        $arrayToObject: "$products"
      }
    }
  }
];

db.transactions.aggregate(pipeline);
```