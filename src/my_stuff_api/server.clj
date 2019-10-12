(ns my-stuff-api.server
  (:require [ring.middleware.defaults :refer [wrap-defaults
                                              api-defaults]]
            [ring.middleware.json :refer [wrap-json-body]]
            [my-stuff-api.service :as service]))


(def app
  (-> (wrap-defaults service/app-routes api-defaults)
      (wrap-json-body {:keywords? true :bigdecimals? true})))
