package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FieldResearchApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldResearchApp() {
    val navController = rememberNavController()
    val tasks = remember { mutableStateListOf<String>() } // single source of truth

    MaterialTheme {
        Surface {
            NavHost(navController = navController, startDestination = "taskList") {
                composable("taskList") {
                    TaskListScreen(
                        tasks = tasks,
                        onAddTaskClick = { navController.navigate("createTask") }
                    )
                }

                composable("createTask") {
                    CreateTaskScreen(
                        onSaveTask = { newTask ->
                            if (newTask.isNotBlank()) tasks.add(newTask)
                            navController.popBackStack()
                        },
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}