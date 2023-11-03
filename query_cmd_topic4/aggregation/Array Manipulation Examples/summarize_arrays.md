## Summarising Arrays For First, Last, Minimum, Maximum & Average Values:

* Assuming I have a dataset containing information about students in a MongoDB database. My task is to write an aggregation query to create a new document that includes the student's name, the value of the first element, the value of the last element, the minimum value, the maximum value, and the average value of the "grades" array for each student.

```javascript
db.createCollection("students")

db.students.insertMany([
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5a"),
    "name": "John Doe",
    "grades": [85, 92, 78, 90, 95]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5b"),
    "name": "Alice Smith",
    "grades": [88, 94, 76, 87, 91]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5c"),
    "name": "Bob Johnson",
    "grades": [75, 89, 82, 93, 79]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5d"),
    "name": "Eva Brown",
    "grades": [90, 86, 94, 88, 91]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5e"),
    "name": "Charlie Wilson",
    "grades": [83, 91, 87, 89, 92]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5f"),
    "name": "Grace Davis",
    "grades": [92, 85, 88, 94, 87]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c60"),
    "name": "William Taylor",
    "grades": [87, 90, 93, 84, 89]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c61"),
    "name": "Sophia Martinez",
    "grades": [89, 82, 91, 86, 90]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c62"),
    "name": "James Lee",
    "grades": [84, 88, 86, 92, 87]
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c63"),
    "name": "Olivia Harris",
    "grades": [90, 87, 89, 91, 86]
  }
]);

var pipeline = [
  {
    $project: {
      name: 1,
      firstGrade: { $arrayElemAt: ["$grades", 0] },
      lastGrade: { $arrayElemAt: ["$grades", -1] },
      minGrade: { $min: "$grades" },
      maxGrade: { $max: "$grades" },
      avgGrade: { $avg: "$grades" }
    }
  }
];

db.students.aggregate(pipeline);
```