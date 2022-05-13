# Problem

Create my own streaming-data-pipeline similar to the one from 1904labs

## Data Source

Data Source: Earthquake data

- ideally we would have a stream, I'm not familiar with finding data sources that are already streams, pubsub architecture (I think I know what it is, I've just never done it)

what I'm going to do:

USGS Earthquake data there are some resources that are updated literally every minute with earthquake data

[USGS Real-time Notifications, Feeds, and Web Services](https://earthquake.usgs.gov/earthquakes/feed/)

Using this data I can kind of mockup my own data stream of new, unique earthquake data.

Specifically requesting a CSV of the last hour events every minute, two minutes, five or ten. I can get data to dump into a Kafka topic.

[USGS Spreadsheet homepage](https://earthquake.usgs.gov/earthquakes/feed/v1.0/csv.php)

consumption target (URL): `https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.csv`

This results in a CSV that is updated every minute, of geological events (earthquakes).

### Considerations

If I make a request every minute, two, five, ten (whatever) I will be getting a dataset that includes data I've already logged into the Kafka topic. I don't want to duplicate data and will need a way to determine the data is **unique** before it goes into the topic.

This is most similar to what you consider a **log** to be, there shouldn't be duplicates with regards to geological events.


### Plan

1. Something to consume the **consumption target** on a recurring schedule every 5 mins.
1. Take the CSV and create a hash out of all values
  a. check file for existing hash
    - if hash exists in file, throw away the event
    - if hash **does not exist** in file, send file to Kafka topic

That's a combo punch, two apps one to request the data, the second to validate the data against a store of hashes & pass it to Kafka Topic via Kafka Producer.

### Dependencies

Kafka server up and running
Kafka topic created to house the geological event data
Connect the Kafka Producer to the Kafka topic (in the secondary application)

## NExt Step

After massaging the data source to act like a data stream, and then adding it to a Kafka topic, I'd like to play with a Kafka Consumer --> but I'm not sure what to do yet. Simply printing out each new event is probably fine to start with.

But let's figure out how to crawl before we talk about running.

## 
