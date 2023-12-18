# stocked-food-management

## API Reference

https://kiyotakeshi.github.io/stocked-food-management/

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

## format sql files

```shell
npm i

./node_modules/sql-formatter/bin/sql-formatter-cli.cjs --language postgresql --fix src/main/resources/db/migration/*.sql
```
