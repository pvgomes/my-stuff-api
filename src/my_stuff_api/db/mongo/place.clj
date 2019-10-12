(ns my-stuff-api.db.mongo.place
  (:require [monger.core :as mg]
            [monger.collection :as mc :exclude any?]
            [my-stuff-api.db.mongo.config :as config])
  (:import org.bson.types.ObjectId))

(def coll "places")

(defn add-place [place]
  (let [conn (mg/connect config/mongo-uri)
        db (mg/get-db conn config/mongo-db)]
    (mc/insert-and-return db coll
                          place)))

(defn all []
  (let [conn (mg/connect config/mongo-uri)
        db   (mg/get-db conn config/mongo-db)]
    (mc/find-maps db coll)))

(defn by-place-id [place-id]
  (let [conn (mg/connect config/mongo-uri)
        db   (mg/get-db conn config/mongo-db)]
    (mc/find-one-as-map db coll { :_id (ObjectId. place-id) })))

(defn by-owner-id [owner-id]
  (let [conn (mg/connect config/mongo-uri)
        db   (mg/get-db conn config/mongo-db)]
    (mc/find-maps db coll { :owner owner-id })))