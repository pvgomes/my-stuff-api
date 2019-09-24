(ns my-stuff-api.service
  (:require [datomic.api :as d]))

(def conn nil)

;; Helper functions

(defn find-stuff-owner-id [owner-name]
  (ffirst (d/q '[:find ?eid
                 :in $ ?owner-name
                 :where [?eid :owner/name ?owner-name]]
               (d/db conn)
               owner-name)))


;; --------- Query functions

(defn add-stuff-owner [stuff-owner]
  @(d/transact conn [{:db/id (d/tempid :db.part/user)
                      :owner/name stuff-owner}]))

(defn add-stuff [stuff owner-name]
  (let [stuff-id (d/tempid :db.part/user)]
    @(d/transact conn [{:db/id stuff-id
                        :stuff/name stuff}
                       {:db/id (find-stuff-owner-id owner-name)
                        :owner/stuffs stuff-id}])))

(defn find-all-stuff-owners []
  (d/q '[:find ?stuff-owner
         :where [_ :owner/name ?stuff-owner]]
       (d/db conn)))

(defn find-stuffs-for-owner [owner-name]
  (d/q '[:find ?stuff-name
         :in $ ?owner-name
         :where [?eid :owner/name ?owner-name]
                [?eid :owner/stuffs ?stuff]
                [?stuff :stuff/name ?stuff-name]]
       (d/db conn)
       owner-name))