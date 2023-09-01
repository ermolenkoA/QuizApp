package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.data.Question
import com.example.quizapp.data.Topic

object UtilsRu {
    val dataset = arrayListOf<Topic>(
        Topic(
            id = 0,
            title = "Животные",
            image = (R.drawable.animal_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы по теме животных.",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "Хотя они кажутся счастливыми," +
                            " \"смех\" какого животного является реакцией на угрозу?",
                    image = null,
                    fakeAnswers = arrayListOf(
                            "Лиса",
                        "Дятел",
                        "Обезьяна",
                        "Лошадь",
                        "Хомяк",
                        "Дельфин"
                    ),
                    realAnswer = "Гиена"
                ),
                Question(
                    id = 1,
                    title = "Какое позвоночное животное самое маленькое?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Рыба",
                        "Ящерица",
                        "Черепаха",
                        "Птица",
                        "Хомяк",
                        "Медуза"
                    ),
                    realAnswer = "Лягушка"
                ),
                Question(
                    id = 2,
                    title = "У какой птицы самый большой размах крыльев?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Коршун",
                        "Орлан",
                        "Журавль",
                        "Орел",
                        "Чайка",
                        "Пингвин"
                    ),
                    realAnswer = "Альбатрос"
                ),
                Question(
                    id = 3,
                    title = "Какое животное является самым быстрым на суше?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Лев",
                        "Жираф",
                        "Страус",
                        "Антилопа",
                        "Зебра",
                        "Олень"
                    ),
                    realAnswer = "Гепард"
                ),
                Question(
                    id = 4,
                    title = "Какое животное является самым большим на Земле?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Африканский слон",
                        "Колоссальный кальмар",
                        "Белый медведь",
                        "Жираф",
                        "Анаконда",
                        "Белая акула"
                    ),
                    realAnswer = "Синий кит"
                )
            )
        ),
        Topic(
            id = 1,
            title = "Автомобили",
            image = (R.drawable.car_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы по автомобильной тематике.",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "Какая марка автомобиля является самой продаваемой в мире?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Volkswagen",
                        "Ford",
                        "Hyundai",
                        "Honda",
                        "Nissan",
                        "BMW"
                    ),
                    realAnswer = "Toyota"
                ),
                Question(
                    id = 1,
                    title = "Какая марка автомобиля является самой дорогой в мире?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Bugatti",
                        "Rolls-Royce",
                        "Ferrari",
                        "Lamborghini",
                        "Aston Martin",
                        "Pagani"
                    ),
                    realAnswer = "Koenigsegg"
                ),
                Question(
                    id = 2,
                    title = "Какой автомобиль не является немецким?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Volkswagen",
                        "BMW",
                        "Audi",
                        "Opel",
                        "Porsche",
                        "Mercedes-Benz"
                    ),
                    realAnswer = "Renault"
                ),
                Question(
                    id = 3,
                    title = "Как называется устройство, которое преобразует " +
                            "химическую энергию топлива в механическую энергию движения?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Турбина",
                        "Генератор",
                        "Аккумулятор",
                        "Амортизатор"
                    ),
                    realAnswer = "Двигатель"
                ),
                Question(
                    id = 4,
                    title = "Как называется устройство, которое обеспечивает связь между " +
                            "двигателем и колесами и позволяет отключать " +
                            "их друг от друга при необходимости?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Турбонагнетатель",
                        "Дифференциал",
                        "Карданный вал",
                        "Редуктор",
                        "Распределительный вал",
                        "Вискомуфта"
                    ),
                    realAnswer = "Сцепление"
                )
            )
        ),
        Topic(
            id = 2,
            title = "Страны",
            image = (R.drawable.earth_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы о интересных фактах из других стран",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "Какая страна является второй по площади в мире?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Россия",
                        "США",
                        "Китай",
                        "Бразилия",
                        "Австралия",
                        "Индия"
                    ),
                    realAnswer = "Канада"
                ),
                Question(
                    id = 1,
                    title = "Столицей какого европейского государства является город Вена?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Италия",
                        "Греция",
                        "Люксембург",
                        "Бельгия",
                        "Швейцария",
                        "Венгиря"
                    ),
                    realAnswer = "Австрия"
                ),
                Question(
                    id = 2,
                    title = "Какую страну называют \"Лёгкими Европы\"",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Чехия",
                        "Польша",
                        "Украина",
                        "Албания",
                        "Молдова",
                        "Сербия"
                    ),
                    realAnswer = "Беларусь"
                ),
                Question(
                    id = 3,
                    title = "Как называется эта страна?",
                    image = ,
                    fakeAnswers = arrayListOf(
                        "Люксембург",
                        "Ватикан",
                        "Монако",
                        "Сан-Марино",
                        "Бельгия",
                        "Косово"
                    ),
                    realAnswer = "Лихтенштейн"
                ),
                Question(
                    id = 4,
                    title = "Какая страна самая молодая?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Македония",
                        "Чехия",
                        "Хорватия",
                        "Алжир",
                        "Беларусь",
                        "Косово"
                    ),
                    realAnswer = "Южный Судан"
                )
            )
        ),
        Topic(
            id = 3,
            title = "Игры",
            image = (R.drawable.game_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы о сфере компьютерных игр",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "Как звали легендарного торговца из игры S.T.A.L.K.E.R.?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Петрович",
                        "Глухарь",
                        "Ворон",
                        "Саныч",
                        "Прохор",
                        "Кабан"
                    ),
                    realAnswer = "Сидорович"
                ),
                Question(
                    id = 1,
                    title = "Сколько вышло версий игры в основной серии CS?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "3",
                        "2",
                        "6",
                        "4",
                        "7"
                    ),
                    realAnswer = "5"
                ),
                Question(
                    id = 2,
                    title = "Какая игра не была выпущена компанией Valve Corporation?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Half-Life",
                        "Dota 2",
                        "Counter-Strike",
                        "Left 4 Dead",
                        "Portal",
                        "Team Fortress 2"
                    ),
                    realAnswer = "Rust"
                ),
                Question(
                    id = 3,
                    title = "Какая компания не выпускала игровые приставки?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Microsoft",
                        "Sony",
                        "Valve",
                        "Nintendo",
                        "Sega",
                        "Philips"
                    ),
                    realAnswer = "Intel"
                ),
                Question(
                    id = 4,
                    title = "Как называется игра на фото?",
                    image = ,
                    fakeAnswers = arrayListOf(
                        "Assassin's Creed",
                        "Elden Ring",
                        "The Elder Scrolls V: Skyrim",
                        "Kingdom Come",
                        "Dark Souls",
                        "God of War"
                    ),
                    realAnswer = "Witcher"
                )
            )
        ),
        Topic(
            id = 4,
            title = "Мемы",
            image = (R.drawable.meme_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы, связанные с популярными мемами в интернете.",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "В каких случаях используют этот мем?",
                    image = ,
                    fakeAnswers = arrayListOf(
                        "Высмеять другого человека",
                        "Запикапить девушку",
                        "Рассказать как выжить в школе",
                        "Победил в игре",
                    ),
                    realAnswer = "Рассказать о неудачной ситуации"
                ),
                Question(
                    id = 1,
                    title = "Из какого фильма взят этот кадр?",
                    image = ,
                    fakeAnswers = arrayListOf(
                        "Зеленая Миля",
                        "Форест Гамп",
                        "Спасти рядового Райна",
                        "Шетое чуство",
                        "Пираты Карибского моря",
                        "Дикий, дикий Запад"
                    ),
                    realAnswer = "Баллада Бастера Скраггса"
                ),
                Question(
                    id = 2,
                    title = "За что Уилл Смит ударил ведущего на “Оскаре”?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Он оскорбил его",
                        "Он высмеял фильм с его участием",
                        "Он проиграл спор",
                        "Они поспорили"
                    ),
                    realAnswer = "Он оскорбил его жену"
                ),
                Question(
                    id = 3,
                    title = "Что Илон Маск занес в офис Твиттера после того, как стал его владельцем?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Трон",
                        "Картонного себя",
                        "Логотип SpaceX",
                        "Красный ковер",
                        "Чайник",
                        "Клетку"
                    ),
                    realAnswer = "Раковину"
                ),
                Question(
                    id = 4,
                    title = "Во что одевались фанаты на премьере фильма \"Миньоны: Грювитация?\"",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "В пижамы",
                        "Во все желтое",
                        "В детскую одежду",
                        "В рабочую форму",
                    ),
                    realAnswer = "В деловые костюмы"
                )
            )
        ),
        Topic(
            id = 5,
            title = "Музыка",
            image = (R.drawable.music_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы по теме \"Музыка\"",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 1,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 2,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 3,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 4,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                )
            )
        ),
        Topic(
            id = 6,
            title = "Космос",
            image = (R.drawable.space_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы о космосе",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 1,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 2,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 3,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 4,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                )
            )
        ),
        Topic(
            id = 7,
            title = "Кодинг",
            image = (R.drawable.coding_img).toString(),
            about = "В данной викторине вам нужно отвечать на вопросы по программированию",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 1,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 2,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 3,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                ),
                Question(
                    id = 4,
                    title = "",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    realAnswer = ""
                )
            )
        )
    )

}