# Stock price loader API

Приложения для загрузки котировок акций с 
[Московской Биржи](https://www.moex.com/), 
используя [Moex API](https://iss.moex.com/iss/reference/813).

### Стэк технологий
- Java 11
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Cloud OpenFeign
- Spring Actuator
- Prometheus and Grafana
- Postgresql
- Gradle
- Docker-compose

## Сборка приложения
```shell script
# Склонировать проект к себе
git clone https://github.com/devalurum/stock-price-loader.git

# перейти в папку docker
cd docker

# поднять контейнеры
docker-compose up -d

# загружает gradle wrapper
gradlew wrapper

# сборка проекта
gradlew clean build 

# запуск Spring сервиса
java -jar build/libs/stock-price-loader.jar 
```
## Запуск и мониторинг приложения

1. Откройте адрес в браузере http://localhost:3000
2. Выполните вход в Grafana
   1. login: admin 
   2. password: admin 
3. Перейдите в JVM дашборд.
4. pgAdmin (GUI для postgresql) http://localhost:5050
   1. login: admin@admin.com
   2. password: admin

### Todo:
- Дописать тесты.
- Доделать контроллеры, мапинг DTO через фасад.
- Рефакторинг.
