package ru.finnetrolle.ark

import com.rabbitmq.client.ConnectionFactory

/*
 * Copyright (c) 2016. Finne Trolle
 */
class ActionExecutor(val params: CommandLineInterface.ConnParams) {

    fun perform(action: Action) {
        val factory = ConnectionFactory()
        factory.host = params.host
        factory.username = params.user
        factory.password = params.pass
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        action.perform(channel)

        channel.close()
        connection.close()
    }
}