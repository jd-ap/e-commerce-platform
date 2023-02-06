FROM maven:3.8.7-eclipse-temurin-17-alpine as build
WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S app_group && adduser -S --shell /sbin/nologin -g app_group app_user
RUN chown -R app_user:app_group /app
USER app_user
VOLUME /tmp
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]