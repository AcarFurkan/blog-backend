FROM openjdk:18

COPY blog_out.jar blog.jar

ENTRYPOINT ["java","-jar","/blog.jar"]