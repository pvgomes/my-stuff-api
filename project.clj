(defproject my-stuff-api "0.0.1"
  :description "my stuff app"
  :url "https://github.com/pvgomes/my-stuff-api"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.novemberain/monger "3.1.0"]
                 [compojure "1.6.1"]
                 [cheshire "5.8.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [clj-http "3.9.1"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler my-stuff-api.server/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]
                        [midje "1.9.6"]
                        [ring/ring-core "1.7.1"]
                        [ring/ring-jetty-adapter "1.7.1"]]
         :plugins [[lein-midje "3.2.1"]
                   [lein-cloverage "1.0.13"]]}}


  :test-paths ["postman/"])
