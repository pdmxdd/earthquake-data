# Welcome

Welcome to the very messy kafka geo-data project.

# Process

1. Bash Script runs every 5 minutes via cron
    1. script makes a cURL request to USGS for all geological events (earthquakes) in the past hour as CSV
    1. script fires a Python3 script which:
        1. combs through the CSV to determine if event has already been recorded via hashing
        1. if event **hasn't** been recorded it records the hash, and the event to a new CSV (achieving exactly-one)
1. Local Kafka Server started and `geo-events` topic created
1. Scala Kafka Producer reads in the exactly-one CSV and adds contents to `geo-events` topic
1. Scala Kafka Consumer:
    1. subscribed & polls to the `geo-events` topic
    1. converts each CSV record to a GeoEvent case class
    1. determines if the magnitude of the GeoEvent was greater than 3.9 and prints result if condition met

A fun little project to get my feet wet with Kafka. I relied a little too heavily on Python to massage requested data into a semi-like-stream, but I'm not actually achieving a data stream.

I think I'll try a slightly different project that uses a random data generator I have hosted that will create a more stream like system.