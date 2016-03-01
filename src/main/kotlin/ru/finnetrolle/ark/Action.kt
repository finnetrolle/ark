package ru.finnetrolle.ark

import com.rabbitmq.client.Channel

/*
 * Copyright (c) 2016. Finne Trolle
 */
class Action(val action: (Channel) -> Unit) {

    fun perform(channel: Channel) {
        action.invoke(channel)
    }

}