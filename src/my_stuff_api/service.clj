(ns my-stuff-api.service
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [schema.coerce :as coerce]
            [ring.util.response :as ring-resp]))

(defrecord Routes [routes]
  component/Lifecycle
  (start [this]
    (assoc this :routes routes))
  (stop  [this] (dissoc this :routes)))

(defn new-routes [routes] (map->Routes {:routes routes}))

(defn default
  [request]
  (ring-resp/response {:message "It Works"}))

(def common-interceptors
  [(body-params/body-params) http/json-body])

(def routes
  #{["/" :get (conj common-interceptors `default)]})
