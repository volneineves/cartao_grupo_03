FROM gradle:jdk17
VOLUME gradle-cache:/home/gradle/.gradle
VOLUME /tmp
USER root
ADD . /home/gradle/project
WORKDIR /home/gradle/project
RUN chown gradle:gradle -R /home/gradle
USER gradle
RUN gradle bootJar

#Start from a java:17
RUN mv /home/gradle/project/build/libs/*.jar /home/gradle/project/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=test","-jar","/home/gradle/project/app.jar"]
