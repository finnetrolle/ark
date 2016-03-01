package ru.finnetrolle.ark

/*
 * Copyright (c) 2016. Finne Trolle
 */

fun send(executor: ActionExecutor, queue: String, message: String) {
    executor.perform(Action { channel ->
        channel.basicPublish("", queue, null, message.toByteArray())
        println("message sent: ${message}")
    })
}

fun copy(executor: ActionExecutor, queueFrom: String, queueTo: String) {
    executor.perform(Action { channel ->
        var cnt = 0
        var response = channel.basicGet(queueFrom, false)
        while (response != null) {
            cnt += 1
            channel.basicPublish("", queueTo, null, response.body)
            channel.basicAck(response.envelope.deliveryTag, false)
            response = channel.basicGet(queueFrom, false)
        }
        println("Copied: ${cnt} messages")
    })
}
