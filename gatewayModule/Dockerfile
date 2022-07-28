FROM tomcat:9.0.63
ADD target/ROOT.war /usr/local/tomcat/webapps/
EXPOSE 8082:8080
CMD ["catalina.sh", "run"]