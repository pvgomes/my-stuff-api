(ns my-stuff-api.core
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [my-stuff-api.config :as config]
            [my-stuff-api.webserver :as webserver]
            [my-stuff-api.service :as service]))

(def system (atom nil))

(defn- build-system-map []
  (component/system-map
    :config (config/new-config config/config-map)
    :routes  (service/new-routes #'my-stuff-api.service/routes)
    :http-server (component/using (webserver/new-webserver) [:config :routes])))

(defn -main
  [& args]
  (-> (build-system-map)
      (config/start-system! system)))
