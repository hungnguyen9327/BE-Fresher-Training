## Distinct List of Values

* Suppose I have a MongoDB collection that contains information about books in a library. My task is to write a MongoDB aggregation query to retrieve a distinct list of genres available in the collection.

```javascript
db.createCollection("books")

db.books.insertMany([
  {
    "title": "Book A",
    "author": "Author X",
    "genre": "Genre 1"
  },
  {
    "title": "Book B",
    "author": "Author Y",
    "genre": "Genre 2"
  },
  {
    "title": "Book C",
    "author": "Author Z",
    "genre": "Genre 1"
  },
  {
    "title": "Book D",
    "author": "Author X",
    "genre": "Genre 3"
  },
  {
    "title": "Book E",
    "author": "Author Y",
    "genre": "Genre 2"
  },
  {
    "title": "Book F",
    "author": "Author Z",
    "genre": "Genre 3"
  },
  {
    "title": "Book G",
    "author": "Author X",
    "genre": "Genre 2"
  },
  {
    "title": "Book H",
    "author": "Author Y",
    "genre": "Genre 1"
  }
])

var pipeline = [
  {
    $group: {
      _id: "$genre"
    }
  },
  {
    $project: {
      _id: 0,
      genre: "$_id"
    }
  }
];
db.books.aggregate(pipeline);
```