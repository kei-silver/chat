# Alpine Linux 기반의 OpenJDK 17 이미지를 사용
FROM openjdk:17-jdk-alpine

# 작업 디렉토리 생성
WORKDIR /app

# 애플리케이션 JAR 파일을 복사
COPY target/client-app.jar /app/client-app.jar

# 애플리케이션 실행 명령
CMD ["java", "-jar", "/app/client-app.jar"]
