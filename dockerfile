# Use the official Tomcat image
FROM tomcat:10.1-jdk17

# Copy the WAR file to the Tomcat webapps directory
COPY target/weather-application-1.0.0.war /usr/local/tomcat/webapps/weather-application.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]