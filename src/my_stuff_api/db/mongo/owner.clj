(ns my-stuff-api.db.mongo.owner
  (:require [monger.core :as mg]
            [monger.collection :as mc :exclude any?]
            [my-stuff-api.db.mongo.config :as config])
  (:import org.bson.types.ObjectId))

(def coll "owners")

(defn add-owner [owner]
  (let [conn (mg/connect config/mongo-uri)
        db (mg/get-db conn config/mongo-db)]
    (mc/insert-and-return db coll
                          owner)))

(defn all []
  (let [conn (mg/connect config/mongo-uri)
        db   (mg/get-db conn config/mongo-db)]
    (mc/find-maps db coll)))

(defn by-owner-id [owner-id]
  (let [conn (mg/connect config/mongo-uri)
        db   (mg/get-db conn config/mongo-db)]
    (mc/find-one-as-map db coll { :_id (ObjectId. owner-id) })))

