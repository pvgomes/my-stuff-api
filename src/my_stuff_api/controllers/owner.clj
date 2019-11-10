(ns my-stuff-api.controllers.owner
  (:require [my-stuff-api.db.mongo.owner :as owner-storage]
            [my-stuff-api.db.mongo.place :as place-storage]
            [cheshire.core :refer :all]))

(defn- parse-response
  [owner-response]
  (assoc owner-response :_id (str (:_id owner-response))))

(defn all []
  (->> (owner-storage/all)
      (map parse-response)))

(defn by-owner-id
  [owner-id]
  (-> owner-id
      (owner-storage/by-owner-id)
      (parse-response)))

(defn add
  [owner]
  (-> owner
     (owner-storage/add-owner)
     (parse-response)))

(defn owner-places
  [owner-id]
  (-> owner-id
      (place-storage/by-owner-id)
      (map)
      (str)))