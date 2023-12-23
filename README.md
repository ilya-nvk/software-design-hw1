## Как пользоваться программой

В начале работы с программой нужно либо создать новый аккаунт сотрудника кинотеатра, либо войти в существующий. Затем вы увидите меню действий, которые можете совершить. (Думаю, дальше понятно как пользоваться.)

## Как она работает

### Хранение данных

Все данные сохраняются локально в папку `resources`. Для сериализации был выбран формат JSON, так для него есть
библиотека Gson, которую очень легко использовать и в целом она достаточно распространена.

### DI

Для улучшения архитектуры и структуры кода использовалась библиотека Dagger 2. Она предоставляет инструменты для реализации dependency injection, что существенно упрощает тестирование кода, уменьшает связанность между компонентами приложения и способствует повторному использованию кода.

## Диаграммы

Они также лежат в папке `diagrams`.

### Диаграмма классов

![class_diagram.png](diagrams%2Fclass_diagram.png)

### Диаграмма прецедентов

![use_case_diagram.png](diagrams%2Fuse_case_diagram.png)

## Пароли

* Пароль от `ilyanvk`: `qwerty`
* Пароль от `admin`: `mnbvcx`