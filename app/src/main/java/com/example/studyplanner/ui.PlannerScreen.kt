package com.example.studyplanner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// 1. كلاس الحالة (UI State)
data class PlannerUiState(
    val greeting: String = "Welcome, Layan",
    val totalTasks: Int = 7,
    val completedTasks: Int = 3
)

// 2. الشاشة العديمة الحالة (Stateless Composable)
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onAddTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "${uiState.completedTasks} of ${uiState.totalTasks} done",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onAddTask
        ) {
            Text("Add Task")
        }
    }
}

// 3. المعاينات (Previews)
@Preview(showBackground = true, name = "Initial State")
@Composable
private fun PlannerInitialPreview() {
    PlannerHomeScreen(
        uiState = PlannerUiState("Hello, Layan", 7, 3),
        onAddTask = {}
    )
}

@Preview(showBackground = true, name = "All Done State")
@Composable
private fun PlannerAllDonePreview() {
    PlannerHomeScreen(
        uiState = PlannerUiState("Great job, Layan", 5, 5),
        onAddTask = {}
    )
}

// 4. الـ Route المالك للحالة
@Composable
fun PlannerHomeRoute() {
    var uiState by remember { mutableStateOf(PlannerUiState()) }

    PlannerHomeScreen(
        uiState = uiState,
        onAddTask = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    completedTasks = uiState.completedTasks + 1
                )
            }
        }
    )
}