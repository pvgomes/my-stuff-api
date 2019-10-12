(ns postman.default
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
            [my-stuff-api.server :refer :all]))

(facts "check if we can health check"
       (fact "the response status is 200"
             (let [response (app (mock/request :get "/"))]
               (:status response) => 200))

       (fact "the body should be 'Hello World'"
             (let [response (app (mock/request :get "/"))]
               (:body response) => "{\"message\":\"It works\"}")))

(facts "invalid routes and verbs as well"
       (fact "the response status must be 404"
             (let [response (app (mock/request :get "/invalid"))]
               (:status response) => 404))

       (fact "invalid response message"
             (let [response (app (mock/request :get "/invalid"))]
               (:body response) => "Not found")))

;(facts "all owners"
;       (fact "all owners response"
;             (let [response (app (mock/request :get "/owner"))]
;               (:body response) => 200)))