# OpenJDK 이미지를 기반으로 설정
FROM maven:3.8.4-openjdk-17 AS build-stage

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드와 Maven 빌드 파일 복사
COPY pom.xml ./

# 나머지 소스 복사
COPY src ./src

# 애플리케이션 패키징
RUN mvn package -DskipTests

# 빌드된 JAR 파일을 실행
FROM openjdk:17

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일을 현재 디렉토리로 복사
COPY --from=build-stage /app/target/*.jar ./app.jar

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]
