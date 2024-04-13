# ВместеВыгоднее - приложение поиска соседа и совместного съема жилья

![GitHub repo size](https://img.shields.io/github/repo-size/vasyan-coder/ProfitableTogetherAndroid?color=A5A09D)
<a href="https://opensource.org/licenses/MIT"><img alt="License" src="https://img.shields.io/badge/License-MIT-535A7A.svg"/></a>
<a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-23%2B-92817A.svg?style=flat"/></a>
<img alt="Clean Architecture" src="https://img.shields.io/badge/Clean-Architecture-white"/>
<img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-92817A"/>
</br>
![GitHub watchers](https://img.shields.io/github/watchers/vasyan-coder/ProfitableTogetherAndroid?color=A5A09D)
![GitHub language count](https://img.shields.io/github/languages/count/vasyan-coder/ProfitableTogetherAndroid?color=A26749)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/vasyan-coder/ProfitableTogetherAndroid?color=A26749)

<img width="1996" alt="мокап" src="https://github.com/vasyan-coder/ProfitableTogetherAndroid/assets/95854753/b82db728-0605-4714-b520-f58047e9ca3d">


<!-- HEADER SECTION -->
<h1 align="center">ВместеВыгоднее</h1>
   <p align="center">
    Поиск соседа и совместный съем жилья </br>
    <B>Приложение еще в разработке!</B>


<!--PROJECT DESCRIPTION-->
## О проекте

### Назначение системы

Приложение “ВместеВыгоднее” предоставляет возможность пользователям искать сожителя для съема квартиры с целью разделения арендной платы. Приложение позволяет пользователю ввести максимальную приемлемую цену для съема квартиры, интересующие станции метро и количество людей, с которыми он готов её снимать. Приложение покажет все доступные варианты, учитывая арендную плату потенциальных сожителей.

### Инструменты разработки

* [Android Studio](https://developer.android.com/studio)
* [Kotlin](https://kotlinlang.org/)

### Технологии, используемые в приложении

Приложение разработано с использованием следующих технологий:

- [Kotlin](https://kotlinlang.org/docs/reference/) - это кроссплатформенный статически типизированный язык программирования общего назначения с выводом типов. Kotlin рассчитан на полное взаимодействие с Java, и стандартная библиотека Kotlin в JVM-версии зависит от библиотеки классов Java, однако выделение типов позволяет сделать синтаксис языка более понятным.se
- [Jetpack Components:](https://developer.android.com/topic/architecture?gclid=Cj0KCQjw8O-VBhCpARIsACMvVLOH1satX45o9f4PMQ4Sxr7bG9myl6-KZL9nYda8PJsHV7m2uJL8bzgaAmqiEALw_wcB&gclsrc=aw.ds)
    - [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=Cj0KCQjwhqaVBhCxARIsAHK1tiMMwHsxQ8Z25jyEdtLha9erq11wROoEfL6RqpGMprgbDTNuMO3_Ri8aAu5EEALw_wcB&gclsrc=aw.ds) - Современный инструментарий Android для создания нативных пользовательских интерфейсов. Он упрощает и ускоряет разработку пользовательского интерфейса на Android.
    - [View Model](https://developer.android.com/topic/libraries/architecture/viewmodel)- хранение и управление данными, связанными с пользовательским интерфейсом, с учетом жизненного цикла.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Выполнение действий в ответ на изменение состояния жизненного цикла другого компонента, например, действий и фрагментов.
    - [Android KTX](https://developer.android.com/kotlin/ktx.html) - Android KTX - это набор расширений Kotlin, которые входят в состав Android Jetpack и других библиотек для Android. Расширения KTX предоставляют краткий, идиоматический Kotlin для Jetpack, платформы Android и других API.
    - [AndroidX](https://developer.android.com/jetpack/androidx) -  Значительное улучшение оригинальной библиотеки [Support Library](https://developer.android.com/topic/libraries/support-library/index) , которая больше не поддерживается. 
    - [Jetpack Navigation Library](https://composedestinations.rafaelcosta.xyz/) - Библиотека для навигации в Compose, разработанная компанией Google.

- [Retrofit](https://github.com/square/retrofit)- это безопасный с точки зрения типов REST-клиент для Android, Java и Kotlin, созданный как мощный фреймворк для потребления API
- [okHttp3](https://square.github.io/okhttp/)- это библиотека для работы с сетью, которая работает под капотом у Retfofit.
- [Dagger-Hilt](https://dagger.dev/hilt/) - библиотека инъекции зависимостей для Android, которая уменьшает количество ошибок, связанных с ручной инъекцией зависимостей в вашем проекте
    
- [Coroutines](https://developer.android.com/kotlin/coroutines) - библиотека для работы с асинхронным кодом в Kotlin.
    
- [Flow](https://developer.android.com/kotlin/flow) - используется совместно с корутинами для потоков данных.
    
- [Coil](https://coil-kt.github.io/coil/compose/) - Библиотека для загрузки изображений.



<!-- GETTING STARTED -->
## Установка и запуск приложения

1. Программное обеспечение </br>
`Android Studio` -> `File` -> `New` -> `From Version Control` -> `Git`</br>
Введите `https://github.com/vasyan-coder/ProfitableTogetherAndroid.git` в поле URL и нажмите кнопку `Clone`.

2. Клонирование репозитория </br>
Запустите следующее в командной строке, чтобы клонировать проект:
   ```sh
   git clone https://github.com/vasyan-coder/ProfitableTogetherAndroid.git
   ```
    Откройте `Software` и выберите `File | Open...` из меню. Выберите клонированный каталог и нажмите кнопку `Open`

<!-- Documentation -->
## Документация разработчика
### Как открыть документацию
1. Перейдите в папку `documentation` в корне проекта.
2. Откройте файл `index.html` в любом веб-браузере.

<!-- VIDEO DEMONSTRATION -->
### Демонстрация работоспособности

Чтобы просмотреть полную версию приложения, нажмите ниже:

<!-- CONTRIBUTING -->
## Содействие

Вклад - это то, что делает сообщество с открытым исходным кодом таким замечательным местом для обучения, вдохновения и творчества. Высоко оценим любой ваш вклад.

1. Разветвите проект
2. Создайте свою ветку функций ("функция git checkout -b/ Потрясающая функция`)
3. Зафиксируйте свои изменения (`git commit -m 'Добавьте какую-нибудь удивительную функцию")
4. Перейдите в ветку (`git push origin feature/Удивительная функция`)
5. Откройте запрос на извлечение

<!-- LICENSE -->
## Лицензия

Распространяется по лицензии MIT. Дополнительную информацию смотрите в разделе `ЛИЦЕНЗИЯ`.

<!-- Contact -->
## Контакты

* **Для связи** - [Telegram](https://t.me/KurwaVasyan)
* **Ссылка проекта** - https://github.com/vasyan-coder/ProfitableTogetherAndroid


## Продемонстрируйте свою поддержку

Поставьте ⭐️, если этот проект помог вам!


