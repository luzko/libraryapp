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
    Читатель имеет доступ к книгам в библиотке, 
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
     * Добавление нового библиотекоря
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
  The web application implements library health support.
  The reader has the ability to search and order books in the catalog.
  The librarian looks through the orders, gives the reader a book at home or in the reading room.
  The administrator manages the users and the content of the system.
___
### Users
   To differentiate user access levels, roles were introduced:
   * **Guest**  
    An unauthorized user has the opportunity to view the catalog and description of books,
   pass authorization or registration.
   * **Reader**  
    The reader has access to books in the library,
   may place orders, view and modify personal information.
   * **Librarian**  
    The librarian has access to the readers' orders and decides on the issue of books.
   * **Administrator**  
    The administrator manages the application, namely: users and system content.
___
### Functionality
  * **General actions**
    * Home page view
    * Change site language
    * Book Search
    * Browse the book catalog
    * View book description
  * **Guest**  
     * Authorization
     * Registration
  * **Reader**  
     * **Unconfirmed**  
       * Personal Profile Activation
       * Sign out
     * **Active** 
       * Viewing personal profile
       * Editing profile information
       * Order a book to the reading room
       * Order a book at home
       * View your orders
       * Returning a book
       * Cancel a order
       * Sending email to administrator
       * Sign out
     * **Заблокированный** 
       * Sending email to administrator
       * Sign out
  * **Librarian**
     * Viewing personal profile
     * Editing profile information
     * View new orders
     * View all orders
     * View orders by book
     * Confirmation of an order
     * Refusal to issue a book
     * Sign out
  * **Administrator**  
     * View a list of all users
     * Search by users
     * Adding a new librarian
     * Blocking a user
     * Unlocking a user
     * Adding a new author
     * Adding a new book
     * Deleting a Book
     * Sign out
___