package app.loococo.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.presentation.component.StoneToDoAddPopup
import app.loococo.presentation.component.StoneToDoIconButton
import app.loococo.presentation.component.rememberShowAddPopupState
import app.loococo.presentation.utils.StoneToDoIcons

@Composable
internal fun HomeRoute() {
    HomeScreen()
}


@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = hiltViewModel()

    val calendarTypeState by viewModel.calendarType.collectAsStateWithLifecycle()
    val selectedDateState by viewModel.selectedDate.collectAsStateWithLifecycle()
    val todoListState by viewModel.todoList.collectAsStateWithLifecycle()
    val todoListMap by viewModel.todoListMap.collectAsStateWithLifecycle()

    val showPopupState = rememberShowAddPopupState()

    if (showPopupState.showPopupState) {
        StoneToDoAddPopup(
            todo = showPopupState.selectedTodoState,
            onAddTodo = viewModel::insert,
            onEditTodo = viewModel::changeTodoDescription,
            onDismissRequest = showPopupState::dismissPopup
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CalendarScreen(
                calendarType = calendarTypeState,
                selectedDate = selectedDateState,
                todoListMap = todoListMap,
                onCalendarNavigation = viewModel::onCalendarNavigation,
                onCalendarTypeChange = viewModel::onCalendarTypeChange,
                onDateSelected = viewModel::updateSelectedDate,
                onDateRange = viewModel::dateRange
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .weight(1f)
            ) {
                TodoListScreen(
                    list = todoListState,
                    onCheckedChange = viewModel::changeTodoStatus,
                    onChangeTodoDescription = {
                        showPopupState.showPopup(it)
                    },
                    onDeleteTodo = viewModel::deleteTodo
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            StoneToDoIconButton(
                size = 60.dp,
                icon = StoneToDoIcons.Plus,
                description = "add",
                onClick = showPopupState::showPopup
            )
        }

    }
}