(ns clj-dns.core
  (:import [org.xbill.DNS Address Lookup ReverseMap Type])
  (:import [java.net InetAddress UnknownHostException]))


(defn- get-host-by-address [^bytes addr]
  (if-let [records (-> (InetAddress/getByAddress addr)
                       (ReverseMap/fromAddress)
                       (Lookup. Type/PTR)
                       (.run))]
    (-> (first records)
        (.getTarget)
        (.toString))
    (throw (UnknownHostException.))))


(defn- get-family [^String ip-address]
  (Address/familyOf (Address/getByAddress ip-address)))


(defn- ip-address-to-byte-array [^String ip-address]
  (Address/toByteArray ip-address (get-family ip-address)))


(defn reverse-dns-lookup [^String ip-address]
  (get-host-by-address (ip-address-to-byte-array ip-address)))


(defn hostname [^String ip-address]
  (try (->> (reverse-dns-lookup ip-address)
            (drop-last)
            (apply str))
       (catch UnknownHostException e nil)))

