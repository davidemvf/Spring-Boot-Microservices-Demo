FROM tomcat:9.0.60
ADD target/ROOT.war /usr/local/tomcat/webapps/
EXPOSE 8081:8080
CMD ["catalina.sh", "run"]