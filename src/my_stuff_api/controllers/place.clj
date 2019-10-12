(ns my-stuff-api.controllers.place
  (:require [my-stuff-api.db.mongo.place :as place-storage]
            [cheshire.core :refer :all]))

(defn- parse-response
  [place-response]
  (assoc place-response :_id (str (:_id place-response))))

(defn all []
  (place-storage/all))

(defn by-place-id
  [place-id]
  (-> place-id
      (place-storage/by-place-id)
      (parse-response)))

(defn add
  [place]
  (-> place
     (place-storage/add-place)
     (parse-response)))
