(ns my-stuff-api.service-test
  (:require [clj-http.client :as http]
            [ring.mock.request :as mock]))


(facts "a owner" :unit
       (let [response (app (mock/request :get "/"))]
            (fact "the response status is 200"
                  (:status response) => 200)

            (fact "the response body is it works"
                  (:body response) => {:message "It works"})))

