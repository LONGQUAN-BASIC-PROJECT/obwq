git pull

mvn clean -DskipTests  install

kill -9 `ps -aux|grep java | grep pargam | awk '{print $2}'`

rm /home/obwq/pargam/apache-tomcat-7.0.55/webapps/obwq* -rf

cp /home/obwq/temp/obwq/obwq-web/target/obwq.war /home/obwq/pargam/apache-tomcat-7.0.55/webapps/

/home/obwq/pargam/apache-tomcat-7.0.55/bin/startup.sh 

