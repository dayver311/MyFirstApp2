package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.dto.Post


class PostRepositoryInMemoryImpl : PostRepository {

    // Теперь это список, а не один пост
    private var posts = listOf(
        Post(
            id = 1,
            author = " Стук в двигателе. Университет автомобильной техники",
            content = " Стучание двигателя — тревожный сигнал, который может указывать на разные проблемы. Возможные причины стучания двигателя: Низкий уровень масла — недостаток масла может ухудшить смазку деталей и привести к звуку. Износ или повреждение поршневых колец или других деталей двигателя. Проблемы с клапанами — неправильная регулировка или износ. Проблемы с топливной смесью — неправильное зажигание или плохое качество топлива. Наличие посторонних предметов внутри двигателя — например, попавшие в мотор части. Что делать: Проверьте уровень масла и при необходимости долейте. Обратите внимание на то, когда появляется стук — при запуске, движении или на холостом ходу. Если стук сильный, не откладывайте — лучше обратиться в сервис для диагностики. Своевременное вмешательство поможет избежать серьезных повреждений.",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 999,
            shares = 25,
            views = 5700
        ),
        Post(
            id = 2,
            author = "Android Dev",
            content = "Вышел новый релиз Android Studio! Теперь с поддержкой Gemini AI и улучшенным композером.",
            published = "22 мая в 10:15",
            likedByMe = false,
            likes = 342,
            shares = 89,
            views = 2300
        ),
        Post(
            id = 3,
            author = "Kotlin Weekly",
            content = "Kotlin 2.0.0 released! Что нового в языке? Смотрим обновления компилятора и стандартной библиотеки.",
            published = "23 мая в 09:42",
            likedByMe = true,
            likes = 1250,
            shares = 420,
            views = 8900
        ),
        Post(
            id = 4,
            author = "Google I/O",
            content = "Анонсированы новые возможности для разработчиков: Compose UI, Wear OS 5, Android 15 Beta",
            published = "20 мая в 20:00",
            likedByMe = false,
            likes = 5678,
            shares = 1234,
            views = 45000
        )
    )

    private val _data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = _data

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
                )
            } else {
                post
            }
        }
        _data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(shares = post.shares + 1)
            } else {
                post
            }
        }
        _data.value = posts
    }

    override fun increaseViews(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(views = post.views + 1)
            } else {
                post
            }
        }
        _data.value = posts
    }
}

