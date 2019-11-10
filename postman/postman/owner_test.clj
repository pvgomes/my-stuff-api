(ns postman.owner-test
  (:require [midje.sweet :refer :all]
            [ring.adapter.jetty :refer [run-jetty]]
            [clj-http.client :as http]
            [postman.helper :as helper]))

(facts "owner fatcs"
       (against-background [(before :facts [(helper/init-server helper/port)
                                            (helper/clean-db)])
                            (after :facts (helper/stop-server))]


                           (fact "root path is http status 200" :aceitacao
                                 (:status (http/get (str helper/base "/"))) => 200)


                           (fact "add owner"
                                 (let [response (http/post (str helper/base "/owner")
                                                           (helper/to-json {:name  "Marko"
                                                                            :email "paulo@mystuff.com"
                                                                            :pass  "123"}))]
                                   (println str response)
                                   (:status response) => 200))

                           (fact "we must get a paulo owner"
                                 (:body (http/get (str helper/base "/owner/5da3b8acab42f56ed9d54b31"))) => "{\"name\":\"paulo id 10\"}")

                           ))
