(ns postman.helper
  (:require [monger.core :as mg]
            [monger.collection :as mc :exclude any?]
            [ring.adapter.jetty :refer [run-jetty]]
            [my-stuff-api.server :refer [app]]
            [cheshire.core :as json]
            [my-stuff-api.db.mongo.config :as config]))

(def base "http://localhost:3001")

(def port 3001)

(def server (atom nil))

(defn init-server [port]
  (swap! server
         (fn [_] (run-jetty app {:port port :join? false}))))

(defn stop-server []
  (.stop @server))

(defn to-json [data]
  {:content-type :json
   :body (json/generate-string data)
   :throw-exceptions false})

(defn clean-db []
  (let [conn (mg/connect config/mongo-uri)
        db   (mg/get-db conn config/mongo-db)]
    (mc/remove db "owners")
    (mc/remove db "places")
    (mc/remove db "stuffs")))