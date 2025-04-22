# Sử dụng image Maven + JDK 21 để build
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file pom.xml trước để tải dependencies
COPY pom.xml /app
RUN mvn dependency:go-offline

# Sao chép toàn bộ source code
COPY src /app/src

# Build ứng dụng, bỏ qua test
RUN mvn clean install -DskipTests

# Dùng image nhẹ hơn để chạy ứng dụng
FROM eclipse-temurin:21-jdk

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file WAR/JAR từ image build sang image chạy
COPY --from=build /app/target/enrollment-service-0.0.1-SNAPSHOT.jar /app/app.jar

# Mặc định chạy app
CMD ["java", "-jar", "/app/app.jar"]
