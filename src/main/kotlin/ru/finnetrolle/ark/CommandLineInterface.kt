package ru.finnetrolle.ark

import com.beust.jcommander.Parameter
import ru.finnetrolle.ark.CommandType.*
import java.util.*

/*
 * Copyright (c) 2016. Finne Trolle
 */
class CommandLineInterface {

    @Parameter(names = arrayOf("-u", "--username"), description = "username to access RabbitMQ server")
    var username: String? = null

    @Parameter(names = arrayOf("-p", "--password"), description = "password to access RabbitMQ server")
    var password: String? = null

    @Parameter(names = arrayOf("--help"), help = true)
    var help: Boolean = true

    @Parameter(names = arrayOf("-h", "--hostname"),
            description = "hostname of RabbitMQ server. Default value is localhost")
    var hostname: String = "localhost"

    @Parameter(description = "command options")
    var commands: List<String> = ArrayList<String>()

    fun getCommandType(): CommandType {
        if (commands.isEmpty()) return UNDEFINED
        when (commands[0]) {
            "copy" -> return COPY_QUEUE_TO_QUEUE
            "import" -> return PERSIST_QUEUE_TO_DISK
            "export" -> return LOAD_QUEUE_FROM_DISK
            "send" -> return PUBLISH_STRING
            else -> return UNDEFINED
        }
    }

    fun getCommandParams(): Map<String, String>? {
        when (getCommandType()) {
            UNDEFINED -> return null
            COPY_QUEUE_TO_QUEUE ->
                return if (commands.size == 3) mapOf("from" to commands[1], "to" to commands[2]) else null
            PERSIST_QUEUE_TO_DISK ->
                return if (commands.size == 3) mapOf("from" to commands[1], "filename" to commands[2]) else null
            LOAD_QUEUE_FROM_DISK ->
                return if (commands.size == 3) mapOf("filename" to commands[1], "to" to commands[2]) else null
            PUBLISH_STRING ->
                return if (commands.size == 3) mapOf("to" to commands[1], "message" to commands[2]) else null
            else -> return null
        }
    }




}