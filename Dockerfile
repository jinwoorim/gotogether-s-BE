FROM openjdk:17-jdk-slim-buster

# manifest파일 경로
VOLUME /tmp

# 컨테이너가 열어줄 포트 번호를 지정합니다.
EXPOSE 8080

# 지정된 jar파일을 이름.jar로 생성
ADD build/libs/*.jar app.jar

# Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]