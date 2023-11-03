## One-to-One Join:

* Suppose I have two MongoDB collections named "users" and "profiles". My task is to write a MongoDB aggregation query to perform a one-to-one join between the "users" and "profiles" collections based on the "user_id" field. The resulting documents should include the "username", "email", "full_name", and "age" fields.


```javascript
db.createCollection("users")
db.createCollection("profiles")

db.users.insertMany([
  {
    "username": "john_doe",
    "email": "john@example.com"
  },
  {
    "username": "alice_smith",
    "email": "alice@example.com"
  },
  {
    "username": "bob_johnson",
    "email": "bob@example.com"
  }
])

db.profiles.insertMany([
  {
    "user_id": "6537fc64b5ad15e34a8471a6",
    "full_name": "John Doe",
    "age": 30
  },
  {
    "user_id": "6537fc64b5ad15e34a8471a4",
    "full_name": "Alice Smith",
    "age": 28
  },
  {
    "user_id": "6537fc64b5ad15e34a8471a5",
    "full_name": "Bob Johnson",
    "age": 35
  }
])

var pipeline = [
  {
    $lookup: {
      from: "profiles",
      localField: "_id",
      foreignField: "user_id",
      as: "profile"
    }
  },
  {
    $unwind: "$profile"
  },
  {
    $project: {
      _id: 0,
      username: 1,
      email: 1,
      "profile.full_name": 1,
      "profile.age": 1
    }
  }
];

db.users.aggregate(pipeline);
```