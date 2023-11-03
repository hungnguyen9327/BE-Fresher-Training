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
	- `db.students.find.sort({money: 1, name: -1})` (acs by money, des by name)<br>
	- `db.students.find.sort({money: -1, name: 1})` (reversed)
* Not support:
	- _{scores: 1, info: 1}_
	- _{scores: -1, info: -1}_

### Multikey Indexes:

* Create an Index on an Array Field:
	- `db.students.createIndex( { scores: 1 } )` <br>
	- `db.students.find({ scores: { $elemMatch: { $gt: 50 } } })` (scores are integer arr)

* Create an Index on an Embedded Field in an Array:
	- `db.students.createIndex( { "scores.score": 1 } )` <br>
	- `db.students.find({ "scores.score": { $gt: 50 } })`

* Multikey Index Bounds:
	- Bounds Intersection:
		- `db.students.find({ scores: { $elemMatch: { $gte: 40, $lte: 60 } } })` (intersect) <br>
		- `db.students.find({ scores: { $gte: 40, $lte: 60 } }) (not intersect)`
	- Compound Bounds:
		- ``db.students.createIndex( { name: 1, scores: 1 } ) 
	db.students.find( { name: "Zuk", scores: { $gte: 15 } } )`` (non-arr field & arr fields) <br>
		- ``db.students.createIndex( { name: 1, "scores.score": 1, "scores.type": 1 } ) 
	db.students.find( { name: "Zuk", "scores.score": { $gte: 15 }, "scores.type": "exam" } )`` (non-arr field & mul arr fields) 

### Text Indexes:

* Create a Text Index:
	- Create a Single-Field Text Index:
		- `db.students.createIndex( { "name": "text" } )` <br>
		- `db.students.find({ $text: { $search: "Zuk" } })`

	- Create a Compound Text Index:
		- `db.students.createIndex( { "name": "text", "about": "text" } )` <br>
		- `db.students.find({ $text: { $search: "cor" } })`

* Create a Wildcard Text Index:
	- `db.students.createIndex( { "$**": "text" } )` <br>
	- `db.students.find({ $text: { $search: "cor" } })` (single) <br>
	- `db.students.find({ $text: { $search: "cor orc" } })` (multiple) <br>
	- `db.students.find({ $text: { $search: "\"choco cr\"" } })` (exact phase)

* Specify the Default Language:
	- `db.students.createIndex({ about: "text" },{ default_language: "spanish" })` <br>
	- `db.students.find( { $text: { $search: "punal" } } )`<br>

### Wildcard Indexes:

* Create a Wildcard Index on a Single Field:
	- `db.collection.createIndex( { "<field>.$**": <sortOrder> } )`<br>

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
	- `db.<collection>.createIndex( { "$**": <sortOrder> } )`

* Create a Wildcard Index on All Fields:
Normally that used in case the document have already used an embedded document<br>
	- `db.students.createIndex({ "scores.$**": 1, wildcardProjection: { ... } })`<br>

* Compound Wildcard Indexes:
	- Omit _id field by default -> need to include it in the wildcardProjection:<br>
	- `db.students.createIndex({ "$**": 1 }, { "wildcardProjection": { _id: 1, ... } })`

	- There are some index that I need to create, it has the same _id field: <br>
		- ``{ _id: 1, “scores.type": 1 }
		{ _id: 1, “scores.score": 1 }``
	- I can compound it as below:<br>
		- `{ tenantId: 1, "scores.$**": 1 }`<br>

### Hashed Indexes:
(_Collect and store hashes of the values of the indexed field_)
`db.<collection>.createIndex( { <field>: "hashed" } )`<br>

* Create a Single-Field and Compound Hashed Index:
	- `db.students.createIndex( { _id: "hashed" } )`<br>
	- `db.students.createIndex( { name: "hashed", scores: -1 } )`<br>

	- After creating hashed index, I can shard that students collection:
		- ``sh.shardCollection(
		   "mytestdb.students",
		   { _id: "hashed" }
		)``
		- ``sh.shardCollection(
		   "mytestdb.students",
		   { name: "hashed", scores: -1 }
		)``

### Geospatial Indexes:<br>
(_Support query on data stored as GeoJSON or Coordinate pairs_)<br>
* 2dsphere Indexes (_Support geospatial queries on an earth-like sphere_): <br>
Init data values:
``
db.createCollection("places")
db.places.insertMany( [
   {
      loc: { type: "Point", coordinates: [ -73.97, 40.77 ] },
      name: "Central Park",
      category : "Park"
   },
   {
      loc: { type: "Point", coordinates: [ -73.88, 40.78 ] },
      name: "La Guardia Airport",
      category: "Airport"
   },
   {
      loc: { type: "Point", coordinates: [ -1.83, 51.18 ] },
      name: "Stonehenge",
      category : "Monument"
   }
] )
``<br>
	- `db.<collection>.createIndex( { <location field> : "2dsphere" } )`<br>
	- Create: `db.<collection>.createIndex( { <location field> : "2dsphere" } )`<br>
	- Example: `db.places.createIndex( { loc : "2dsphere" } )`<br>
	- Query (_User $geometry operator to identify that "location"_):

		1. Query for Locations Bound by a Polygon (_Use the $geoWithin operator and specify the coordinates of the polygon's vertices_):
			- ``db.places.find( {
			   loc: {
			      $geoWithin: {
			         $geometry: {
			            type: "Polygon",
			            coordinates: [ [
			               [ -73.95, 40.80 ],
			               [ -73.94, 40.79 ],
			               [ -73.97, 40.76 ],
			               [ -73.98, 40.76 ],
			               [ -73.95, 40.80 ]
			            ] ]
			          }
			      }
			   }
			} )``<br>
			- Result: <br>
			 ``
			[{
			      loc: { type: "Point", coordinates: [ -73.97, 40.77 ] },
			      name: "Central Park",
			      category : "Park"
			   }]``<br>
		2. Query for Locations Near a Point on a Sphere (_Query for location data near a specified point by using the $near operator. Additional, use $maxDistance to limit range of location we need to query_): <br>

			 - ``db.places.find( {
			   loc: {
			      $near: {
			         $geometry: {
			            type: "Point",
			            coordinates: [ -73.92, 40.78 ]
			         },
			         $maxDistance : 5000
			      }
			   }
			} )``<br>
			- Result: <br>
				``
				[
				{
				loc: { type: 'Point', coordinates: [ -73.88, 40.78 ] },
				    name: 'La Guardia Airport',
				    category: 'Airport'
				},
				{
				loc: { type: 'Point', coordinates: [ -73.97, 40.77 ] },
				    name: 'Central Park',
				    category: 'Park
				}
				]
				``<br>

		3. Query for Locations within a Circle on a Sphere (_Query for location data within a circle on a sphere by using $geoWithin with the $centerSphere operator. $centerSphere has a coordinate that is coordinate origin and radius value_):
		 	- ``db.places.find( {
			   loc: {
			      $geoWithin: {
			         $centerSphere: [
			            [ -1.76, 51.16 ],
			            10 / 6378.1
			         ]
			      }
			   }
			} )`` <br>
			- Result: <br>
				- ``[
					{
				loc: { type: 'Point', coordinates: [ -1.83, 51.18 ] },
				     name: 'Stonehenge',
				     category: 'Monument'
				}
				]``

		4. Query for Locations that Intersect a GeoJSON Object (_Query for location data that intersects with a GeoJSON object by using the $geoIntersects operator. Set Coodinates is presenting a road - linestring_):
			- Init values:
				- ``db.createCollection("stations")
				db.stations.insertMany( [
				   {
				      loc: { type: "Point", coordinates: [ -106.31, 35.65 ] },
				      state: "New Mexico",
				      country: "United States",
				      name: "Horizons Gas Station"
				   },
				   {
				      loc: { type: "Point", coordinates: [ -122.62, 40.75 ] },
				      state: "California",
				      country: "United States",
				      name: "Car and Truck Rest Area"
				   },
				   {
				      loc: { type: "Point", coordinates: [ -72.71, 44.15 ] },
				      state: "Vermont",
				      country: "United States",
				      name: "Ready Gas and Snacks"
				   }
				] )``
			- ``db.gasStations.find( {
			   loc: {
			      $geoIntersects: {
			         $geometry: {
			            type: "LineString",
			            coordinates: [
			               [ -105.82, 33.87 ],
			               [ -106.01, 34.09 ],
			               [ -106.31, 35.65 ],
			               [ -107.39, 35.98 ]
			            ]
			          }
			      }
			   }
			} )``
			- Result: <br>
   			``[
				{
					loc: { type: 'Point', coordinates: [ -106.31, 35.65 ] },
				     	state: 'New Mexico',
				     	country: 'United States',
				     	name: 'Horizons Gas Station'
				}
		     	]``


Init data:
`db.createCollection("contacts")`
``
db.contacts.insertMany( [
   {
      name: "Evander Otylia",
      phone: "202-555-0193",
      address: [ 55.5, 42.3 ]
   },
   {
      name: "Georgine Lestaw",
      phone: "714-555-0107",
      address: [ -74, 44.74 ]
   }
] )
``
* 2d Indexes:
	`db.<collection>.createIndex( { <location field> : "2d" } )`
	- Simple creation: `db.<collection>.createIndex( { <location field> : "2dsphere" } )`<br>
	- Example: `db.places.createIndex( { loc : "2dsphere" } )`<br>
	- Create a 2d Index:
		1. Define Location Precision for a 2d Index (_bits value between 1 and 32_):
    			- ``db.contacts.createIndex(
			   { address: "2d" },
			   { bits: 32 }
			)`` 
    		2. Define Location Range for a 2d Index (_by default, boundaries value of longitude and latitude is set by -180 and 180_):
        		- ``
			db.contacts.createIndex(
			   {
			      address: "2d"
			   },
			   {
			      min: -75,
			      max: 60
			   }
			)``
   			- After create above index, if inserting address value is out of range (-75, 60), that will not be inserted. For example:
      			- ``
				db.contacts.insertOne(
				   {
				      name: "Paige Polson",
				      phone: "402-555-0190",
				      address: [ -80, 47.3 ]
				   }
				)
           		``
	- Query a 2d Index:
		- Default created index: `db.contacts.createIndex( { address: "2d" } )` <br>
		1. Query for Locations Near a Point on a Flat Surface (_Query for location data near a specified point by using the $near operator_):
    			- ``
			     db.contacts.find( {
			   address: {
			      $near: [ 50.7, 68.9 ],
			      $maxDistance : 50
			   }
			} )``
			- Result: <br> ``
			[
				{
			      name: "Evander Otylia",
			      phone: "202-555-0193",
			      address: [ 55.5, 42.3 ]
			   },
			]
     			``
		2. Query for Locations within a Shape on a Flat Surface (_Query for location data within a specified shape on a flat surface by using the $geoWithin operator_):
			- ``
				db.contacts.find( {
				   address: {
				      $geoWithin: {
				         $box: [ [ -80, 29.3 ], [ 49.7, 50.3 ] ]
				      }
				   }
				} )
     			``
     			- Result:
      			``
			[
				{
			      name: "Georgine Lestaw",
			      phone: "714-555-0107",
			      address: [ -74, 44.74 ]
			   }
			]
   			``
## Index properties:
### Unique Indexes (_Ensures that the indexed fields do not store duplicate values_):
* Create a Unique Index:
  	- `db.collection.createIndex( <key and index type specification>, { unique: true } )`
	- Example: `db.students.createIndex( { "name": 1 }, { unique: true } )` (Single Field) <br>
* Unique Compound Index:
  	- `db.students.createIndex( { "scores.score": 1, "scores.type": 1 }, { unique: true } )`
* Behavior:
  	- Restrictions: cannot create a unique index on the specified index field(s) if the collection already contains data
 	- Unique Constraint Across Separate Documents (example from  _unique compound index_):
  		- `db.students.insertOne( { scores: [ { score: "50", type: "exam" } ] } )`
    		- `db.students.insertOne( { scores: [ { score: "50" } ] } )`
		- `db.students.insertOne( { scores: [ { type: "exam" } ] } )`
    		- => Allow insert data from 3 row above
	- Missing Document Field in a Unique Single-Field Index:
		- Only accept 1 null value in the same field. If it has more than 1, The operation fails will be showed.
  		- `db.students.insertOne( { scores: [ { score: "50", type: "exam" } ] } )`
		- `db.students.insertOne( { scores: [ { type: "exam" } ] } )`
		- `db.students.insertOne( { scores: [ { type: "exercise" } ] } )` => fail <br>
	- Missing Document Fields in a Unique Compound Index: Has the same rule with single-field from above
		- All missing in the same field can be accepted
	- Unique Partial Indexes:
		- Only accept 1 null value in the same field. If it has more than 1, The operation fails will be showed.
  		- `db.students.insertOne( { scores: [ { score: "50", type: "exam" } ] } )`
		- `db.students.insertOne( { scores: [ { type: "exam" } ] } )`
		- `db.students.insertOne( { scores: [ { type: "exercise" } ] } )` => fail <br>	
	- Basic and Unique Indexes With Duplicate Key Patterns: basic and unique indexes can exist with the same key pattern

### TTL Indexes (_Ensures that the indexed fields do not store duplicate values_):
* Create a TTL Index (_Automatically remove documents from a collection after a certain amount of time or at a specific clock time, (expireAfterSeconds field value within 0-2147483647)_):
  	- For example, we have _expiredAt_ field in _students_ collection, then we can create an ttl index with expire time is 0s for _expiredAt_ field as below:
  	- `db.students.createIndex( { "expiredAt": 1 }, { expireAfterSeconds: 0 } )`
	- `db.students.insertOne( { "expiredAt": new Date(), name: "test", scores: [{type: "exam", score: 40}]} )`

(_Change 'a non-TTL single-field index to a TTL index' and 'the expireAfterSecondsvalue for TTL index'  by using the 'collMod' database command_)
* Convert a non-TTL single-field Index into a TTL Index:
  	- ``
  	  db.runCommand({
	  "collMod": "students",
	  "index": {
	    "keyPattern": { "modifiedAt": 1 },
	    "expireAfterSeconds": 240
	  }
	})``
* Change the expireAfterSeconds value for a TTL Index:
	- ``
  	  db.runCommand({
	  "collMod": "students",
	  "index": {
	    "keyPattern": { "modifiedAt": 1 },
	    "expireAfterSeconds": 480
	  }
	})``
* Indexes Configured Using NaN: dont set expireAfterSeconds to NaN in TTL index configuration.
* Restrictions:
  	- TTL index is _single field indexes_, isn't supported by _compound indexes_ and this indexes ignore the expireAfterSeconds option.
  	- Dont support TTL index for __id_ field.
	- Cannot create TTL index for capped collection.
	- Cannot change expireAfterSeconds by createIndex, it has to be changed by 'collMod' database command.
	- Cannot create TTL index for a field when it had already created the Non-TTL index on that field. In order to change, using 'collMod' database command.

### Hidden Indexes (_Hide a values fields while querying. Not be used to support query_):
* Behavior:
  	- Still apply unique constraints if hidden index is unique index.
  	- Still expire documents if hidden index is TTL index.
  	- Included in _listIndexes_ and _db.collection.getIndexes()_ results.
  	- Updated upon write operations to the collection and continue to consume disk space and memory.
  	- Hide or unhide index will reset its $indexStats.
  	- And hide an already hidden or unhide an already unhide index wont reset its $indexStats. 
* Restrictions:
	- Cannot set index to hide _id field.
	- Cannot use _cursor.hint()_ hidden index.
* Create a Hidden Index:
	- ``
	db.students.createIndex(
	   { scores: 1 },
	   { hidden: true }
	)``
* Hide a Existing Index:
	- `db.students.hideIndex( { scores: 1 } );` (or by index name)<br>
* Unhide an Existing Index:
  	- `db.students.unhideIndex( { scores: 1 } );` (or by index name)<br>

### Partial Indexes (_Index the documents in a collection that meet a specified filter expression_):
* Create a Partial Index:
	- ``
	db.students.createIndex(
	   { "scores.score": 1 },
	   { partialFilterExpression: { name: { $gt: 'a' } } }
	)``
* Behavior:
  	- Query Coverage: from above example, I can create a find command as below (it still match with existing index we have created):
	  	- ``
		db.students.find(
		   {
			"scores.score": { $gt: 50 },
			name: { $gt: 'b' }
			}
		)``
	- Comparison with Sparse Indexes: 
* Restrictions:
  	- Cannot specify both the _partialFilterExpression_ option and the _sparse_ option.
  	- Cannot create partial index for _id
  	  
* Partial Index with Unique Constraint (_Unique constraint only applies to the documents that meet the filter expression from partial index_):
  	- ``
  	  db.student.createIndex(
	   { name: 1 },
	   { unique: true, partialFilterExpression: { age: { $gte: 21 } } }
	)``
	 
### Sparse Indexes (_Only contain entries for documents that have the indexed field, even if the index field contains a null value_):
* Create a Sparse Index:
	- `db.students.createIndex( { "age": 1 }, { sparse: true } )`
* Create a Sparse Index On A Collection:
	- From above create Sparse Index, I create a find command match with that index, for example:
		- `db.students.find( { age: { $lt: 10 } } )` (Only document has age field and has age value that greater than 10) <br>
* Sparse Index On A Collection Cannot Return Complete Results:
	- `db.students.find().sort( { age: -1 } )` (not using the existing sparse index I have created from above -> get all document from students) <br>
	- `db.students.find().sort( { age: -1 } ).hint({ age: 1 })` (using _hint()_ to specify the index -> only document having age field will be resulted) <br>
* Sparse Index with Unique Constraint (_With unique constraint, index will only apply non-duplicate value of field we set from sparse index_):
  	- `db.students.createIndex( { age: 1 } , { sparse: true, unique: true } )`
* Sparse and Non-Sparse Unique Indexes:
  	- Unique sparse and unique non-sparse exist with the same _key pattern_ on single document.
	- `db.students.createIndex( { age : 1 }, { name: "unique_index", unique: true } )`
	- `db.students.createIndex( { age : 1 }, { name: "unique_sparse_index", unique: true, sparse: true } )`
	
### Case Insensitive Indexes (_Support queries that perform string comparisons without regard for case_):
* Create a case insensitive index by specifying the _collation_ parameter as an option:
	-``db.collection.createIndex( { "key" : 1 },
	                           { collation: {
	                               locale : <locale>,
	                               strength : <strength>
	                             }
	} )``
*  To specify a collation for a case sensitive index, include:
  	- locale: specifies language rules.
   	- strength: determines comparison rules. A value of 1 or 2 indicates a case insensitive collation.	 
* Create a Case Insensitive Index:
	- ``db.students.createIndex( { name: 1},
                      { collation: { locale: 'en', strength: 2 } } )``
   	- ``db.students.insertMany( [
	   { name: "John" },
	   { name: "JoHN" },
	   { name: "jOhN" }
	] )``
	- `db.students.find( { name: "John" } )` (_only 1 result found_) <br>
	- `db.students.find( { name: "John" } ).collation( { locale: 'en', strength: 1 } )` (_using index and 3 result found__) <br>
	- `db.students.find( { name: "John" } ).collation( { locale: 'en', strength: 2 } )` (_not using index and 3 result found_) <br>
* Case Insensitive Indexes on Collections with a Default Collation:
  	- Assuming, I have created the _students_ collection and config it with default collation as below:
  	- `db.createCollection("students", { collation: { locale: 'en_US', strength: 2 } } )`
	- So, all index that we have created in this collection will be inherit from default collation, which I created before
	- `db.students.createIndex( { name: 1 } )` (_inherit default collation_) <br>
 	- `db.names.find( { name: "John" } )` (_3 result found_) <br>

