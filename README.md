# Автоматизация тестирования API сервиса [Petstore](https://petstore.swagger.io/)


## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>


[![Java.png](icons/Java.png)](https://www.java.com)[![Gradle](/icons/Gradle.png)](https://gradle.org)[![IntelliJ IDEA](/icons/Intelij_IDEA.png)](https://www.jetbrains.com/idea)[![Selenide](/icons/Selenide.png)](https://selenide.org)[![Selenoid](/icons/Selenoid.png)](https://aerokube.com/selenoid)[![JUnit 5](/icons/JUnit5.png)](https://junit.org/junit5)[![Jenkins (1).png](icons/Jenkins%20%281%29.png)](https://www.jenkins.io)[![Allure Report](/icons/Allure_Report.png)](https://docs.qameta.io/allure)[![Telegram](/icons/Telegram.png)](https://telegram.org)

## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов:**</a>

- [x] Добавление нового питомца в магазин
- [x] Поиск питомца по статусу
- [x] Обновляем информацию о питомце
- [x] Создание нового пользователя
- [x] Успешный логин пользователя
- [x] Обновляем данные пользователя
- [x] Успешный разлогин пользователя


Отчетность о прогоне тестов в Telegram позволит оперативно отслеживать потенциальные и уже существующие проблемы на сайте

### **Пример отчета в Telegram:**

<img src="/images/Telegram_api.png"> 

### [**Пример Allure отчета:**](https://jenkins.autotests.cloud/job/Centicore-test/15/allure)

<img src="/images/Allure_api.PNG"> 

### Прогон автотестов в Jenkins:

<img src="/images/Jenkins_api.PNG">



***Локальный запуск с помощью Intellij IDEA :***
```bash  
gradlew api_pet
gradlew api_user
```


