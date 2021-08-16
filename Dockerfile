FROM openjdk:11
MAINTAINER  Alex Mokhov <qwertygoog@gmail.com>
ADD  build/libs/roadmap.jar /app/
CMD ["java","-jar","/app/roadmap.jar"]
EXPOSE 8080