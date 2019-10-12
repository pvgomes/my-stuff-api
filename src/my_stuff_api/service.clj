(ns my-stuff-api.service
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]
            [my-stuff-api.controllers.owner :as owner]
            [my-stuff-api.controllers.place :as place]))

(defn to-json [content & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string content)})


(defroutes app-routes
           (GET "/" [] (to-json {:message "It works"}))
           (POST "/owner" owner-request
             (-> (:body owner-request)
                 (owner/add)
                 (to-json 200)))
           (GET "/owner" [] (to-json (owner/all)))
           (GET "/owner/:id" [id]
             (-> id
                 (owner/by-owner-id)
                 (to-json)))
           (GET "/owner/:id/places" [id]
             (-> id
                 (owner/owner-places)
                 (to-json)))
           (POST "/place" place-request
             (-> (:body place-request)
                 (place/add)
                 (to-json 200)))
           (GET "/place" [] (to-json (place/all)))
           (GET "/place/:id" [id]
             (-> id
                 (place/by-place-id)
                 (to-json)))
           (route/not-found "Not found"))
