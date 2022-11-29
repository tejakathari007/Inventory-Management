FROM openjdk:8-jdk-alpine
COPY  ./target/InventoryManagementService-*.jar InventoryManagementService.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/InventoryManagementService.jar"]