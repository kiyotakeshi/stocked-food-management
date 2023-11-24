# stocked-food-management

## run local


```shell
docker compose up -d

docker compose ps

./mvnw spring-boot:run
```


## generate docs

```shell
./mvnw clean package

open target/generated-docs/index.html
```

## format and validate

```shell
./mvnw spring-javaformat:apply validate
```
