#!/bin/bash

wget https://gitlab.univ-nantes.fr/sunye-g/AlarmClock/repository/master/archive.tar.gz .
tar zxvf archive.tar.gz
rm archive.tar.gz
mv AlarmClock-master* AlarmClock
#- Download Alarm clock
#- unzip

#cp -R ../AlarmClock .
cd AlarmClock
mvn clean site install
cp -R src/* ../structurel/src/
cp -R target/site/apidocs ../fonctionnel/
cd ..
rm -rf AlarmClock
