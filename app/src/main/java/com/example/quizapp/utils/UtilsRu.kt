package com.example.quizapp.utils

import android.net.Uri
import com.example.quizapp.R
import com.example.quizapp.data.Question
import com.example.quizapp.data.Topic


object UtilsRu {
    const val langKeyRu = "ru"
    const val databaseRU = "TopicDatabaseEn"
    val dataset = arrayListOf(
        Topic(
            id = 0,
            title = "Животные",
            image = getUrlString(R.drawable.animal_img),
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
            image = getUrlString(R.drawable.car_img),
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
            image = getUrlString(R.drawable.earth_img),
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
                    image = getUrlString(R.drawable.liechtenstein_img),
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
            image = getUrlString(R.drawable.game_img),
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
                    image = getUrlString(R.drawable.witcher_img),
                    fakeAnswers = arrayListOf(
                        "Assassin's Creed",
                        "Elden Ring",
                        "The Elder Scrolls V: Skyrim",
                        "Kingdom Come",
                        "Dark Souls",
                        "God of War"
                    ),
                    realAnswer = "The Witcher"
                )
            )
        ),
        Topic(
            id = 4,
            title = "Мемы",
            image = getUrlString(R.drawable.meme_img),
            about = "В данной викторине вам нужно отвечать на вопросы, связанные с популярными мемами в интернете.",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "В каких случаях используют этот мем?",
                    image = getUrlString(R.drawable.bad_luck_brian_img),
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
                    title = "Как называется этот шедевр?",
                    image = getUrlString(R.drawable.vzhuh_img),
                    fakeAnswers = arrayListOf(
                        "Исчезни",
                        "Парирам",
                        "Пшик",
                        "Экспекто Патронум",
                        "Кот-колдун"
                    ),
                    realAnswer = "Вжух"
                ),
                Question(
                    id = 2,
                    title = "Какое из этих чисел не является мемным?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "228",
                        "1337",
                        "322",
                        "300",
                        "1488"
                    ),
                    realAnswer = "77"
                ),
                Question(
                    id = 3,
                    title = "Что Илон Маск занес в офис Твиттера после того, как стал его владельцем?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Колесо",
                        "Картонного себя",
                        "Логотип SpaceX",
                        "Красный ковер",
                        "Чайник",
                        "Клетку с попугаем"
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
            image = getUrlString(R.drawable.music_img),
            about = "В данной викторине вам нужно отвечать на вопросы по теме \"Музыка\"",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "Какому музыкальному исполнителю принадлежит альбом под названием \"?\"(знак вопроса)?",
                    image = getUrlString(R.drawable.question_mark_img),
                    fakeAnswers = arrayListOf(
                        "Lil Peep",
                        "Lil Pump",
                        "Noize MC",
                        "Scriptonite",
                        "Макс Корж",
                        "Michael Jackson"
                    ),
                    realAnswer = "XXXTentacion"
                ),
                Question(
                    id = 1,
                    title = "Какой музыкальный композитор написал cонату для фортепиано №14 (более известную под названием \"Лунная\")",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Вольфганг Амадей Моцарт",
                        "Иоганн Себастьян Бах",
                        "Джузеппе Верди",
                        "Петр Чайковский",
                        "Фридерик Шопен"
                    ),
                    realAnswer = "Людвиг ван Бетховен"
                ),
                Question(
                    id = 2,
                    title = "Как называется прием игры на гитаре, при котором палец одновременно зажимает все струны?",
                    image = getUrlString(R.drawable.barre_img),
                    fakeAnswers = arrayListOf(
                        "диез",
                        "бемоль",
                        "бурре",
                        "минор",
                        "кварм"
                    ),
                    realAnswer = "баррэ"
                ),
                Question(
                    id = 3,
                    title = "Один из популярных хитов Макса Коржа называется...",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Малиновый рассвет",
                        "Сливочное масло",
                        "Мудрец",
                        "Пессимист",
                        "Цвет настроения черный",
                        "Погода"
                    ),
                    realAnswer = "Малиновый закат"
                ),
                Question(
                    id = 4,
                    title = "В каком году вышла песня под названием \"Diamonds\"?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "1999",
                        "2005",
                        "2010",
                        "2014",
                        "2009"
                    ),
                    realAnswer = "2012"
                )
            )
        ),
        Topic(
            id = 6,
            title = "Космос",
            image = getUrlString(R.drawable.space_img),
            about = "В данной викторине вам нужно отвечать на вопросы о космосе",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "Как называется наша галактика?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Большая Медведица",
                        "Бернарда",
                        "Андромеда",
                        "Сатурн"
                    ),
                    realAnswer = "Млечный Путь"
                ),
                Question(
                    id = 1,
                    title = "Как называется ближайшая к Земле звезда (не считая Солнца)?",
                    image = getUrlString(R.drawable.proxima_img),
                    fakeAnswers = arrayListOf(
                        "Сириус",
                        "Барнарда",
                        "Альфа Центавра",
                        "Луна",
                        "Марс"
                    ),
                    realAnswer = "Проксима Центавра"
                ),
                Question(
                    id = 2,
                    title = "Как звали первое животное, выведенное на орбиту Земли?",
                    image = getUrlString(R.drawable.earth_orbit_img),
                    fakeAnswers = arrayListOf(
                        "Белка",
                        "Стрелка",
                        "Марс",
                        "Снежок",
                        "Каспер"
                    ),
                    realAnswer = "Лайка"
                ),
                Question(
                    id = 3,
                    title = "В зависимости от массы звезды жизнь ее может закончиться по-разному." +
                            " А чем она закончиться не может?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Черной дырой",
                        "Нейтронной звездой",
                        "Белым карликом"
                    ),
                    realAnswer = "Красным гигантом"
                ),
                Question(
                    id = 4,
                    title = "Какая планета самая большая в Солнечной системе?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Марс",
                        "Меркурий",
                        "Сатурн",
                        "Уран",
                        "Нептун"
                    ),
                    realAnswer = "Юпитер"
                )
            )
        ),
        Topic(
            id = 7,
            title = "Кодинг",
            image = getUrlString(R.drawable.coding_img),
            about = "В данной викторине вам нужно отвечать на вопросы по программированию",
            questions = arrayListOf(
                Question(
                    id = 0,
                    title = "Какой результат выведет следующая программа?",
                    image = getUrlString(R.drawable.cd1_img),
                    fakeAnswers = arrayListOf(
                        "Ошибку при выполнении",
                        "Ошибку компиляции",
                        "A is equal to NaN",
                    ),
                    realAnswer = "A is equal to Infinity"
                ),
                Question(
                    id = 1,
                    title = "Какой результат выведет следующая программа?",
                    image = getUrlString(R.drawable.cd2_img),
                    fakeAnswers = arrayListOf(
                        "null",
                        "Ошибку при выполнении",
                        "Hello",
                    ),
                    realAnswer = "Ошибку компиляции"
                ),
                Question(
                    id = 2,
                    title = "Какой результат выведет следующая программа?",
                    image = getUrlString(R.drawable.cd3_img),
                    fakeAnswers = arrayListOf(
                        "Ошибка при выполнении",
                        "Ошибка компиляции",
                        "200",
                        "100",
                        "101"
                    ),
                    realAnswer = "0"
                ),
                Question(
                    id = 3,
                    title = "Какой результат выведет следующая программа?",
                    image = getUrlString(R.drawable.cd4_img),
                    fakeAnswers = arrayListOf(
                        "0",
                        "Ошибку компиляции",
                        "128",
                        "127",
                        "Ошибка при выполнении"
                    ),
                    realAnswer = "-128"
                ),
                Question(
                    id = 4,
                    title = "\"val test = 33 to 42\".\n" +
                            "Что такое \"to\" в приведенном примере?",
                    image = null,
                    fakeAnswers = arrayListOf(
                        "Опечатка",
                        "Ключевое слово для создания диапазона от 33 до 42",
                        "Ключевое слово Kotlin для создания пары (33, 42)",
                        "Инфиксная функция, создающая диапазон от 32 до 42 (33, 42)"
                    ),
                    realAnswer = "Инфиксная функция, создающая пару (33, 42)"
                )
            )
        )
    )

    private fun getUrlString(res: Int): String = Uri.parse("android.resource://com.example.quizapp/$res").toString()
}