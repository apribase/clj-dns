# clj-dns

Clojure DNS Library with [dnsjava](http://xbill.org/dnsjava)

## Installation

Add the following dependency to your `project.clj`

```clj
[net.apribase/clj-dns "0.1.0"]
```

## Lookup

clj-dns 0.1.0 implements only reverse-dns-lookup.

```clj
(ns foo.bar
  (:require [clj-dns.core :as dns]))

(dns/reverse-dns-lookup "192.30.252.129") ;; => github.com.
(dns/hostname "192.30.252.129") ;; => github.com

;; reverse-dns-lookup throw java.net.UnknonHostException
(try (dns/reverse-dns-lookup "192.30.252.129")
     (catch UnknownHostException e nil))

;; hostname function return a nil if the hostname not found.
(if-let [hostname (dns/hostname "192.30.252.129")]
  (println hostname)
  (println "hostname not found."))
```

## Differences from brweber2/clj-dns

[brweber2/clj-dns](http://github.com/brweber2/clj-dns)'s `reverse-dns-lookup` throws `UnknonHostException` with `printStackTrace` because [dnsjava](http://xbill.org/dnsjava)'s `DNSJavaNameService#invoke` is called by `DNSJavaNameServiceDescriptor`.

## Licence

Copyright (C) 2015 apribase

Distributed under the Eclipse Public License, the same as Clojure.

