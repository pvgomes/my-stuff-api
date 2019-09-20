(ns my-stuff-api.service
  (:require [datomic.api :as d]))

(def conn nil)

(defn add-stuff-owner [stuff-owner]
  @(d/transact conn [{:db/id (d/tempid :db.part/user)
                      :owner/name stuff-owner}]))

;(defn add-stuff [stuff owner-name]
;  (let [stuff-id (d/tempid :db/part/user)]
;    @(d/transact conn [])))

(defn find-all-stuff-owners []
  (d/q '[:find ?stuff-owner
         :where [_ :owner/name ?stuff-owner]]
       (d/db conn)))
