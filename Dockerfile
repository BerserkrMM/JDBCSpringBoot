# syntax=docker/dockerfile:1
FROM openjdk:17
COPY build/classes/java/main/com/example/jdbcspringbootapp/ /tmp
WORKDIR /tmp
javac src.main.java.com.example.jdbcspringbootapp.JdbcSpringBootAppApplication.java
java -classpath out.artifacts.JDBCSpringBootApp_jar.JDBCSpringBootApp.jar src.main.java.com.example.jdbcspringbootapp.JdbcSpringBootAppApplication.java
