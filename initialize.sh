

#- Download Alarm clock
#- unzip

cp -R ../AlarmClock .
cd AlarmClock
mvn clean site install
cp -R src/main ../fonctionnel/src/
cp -R target/site/apidocs ../fonctionnel/
# - copy apidocs to fonctionnel

cd ..
#rm -rf AlarmClock
