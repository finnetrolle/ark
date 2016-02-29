/*
 * Copyright (c) 2016. Finne Trolle
 */

package ru.finnetrolle.ark

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.rabbitmq.client.ConnectionFactory

fun send(host:String, queue: String, message: String, username: String?, password: String?) {
    val factory = ConnectionFactory()
    factory.host = host
    if (username != null) factory.username = username
    if (password != null) factory.password = password
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    channel.basicPublish("", queue, null, message.toByteArray())
    println(" [x] sent: ${message}")

    channel.close()
    connection.close()
}

fun main(args: Array<String>) {

    val cli = CommandLineInterface()
    val jc = JCommander(cli, *args)
    jc.setProgramName("ark")

    when (cli.getCommandType()) {
        CommandType.PUBLISH_STRING -> {
            val ops = cli.getCommandParams()
            if (ops == null) {
                println("wrong parameters")
                val sb: StringBuilder = StringBuilder()
                jc.usage(sb)
                println(sb.toString())
                return
            }
            send(cli.hostname, ops.get("to").orEmpty(), ops.get("message").orEmpty(), cli.username, cli.password)
        }
        else -> {
            println("Methods is not implemented")
        }
    }



}


