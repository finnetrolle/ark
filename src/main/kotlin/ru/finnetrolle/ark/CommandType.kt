package ru.finnetrolle.ark

/*
 * Copyright (c) 2016. Finne Trolle
 */
enum class CommandType {
    UNDEFINED,
    COPY_QUEUE_TO_QUEUE,
    PERSIST_QUEUE_TO_DISK,
    LOAD_QUEUE_FROM_DISK,
    PUBLISH_STRING
}