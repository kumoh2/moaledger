# 1. Java 21 JDK 이미지 사용 (빌드 단계)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# 2. 프로젝트 코드 복사 및 빌드
COPY . .
RUN ./mvnw clean package -DskipTests

# 3. 실행 단계 (JDK 필요 없고 JRE만 사용)
FROM eclipse-temurin:21-jre
WORKDIR /app

# 4. 빌드된 JAR 파일 복사
COPY --from=build /app/target/*.jar app.jar

# 5. 컨테이너 시작 시 실행할 명령어
CMD ["java", "-jar", "app.jar"]
