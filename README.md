# Сервис по учету котиков и их владельцев

## Сущности
### Котики
- Имя
- Дата рождения
- Порода
- Цвет (один из заранее заданных вариантов)
- Хозяин
- Список котиков, с которыми дружит этот котик (из представленных в базе)

### Хозяева
- Имя
- Дата рождения
- Список котиков

## Техническая информация
- Архитектура: Controller-Service-Dao на Spring Framework
- Хранение данных: БД PostgreSQL
- HTTP интерфейс: REST API для получения информации о котиках и владельцах
- Использование Spring Bean’ов с Dependency Injection (Autowired)
- DAO: наследуют JpaRepository и содержат шаблонные Spring Data JPA методы

## Авторизация и безопасность
- Роль администратора с доступом ко всем методам и возможностью создания новых пользователей
- Соотношение пользователь - владелец в 1:1
- Защита методов по получению информации о котиках и владельцах с использованием Spring Security
- Доступ к методам для фильтрации имеют все авторизованные пользователи, но результаты ограничены данными о своих котиках