FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/fs3_1_usuarios-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_NDV6JV3GQZU7AA12 /app/oracle_wallet
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]