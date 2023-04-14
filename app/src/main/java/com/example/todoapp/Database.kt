package com.example.todoapp

import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Entity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun dao(): DAO
}