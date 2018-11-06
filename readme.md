Summary
===

This a small project showing how akka-stream and akka-http can be used
to build a reactive app.
Server Sent Event (SSE) is used. 

Note that not all browsers support this technology.
See [this link](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#EventSource)
for compatibility matrix.

Requires :
* Java
* scala
* sbt

how to run
===

Launch server backend using :
`sbt run`

Server should be listening on port `9000`

Testing
===

Routes can be found in file `src/main/scala/fr/drysoft/pocAkka/http/Router.scala`
This project is meant to be used with frontend project [poc-akka-react](https://github.com/remy-auricoste/poc-akka-react)

Launch unit tests with `sbt test`

