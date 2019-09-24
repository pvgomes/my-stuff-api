# my-stuff-api


#### Run
`lein run`

#### Endpoints
`curl http://localhost:8080`


#### Setup
We use [lein-datomic](https://github.com/johnwayner/lein-datomic) follow the steps to install datomic


Add this in your ~./lein/profiles.clj
```clojure
{:user
 {:plugins [[lein-ancient "0.6.15"]
            [lein-autoexpect "1.9.0"]
			[lein-datomic "0.2.0"]]}}
``` 

**run datomic and init schema**
`lein datomic start &`
`lein datomic initialize`

**run tests**

`lein autoexpect`