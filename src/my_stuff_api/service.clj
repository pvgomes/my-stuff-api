(ns my-stuff-api.service
  (:require [datomic.api :as d]))


;run first time only
;(def uri "datomic:free://localhost:4334/my-stuff-db?password=datomic")
;(d/create-database uri)
;(def conn (d/connect uri))
;(d/transact conn (load-file "resources/datomic/schema.edn"))


;; --------- Database connection --------
(def uri "datomic:free://localhost:4334/my-stuff-db?password=datomic")
(def conn (d/connect uri))

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



;; repl tests
(comment


  (add-stuff-owner "Paulo")
  (add-stuff-owner "Andréia")
  (add-stuff "Macbook" "Paulo")
  (add-stuff "Mouse" "Paulo")
  (find-all-stuff-owners)
  (find-stuffs-for-owner "Paulo")

  )