(ns my-stuff-api.service-test
  (:require [expectations :refer :all]
            [my-stuff-api.service :refer :all]
            [datomic.api :as d]))

(defn create-empty-in-memory-db []
  (let [uri "datomic:mem://my-stuff-api-test-db"]
    (d/delete-database uri)
    (d/create-database uri)
    (let [conn (d/connect uri)
          schema (load-file "resources/datomic/schema.edn")]
      (d/transact conn schema)
      conn)))

; Adding one owner should allow us to find that owner
(expect #{["Paulo"]}
        (with-redefs [conn (create-empty-in-memory-db)]
          (do
            (add-stuff-owner "Paulo")
            (find-all-stuff-owners))))

; Adding multiple owners should allow us to find all those owners
(expect #{["Paulo"] ["Andréia"] ["Marcella"] ["Vinicius"]}
        (with-redefs [conn (create-empty-in-memory-db)]
          (do
            (add-stuff-owner "Paulo")
            (add-stuff-owner "Andréia")
            (add-stuff-owner "Marcella")
            (add-stuff-owner "Vinicius")
            (find-all-stuff-owners))))


;; Adding one owner with one stuff should allow us to find that stuff for that owner
(expect #{["MacBook"]}
        (with-redefs [conn (create-empty-in-memory-db)]
          (do
            (add-stuff-owner "Paulo")
            (add-stuff "MacBook" "Paulo")
            (find-stuffs-for-owner "Paulo"))))


;; Adding multiple stuffs for a owners should allow us to find all stuffs
(expect #{["MacBook Pro"] ["MagicMouse"]}
        (with-redefs [conn (create-empty-in-memory-db)]
          (do
            (add-stuff-owner "Andréia")
            (add-stuff "MacBook" "Andréia")
            (add-stuff "MagicMouse" "Andréia")
            (add-stuff "USB Adapter" "Andréia")
            (find-stuffs-for-owner "Andréia")
            (add-stuff-owner "Paulo")
            (add-stuff "MacBook Pro" "Paulo")
            (add-stuff "MagicMouse" "Paulo")
            (find-stuffs-for-owner "Paulo"))))
