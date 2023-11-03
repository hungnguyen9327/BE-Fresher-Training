## Compound Text Search Criteria:

* Assume you have a dataset containing information about books in a MongoDB collection. My task is to write an aggregation query to search for books that match multiple text search criteria on the "title" and "description" fields. Specifically, you need to find books that contain both the word "wealthy" in the "description" field and the word "Gatsby" in the "title" field.

```javascript
db.createCollection("books")

db.books.insertMany([
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5a"),
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "description": "The story of the fabulously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan."
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5b"),
    "title": "To Kill a Mockingbird",
    "author": "Harper Lee",
    "description": "The story of racial injustice in the American South during the 1930s."
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5c"),
    "title": "1984",
    "author": "George Orwell",
    "description": "A dystopian novel that explores the consequences of totalitarianism."
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5d"),
    "title": "Pride and Prejudice",
    "author": "Jane Austen",
    "description": "A classic romance novel that explores societal norms and love."
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5e"),
    "title": "Moby-Dick",
    "author": "Herman Melville",
    "description": "The epic tale of Captain Ahab's obsessive quest for the white whale."
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c5f"),
    "title": "The Catcher in the Rye",
    "author": "J.D. Salinger",
    "description": "The story of Holden Caulfield's experiences in New York City."
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c60"),
    "title": "Brave New World",
    "author": "Aldous Huxley",
    "description": "A dystopian novel that explores a futuristic, totalitarian society."
  },
  {
    "_id": ObjectId("5f8d2d31a2e92c001e9e9c61"),
    "title": "The Lord of the Rings",
    "author": "J.R.R. Tolkien",
    "description": "An epic fantasy trilogy set in the world of Middle-earth."
  }
]);

db.books.createIndex({
  "title": "text",
  "description": "text"
})

var pipeline = [
  {
    "$search": {
        "index": "default",    
        "compound": {
          "must": [
            {"text": {
              "path": "description",
              "query": "novel",
            }},
          ],
          "should": [
            {"text": {
              "path": "description",
              "query": "society",
            }},
          ],
          "mustNot": [
            {"text": {
              "path": "description",
              "query": "quest",
            }},
          ],
          "filter": [
            {"text": {
              "path": "title",
              "query": "Moby-Dick",
            }},      
          ],
        }
      }
  }
]

db.books.aggregate(pipeline);
```