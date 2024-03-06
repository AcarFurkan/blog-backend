FROM openjdk:18

COPY blog2.jar blog.jar

ENTRYPOINT ["java","-jar","/blog.jar"]