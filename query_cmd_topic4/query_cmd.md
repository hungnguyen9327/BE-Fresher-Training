# Init db and data

`use mytestdb` <br>
`db.createCollection("students")` <br>
``db.insert([
  {"_id":1,"name":"Dodie Staller","scores":[{"score":7.772386442858281,"type":"exam"},{"score":31.84300235104542,"type":"quiz"},{"score":80.52136407989194,"type":"homework"}]},
  {"_id":2,"name":"Fletcher Mcconnell","scores":[{"score":39.41011069729274,"type":"exam"},{"score":81.13270307809924,"type":"quiz"},{"score":97.70116640402922,"type":"homework"}]},
  {"_id":3,"name":"Verdell Sowinski","scores":[{"score":62.12870233109035,"type":"exam"},{"score":84.74586220889356,"type":"quiz"},{"score":81.58947824932574,"type":"homework"}]},
  {"_id":4,"name":"Gisela Levin","scores":[{"score":44.51211101958831,"type":"exam"},{"score":0.6578497966368002,"type":"quiz"},{"score":93.36341655949683,"type":"homework"}]},
  {"_id":5,"name":"Tambra Mercure","scores":[{"score":69.1565022533158,"type":"exam"},{"score":3.311794422000724,"type":"quiz"},{"score":45.03178973642521,"type":"homework"}]}
])
``

## Query in MongoDB

### 1. Find all documents in a collection:
`db.collection.find()` <br>
Example: `db.students.find()`

### 2. Find documents with the condition equal to:
`db.collection.find({ field: value })` <br>
Example: ` db.students.find({ name: "Salena Olmos"})`

### 3. Find documents with the condition greater (or less - _lt_) than: 
`db.collection.find({ field: { $gt: value } })` <br>
Example: <br>
``
db.students.find({_id: {$gt: 20}})
db.students.find({"scores":{score: {$gt: 50}, type: 'exam'}})
``

### 4. Find documents with the condition not equal to:
`db.collection.find({ field: { $ne: value } })` <br>
Example: `db.students.find({_id: {$ne: 5}})`

### 5. Find documents with the condition in (or not in - _$nin_) an array:
`db.collection.find({ field: { $in: [value1, value2] } })` <br>
Example: `db.students.find({name: { $in: [Gisela Levin, Tressa Schwing] }})`

### 6. Find documents with the AND (OR - _$or_) condition (combining multiple conditions):
`db.collection.find({ $and: [ { condition1 }, { condition2 } ] })`
Example: `db.students.find({ $and: [{ _id: {$ne: 5} }, { name: { $in: ['Gisela Levin', 'Tressa Schwing'] } }]})` <br>

### 7. Sort the results by a specific field:
`db.collection.find().sort({ field: 1 })` <br>
Example: ` db.students.find().sort({ _id: -1 })`

### 8. Limit the number of returned documents:
`db.collection.find().limit(number)` <br>
Example: ` db.students.find().limit(10)`

### 9. Find documents with text search:
`db.collection.find({ $text: { $search: "keyword" } })` <br>
Example: <br>
``
db.students.createIndex({"name": "text"})
db.students.find({ $text: { $search: "Cody" } })
``

### 10. Find documents with pagination:
`db.collection.find().skip(offset).limit(pageSize)` <br>
Example: `db.students.find().skip(10).limit(5)`

### 11. Find documents with date conditions:
`db.collection.find({ dateField: { $gte: startDate, $lte: endDate } })` <br>
Example: `db.students.find({ { createdAt:{$gte:ISODate("2023-01-01"),$lt:ISODate("2023-10-01"} } })`

### 12. Find documents and return only the necessary fields:
`db.collection.find({}, { field1: 1, field2: 1 })` <br>
Example: `db.students.find({}, { name: 1, scores: 1, _id: 0 })`

### 13. Find documents and group the results by a field:
`db.collection.aggregate([{ $group: { _id: "$field", count: { $sum: 1 } } }])` <br>
Example: `db.students.aggregate([{ $group: { _id: "$name", count: { $sum: 1 } } }])`

### 14. Find documents with regex (regular expression) condition:
`db.collection.find({ field: { $regex: "pattern" } })` <br>
Example: `db.students.find({ name: { $regex: "^aim" } })` 

### 15. Find documents with conditions and update them (findAndModify, findOneAndUpdate, findOneAndReplace): 
`db.collection.findAndModify({ query: { field: value }, sort: { field: 1 }, update: { $set: { field: value } }, new: true|false })` <br>
Example: <br>
``
db.students.findAndModify({
  query: { name: "Gisela Levin" },
  update: { $set: { name: "Gisela Levinnnn" } },
  new: true
})
``

### 16. Find documents and update them (by aggregation pipeline - _$set_, _$replaceWith_, ...) (update, updateOne, updateMany):
`db.collection.updateOne({ field: value })` <br>
Example: `db.students.updateOne({ _id: 3 }, [ { $set: { name: "Fletcher Mcconnell" } ] )`

### 17. Delete one or multiple documents (with conditions):
`db.collection.deleteMany({ field: value })` <br>
`db.collection.delete({ field: value })` <br>
Example: ``db.students.deleteOne({ name: "Fletcher Mcconnell" })``


## Command in MongoDB

### 1. Create a new user in the database:
`db.createUser({ user: "jaser2712", pwd: "hung2712", roles: ["readWrite"] })`

### 2. View the list of users in the database:
`db.getUsers()`

### 3. Rename a collection:
`db.students.renameCollection("employees")`

### 4. Remove a user from the database:
`db.dropUser("jaser2712")`

### 5. Check the status of the MongoDB server:
`db.serverStatus()`

### 6. Display the version of MongoDB:
`db.version()`

### 7. Check the memory usage of the server:
`db.runCommand({ serverStatus: 1, mem: 1 })`

### 8. Check the storage usage of a collection:
`db.runCommand({ collStats: "mycollection" })`

### 9. View information about the indexes of a collection:
`db.students.getIndexes()`

### 10. Remove a collection from the database:
`db.students.drop()`

### 11. Check the connection status to MongoDB:
`db.runCommand({ ping: 1 })`

### 12. Display information about active connections:
`db.currentOp()`

### 13. View the list of logged commands on the server:
`db.adminCommand({ getLog: "global" })`

### 14. View information about a specific index in a collection:
`db.students.getIndexKeys({ _id: 1 })`

### 15. View information about the collections in a database:
`db.getCollectionInfos()`

### 16. View information about a specific collection:
`db.getCollectionInfo("students")`

### 17. Create a view from an aggregation pipeline:
`db.createView("myview", "students", [{ $match: { name:  { $regex: "^aim" } } }])`

### 19. Remove a view from the database:
`db.myview.drop()`

### 20. Display the list of available commands in MongoDB:
`db.listCommands()`





