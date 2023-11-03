## Filtered Top Subset

* Suppose I have a dataset of articles from a news website. Each article has attributes such as title, author, publish date, and number of views. I want to find the authors with the highest number of views for the articles posted within the time range from date A to date B.

```javascript
use aggregation_db;
db.createCollection("articles")

db.articles.insertMany([
  {
    title: "How to Cook Delicious Meals",
    author: "John Smith",
    publishDate: new Date('2023-04-24'),
    views: 1000
  },
  {
    title: "JavaScript Programming Guide",
    author: "Mary Johnson",
    publishDate: new Date('2023-01-23'),
    views: 750
  },
  {
    title: "Top 10 Amazing Travel Destinations",
    author: "David Brown",
    publishDate: new Date('2023-06-22'),
    views: 1200
  },
  {
    title: "Improving Your Study Skills",
    author: "John Smith",
    publishDate: new Date('2023-08-21'),
    views: 850
  },
  {
    title: "Teamwork Skills in the Workplace",
    author: "Michael White",
    publishDate: new Date('2023-12-20'),
    views: 900
  }
]);

var startDate = new Date("2023-01-01");
var endDate = new Date("2023-12-30");
var pipeline = [
  {
    $match: {
      publishDate: {
        $gte: startDate,
        $lte: endDate
      }
    }
  },
  {
    $unwind: "$author"
  },
  {
    $group: {
      _id: "$author",
      totalViews: { $sum: "$views" }
    }
  },
  {
    $sort: { totalViews: -1 }
  },
  {
    $limit: 1
  }
];

db.articles.aggregate(pipeline);
```