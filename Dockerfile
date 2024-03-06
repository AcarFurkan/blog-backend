FROM openjdk:18

COPY blog-0.0.2-SNAPSHOT.jar blog.jar

ENTRYPOINT ["java","-jar","/blog.jar"]