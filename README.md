# my-stuff-api


#### Setup database
`docker-compose up -d`

#### Setup api
`lein ring server-headless`

#### Endpoints
`curl http://localhost:3000`


**run tests**

`lein midje`

coverage

`lein cloverage --runner :midje`


### Mongodb
Connect to [mongo](https://docs.mongodb.com/manual/mongo/) db using cli to make some queries
```
docker exec -it mongodb bash
mongo --host localhost --port 27017
show dbs
use mystuff
db.owners.find()
db.owners.remove({})
```

In docker we use [mongoimport](https://docs.mongodb.com/manual/reference/program/mongoimport/) to upload mocks