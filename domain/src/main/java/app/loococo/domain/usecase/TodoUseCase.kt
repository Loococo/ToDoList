package app.loococo.domain.usecase

import app.loococo.domain.model.Todo
import app.loococo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class TodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun insert(todo: Todo) {
        todoRepository.insert(todo)
    }

    fun getTodoList(date: LocalDate): Flow<List<Todo>> {
        return todoRepository.getTodoList(date)
    }

    fun getTodoList(startDate: LocalDate, endDate: LocalDate): Flow<List<Todo>> {
        return todoRepository.getTodoList(startDate, endDate)
    }

    suspend fun changeTodoStatus(id: Int, status: Boolean) {
        todoRepository.changeTodoStatus(id, status)
    }

    suspend fun changeTodoDescription(id: Int, description: String) {
        todoRepository.changeTodoDescription(id, description)
    }

    suspend fun deleteTodo(id: Int) {
        todoRepository.deleteTodo(id)
    }
}