(ns sample-ring.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as res]))

;;(defn handler [request]
;;  {:status 200
;;   :headers {"Content-Type" "text/html"}
;;   :body "<h1>Hello, world!</h1>"})

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "<h1>Sample Ring rules them all!</h1>"
              "<ul><li>Request from IP: "
              (:remote-addr request)
              "</li><li>Request method: "
              (:request-method request)
              "</li><li>Headers: "
              (select-keys
                (:headers request)
                ["accept" "user-agent" "accept-encoding"])
              "</li><li>URI: "
              (:uri request)
              "</li><li>Query string: "
              (:query-string request)
              "</li></ul>")})

(def simple-res (res/response "Hello, world!"))
;; -> {:status 200,
;;     :headers {},
;;     :body "Hello, world!"}

(def json-res(res/response "{\"id\":1,\"content\":\"Hello, World!\"}"))
;; -> {:status 200,
;;     :headers {},
;;     :body "{\"id\":1,\"content\":\"Hello, World!\"}"}

;;(defn simple-handler [request] simple-res)
;;(defn simple-handler [request] (res/charset simple-res "utf-8"))
;;(defn simple-handler [request] json-res)
(defn simple-handler [request] (res/content-type json-res "application/json"))

(defn -main []
;;  (jetty/run-jetty handler
  (jetty/run-jetty simple-handler
                   {:port 3000}))
