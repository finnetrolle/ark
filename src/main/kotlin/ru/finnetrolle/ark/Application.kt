/*
 * Copyright (c) 2016. Finne Trolle
 */

package ru.finnetrolle.ark

import com.beust.jcommander.JCommander

fun main(args: Array<String>) {

    val cli = CommandLineInterface()
    val jc = JCommander(cli, *args)
    jc.setProgramName("ark")

    val executor = ActionExecutor(cli.getConnParams())
    val ops = cli.getCommandParams()
    if (ops == null) {
        println("Command con not be executed because of wrong parameters")
        jc.usage()
        return
    }

    executeCommand(jc, executor, cli.getCommandType(), ops)

}

fun executeCommand(jc: JCommander, executor: ActionExecutor, commandType: CommandType, ops: Map<String, String>) {
    when (commandType) {
        CommandType.PUBLISH_STRING -> {
            send(executor, ops.get("to").orEmpty(), ops.get("message").orEmpty())
        }
        CommandType.COPY_QUEUE_TO_QUEUE -> {
            copy(executor, ops.get("from").orEmpty(), ops.get("to").orEmpty())
        }
        CommandType.HELP -> {
            jc.usage()
        }
        else -> {
            jc.usage()
        }
    }
}


