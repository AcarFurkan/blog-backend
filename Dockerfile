FROM openjdk:18

COPY blog-0.0.1-SNAPSHOT.jar blog.jar

ENTRYPOINT ["java","-jar","/blog.jar"]