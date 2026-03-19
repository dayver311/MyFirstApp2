package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.dto.Post


class PostRepositoryInMemoryImpl : PostRepository {
    // Исходные данные
    private var post = Post(
        id = 1,
        author = " Стук в двигателе. Университет автомобильной техники",
        content = " Стучание двигателя — тревожный сигнал, который может указывать на разные проблемы. Возможные причины стучания двигателя: Низкий уровень масла — недостаток масла может ухудшить смазку деталей и привести к звуку. Износ или повреждение поршневых колец или других деталей двигателя. Проблемы с клапанами — неправильная регулировка или износ. Проблемы с топливной смесью — неправильное зажигание или плохое качество топлива. Наличие посторонних предметов внутри двигателя — например, попавшие в мотор части. Что делать: Проверьте уровень масла и при необходимости долейте. Обратите внимание на то, когда появляется стук — при запуске, движении или на холостом ходу. Если стук сильный, не откладывайте — лучше обратиться в сервис для диагностики. Своевременное вмешательство поможет избежать серьезных повреждений.",

        published = "21 июня в 18:36",
        likedByMe = false,
        likes = 999,
        shares = 25,
        views = 5700
    )

    // MutableLiveData, который можно изменять
    private val _data = MutableLiveData(post)

    // Внешний доступ только для чтения (LiveData, а не MutableLiveData)
    override fun get(): LiveData<Post> = _data

    override fun like() {
        // Меняем состояние лайка на противоположное
        post = post.copy(
            likedByMe = !post.likedByMe,
            likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
        )
        // Оповещаем подписчиков об изменении
        _data.value = post
    }

    override fun share() {
        post = post.copy(
            shares = post.shares + 1
        )
        _data.value = post
    }

    override fun increaseViews() {
        // Можно будет реализовать позже
        post = post.copy(
            views = post.views + 1
        )
        _data.value = post
    }
}
