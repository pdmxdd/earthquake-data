#!/bin/bash

if [ ! -d ~/.earthquake-data ]; then
mkdir ~/.earthquake-data
fi

if [ ! -f ~/.earthquake-data/hashes ]; then
touch ~/.earthquake-data/hashes
fi

if [ ! -f ~/.earthquake-data/exactly-once-eq-data.csv ]; then
echo "time,latitude,longitude,depth,mag,magType,nst,gap,dmin,rms,net,id,updated,place,type,horizontalError,depthError,magError,magNst,status,locationSource,magSource" > ~/.earthquake-data/exactly-once-eq-data.csv
fi

curl -s https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.csv > ~/.earthquake-data/eq-data.csv

/usr/bin/python3 ~/personal/earthquake-data/main.py