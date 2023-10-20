# Indexes

## Create an Index:
`db.collection.createIndex(keys, options)`

## Drop an Index (or all removable Indexes):
`db.collection.dropIndex("<indexName>")` (except default index) <br>
`db.collection.dropIndexes("<index1>", "<index2>", "<index3>", ...)` <br>
`db.collection.dropIndexes()` (except _id index) <br>


## Index types:

### Single Field Indexes:

* Create an Index on a Single Field:
`db.students.createIndex( { name: 1 } )`

* Create an Index on an Embedded Document:
`db.students.createIndex( { info: 1 } )`

* Create an Index on a Single Field:
`db.students.createIndex( { "info.phone": 1 } )`

### Compound Indexes:

`db.students.createIndex({money: 1, name: -1})`
_(only scores or both)_

* Sorting (default by index and its revervation):
`db.students.find.sort({money: 1, name: -1})` (acs by money, des by name)<br>
`db.students.find.sort({money: -1, name: 1})` (reversed)
* Not support:
	- _{scores: 1, info: 1}_
	- _{scores: -1, info: -1}_

### Multikey Indexes:

* Create an Index on an Array Field:
`db.students.createIndex( { scores: 1 } )` <br>
`db.students.find({ scores: { $elemMatch: { $gt: 50 } } })` (scores are integer arr)

* Create an Index on an Embedded Field in an Array:
`db.students.createIndex( { "scores.score": 1 } )` <br>
`db.students.find({ "scores.score": { $gt: 50 } })`

* Multikey Index Bounds:
	- Bounds Intersection:
	`db.students.find({ scores: { $elemMatch: { $gte: 40, $lte: 60 } } })` (intersect) <br>
	`db.students.find({ scores: { $gte: 40, $lte: 60 } }) (not intersect)`
	- Compound Bounds:
	``db.students.createIndex( { name: 1, scores: 1 } ) 
	db.students.find( { name: "Zuk", scores: { $gte: 15 } } )`` (non-arr field & arr fields) <br>
	``db.students.createIndex( { name: 1, "scores.score": 1, "scores.type": 1 } ) 
	db.students.find( { name: "Zuk", "scores.score": { $gte: 15 }, "scores.type": "exam" } )`` (non-arr field & mul arr fields) 

### Text Indexes:

* Create a Text Index:
	- Create a Single-Field Text Index:
	`db.students.createIndex( { "name": "text" } )` <br>
	`db.students.find({ $text: { $search: "Zuk" } })`

	- Create a Compound Text Index:
	`db.students.createIndex( { "name": "text", "about": "text" } )` <br>
	`db.students.find({ $text: { $search: "cor" } })`

* Create a Wildcard Text Index:
	`db.students.createIndex( { "$**": "text" } )` <br>
	`db.students.find({ $text: { $search: "cor" } })` (single) <br>
	`db.students.find({ $text: { $search: "cor orc" } })` (multiple) <br>
	`db.students.find({ $text: { $search: "\"choco cr\"" } })` (exact phase)

* Specify the Default Language:
	`db.students.createIndex({ about: "text" },{ default_language: "spanish" })` <br>
	`db.students.find( { $text: { $search: "punal" } } )`

### Wildcard Indexes:

* Create a Wildcard Index on a Single Field:
`db.collection.createIndex( { "<field>.$**": <sortOrder> } )`

* Include or Exclude Fields:
``db.collection.createIndex( 
	{ "$**": <sortOrder> },
	{
     	 	"wildcardProjection" : {
         		"<field1>" : < 0 | 1 >,
        	 	...
         		"<fieldN>" : < 0 | 1 >
      		}
  	}
)``

* Create a Wildcard Index on All Fields:
`db.<collection>.createIndex( { "$**": <sortOrder> } )`

* Create a Wildcard Index on All Field:
Normally that used in case the document have already used an embedded document
`db.students.createIndex({ "scores.$**": 1, wildcardProjection: { ... } })`