# MVP

- [x] bash: get data as CSV
- [x] python3: read, hash, validate, write data
- [x] bash: add python3 script after getting data
- [x] cron: job to fire bash script every 5 minutes
- [x] start kafka server
- [x] create kafka topic
- [x] kafka producer to dump anything
- [x] kafka producer to dump geoEvent
- [x] create kafka producer to dump exactly-once geo data into topic
- [x] create kafka consumers that do a few basic things:
  - [x] load as case class & filter over certain magnitude
  

# FUTURE IDEAS:

- [] load as case class & filter events within 5 km of cities
- [] convert the python CSV record validator to scala?