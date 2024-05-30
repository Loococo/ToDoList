package app.loococo.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.loococo.domain.model.Todo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val description: String,
    val completion: Boolean = false
)


fun TodoEntity.toTodo(): Todo {
    return Todo(
        date = date.toLocalDate(),
        description = description,
        completion = completion
    )
}

fun Todo.toTodoEntity(): TodoEntity {
    return TodoEntity(
        date = date.toISO(),
        description = description,
        completion = completion
    )
}

fun LocalDate.toISO(): String {
    return this.format(DateTimeFormatter.ISO_LOCAL_DATE)
}

fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
}