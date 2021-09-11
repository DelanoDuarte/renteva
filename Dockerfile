FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} renteva.jar
ENTRYPOINT ["run.sh"]