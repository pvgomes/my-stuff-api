(ns my-stuff-api.db.mongo.config
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [com.mongodb MongoOptions ServerAddress]))

;;see http://clojuremongodb.info/articles/getting_started.html


;(let [conn (mg/connect {:host "127.0.0.1" :port 7878})
;      db   (mg/get-db conn "mystuff")])

(def mongo-uri "mongodb://127.0.0.1:27017/mystuff")
(def mongo-db "mystuff")

(defn connect []
  (let [{:keys [conn db]} (mg/connect-via-uri mongo-uri)]

    ;;(mc/insert-and-return db "owner" {:name "John" :email "john@gmail.com" :pass "123"})

    ;;(mc/find db "owner" {:name "John"})

    ;;(mc/find-maps db "stuffs")

    ;;find all with name macbook pro
    ;;(mc/find-maps db "stuffs" { :name "Macbook Pro " })
    )
  )



;; all in http://clojuremongodb.info/articles/getting_started.html

