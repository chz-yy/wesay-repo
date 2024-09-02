# 使用 Maven 和 JDK 11 的官方 Maven 镜像作为构建镜像
FROM maven:3.9.8-openjdk-11 AS build

# 设置工作目录
WORKDIR /app

# 将 Maven 配置文件和源代码复制到工作目录
COPY pom.xml .
COPY src ./src

# 构建 Spring Boot 应用
RUN mvn clean package -DskipTests

# 使用较小的 JDK 11 镜像作为运行镜像
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

# 从构建镜像中复制 JAR 文件到运行镜像
COPY --from=build /app/target/*.jar app.jar

# 运行 JAR 文件
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
