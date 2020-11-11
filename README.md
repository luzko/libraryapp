## Веб приложение - "libraryapp"
---
### Учебный проект по курсу Java Web Development
### Автор: Лузько Дмитрий
---
### Общее описание
  Веб приложение реализует поддержку работоспособности библиотеки. 
  Читатель имеет возможность осуществлять поиск и заказ книг в каталоге. 
  Библиотекарь просматривает заказы, выдает читателю книгу на дом или в читальный зал. 
  Администратор управляет пользователями и контентом системы.
___
### Пользователи
   Для разграничения уровней доступа пользователей были введены роли:
   * **Гость**  
    Неавторизованный пользователь имеет возможность ознакомиться с каталогом и описанием книг, 
   пройти авторизацию или регистрацию.
   * **Читатель**  
    Читатель имеет доступ к книгам в библиотеке, 
   может делать заказы, просматривать и изменять личную информацию.
   * **Библиотекарь**  
    Библиотекарь имеет доступ к заказам читателей и принимает решение о выдаче книг.
   * **Администратор**  
    Администратор управляет приложением, а именно: пользователями и контентом системы. 
___
### Функциональные возможности
  * **Общие действия**
    * Просмотр домашней страницы
    * Смена языка сайта
    * Поиск книги
    * Просмотр каталога книг
    * Просмотр описания книги
  * **Гость**  
     * Авторизация
     * Регистрация
  * **Читатель**  
     * **Неподтвержденный**  
       * Активация личного профиля
       * Выход из аккаунта
     * **Активный** 
       * Просмотр личного профиля
       * Редактирование информации профиля
       * Оформление заказа в читальный зал
       * Оформление заказа на дом
       * Просмотр своих заказов
       * Возврат книги
       * Отмена заказа
       * Отправка электронной почты администратору
       * Выход из аккаунта
     * **Заблокированный** 
       * Отправка электронной почты администратору
       * Выход из аккаунта
  * **Библиотекарь**
     * Просмотр личного профиля
     * Редактирование информации профиля
     * Просмотр новых заказов
     * Просмотр всех заказов
     * Просмотр заказов по книге
     * Подтверждение выдачи книги по заказу
     * Отказ в выдаче книги
     * Выход из аккаунта
  * **Администратор**  
     * Просмотр списка всех пользователей
     * Поиск по пользователям
     * Добавление нового библиотекаря
     * Блокировка пользователя
     * Разблокировка пользователя
     * Добавление нового автора
     * Добавление новой книги
     * Удаление книги
     * Выход из аккаунта
___
## Web application - "libraryapp"
---
### Java Web Development Teaching Project
### Author: Luzko Dmitry
---
### General description
  The web application supports the functionality of the library. 
  The reader can search and order books in the catalogue. 
  The librarian reviews the orders, gives the book to the reader at home or in the reading room. 
  The administrator manages the system’s users and content.
___
### Users
   Roles were introduced to delineate user access levels:
   * **Guest**  
    An unauthorised user has the opportunity to view the catalogue and book descriptions, to log in or
    register.
   * **Reader**  
    The reader has access to books in the library, can order, view and modify personal information.
   * **Librarian**  
    The librarian has access to the orders of the readers and decides on the issue of books.
   * **Administrator**  
    The administrator manages the application, namely the users and the content of the system.
___
### Functionality
  * **General actions**
    * Home page view
    * Change site language
    * Book Search
    * Browse the book catalog
    * Book description review
  * **Guest**  
     * Authorization
     * Registration
  * **Reader**  
     * **Unconfirmed**  
       * Activating the Personal Profile
       * Logging out
     * **Active** 
       * Personal profile view
       * Editing profile information
       * Ordering of reading rooms
       * Ordering of the house
       * Review your orders
       * Book return
       * Cancellation
       * E-mail to administrator
       * Logging out
     * **Blocked** 
       * E-mail to administrator
       * Logging out
  * **Librarian**
     * Personal profile view
     * Editing profile information
     * View new orders
     * View all orders
     * Book order review
     * Acknowledgement of booking
     * Refusal to issue a book
     * Logging out
  * **Administrator**  
     * View list of all users
     * Search by users
     * Adding a new librarian
     * User lock
     * Unlocking the user
     * Adding a new author
     * Adding a new book
     * Book deletion
     * Logging out
___