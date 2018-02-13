FROM openjdk:8-alpine
# The caseid service runs on port 8080 per default.
EXPOSE 8080
# Create a working directory for our caseid service to run in.
RUN mkdir -p /opt/todo-app
WORKDIR /opt/todo-app
# Copy the redistributable
COPY target/Spring-TodoList-Simple-CQRS-0.0.1-SNAPSHOT.jar /opt/todo-app
# Run the caseid jar. Tell it to use the kubernetes profile.
CMD ["java", "-Dspring.profiles.active=k8s", "-jar", "Spring-TodoList-Simple-CQRS-0.0.1-SNAPSHOT.jar"]
