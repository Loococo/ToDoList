package app.loococo.domain.repository

import app.loococo.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TodoRepository {

    suspend fun insert(todo: Todo)

    fun getTodoList(date: LocalDate): Flow<List<Todo>>

    fun getTodoList(startDate: LocalDate, endDate: LocalDate): Flow<List<Todo>>
}