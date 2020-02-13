FROM openjdk:8-alpine
EXPOSE 8080
RUN mkdir -p /opt/todo-app
WORKDIR /opt/todo-app
COPY target/Spring-TodoList-Simple-CQRS-0.0.1-SNAPSHOT.jar /opt/todo-app
CMD ["java", "-jar", "Spring-TodoList-Simple-CQRS-0.0.1-SNAPSHOT.jar"]
