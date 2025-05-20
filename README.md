# task-manager-app-in-kotlin 

# DICT 311: Android Task Management Application

## Project Overview
This repository contains the source code for a simple Task Management Application developed for the University of Mpumalanga as part of the DICT 311: Application Programming module[cite: 17, 18]. The application allows students to manage their daily tasks by enabling them to add, display, and delete tasks[cite: 19]. It incorporates fundamental Android Kotlin framework concepts including object-oriented programming, event handling, networking, multimedia features, and database persistence[cite: 20].

## Features
* **Task Management**: Add, display, and delete tasks[cite: 19].
* **Object-Oriented Design**: Implemented `Task` and `TaskManager` classes[cite: 22].
* **Data Persistence**: Uses `ViewModel` for data persistence and Room Database for storage[cite: 22, 23].
* **Event Handling**: Click listeners for adding and completing tasks, long-press for deleting tasks[cite: 22].
* **Multimedia Support**: Image indicators for task completion, notification sound on task addition, toast message on task deletion[cite: 22].
* **Networking**: Fetches task suggestions from an API using Kotlin Coroutines/AsyncTask and HttpURLConnection/Retrofit[cite: 22].
* **Notifications**: Sends email for overdue tasks and SMS reminders for incomplete tasks[cite: 22].
* **UI Enhancements**: Dark Mode support and a search bar to filter tasks[cite: 23, 24].

## Technologies Used
* Android Kotlin
* Room Database
* Retrofit / HttpURLConnection
* Coroutines / AsyncTask

## Setup and Installation

### Prerequisites
* Android Studio
* Kotlin Plugin for Android Studio
* An Android device or emulator running API 21 or higher.

### Steps
1.  **Clone the repository**:
    ```bash
    git clone [https://github.com/yourusername/DICT311_TaskManagementApp.git](https://github.com/yourusername/DICT311_TaskManagementApp.git)
    ```
2.  **Open in Android Studio**:
    Open the cloned project in Android Studio.
3.  **Sync Gradle**:
    Allow Android Studio to sync the Gradle files and download any necessary dependencies.
4.  **Run the application**:
    Run the application on an Android emulator or a physical device.

## Usage
1.  **Add a Task**: Enter the task details in the provided `EditText` and click the "Add" button[cite: 22].
2.  **Mark as Completed**: Tap on a task to mark it as completed. An image will indicate its status[cite: 22].
3.  **Delete a Task**: Long-press on a task to delete it[cite: 22]. A toast message will be displayed upon deletion[cite: 22].
4.  **Dark Mode**: Toggle Dark Mode from the app settings.
5.  **Search Tasks**: Use the search bar to filter tasks by title[cite: 24].

## Project Structure (App Module)
* `model/`: Contains data classes like `Task.kt`[cite: 22].
* `manager/`: Contains `TaskManager.kt` for managing task storage and retrieval[cite: 22].
* `viewmodel/`: Contains `TaskViewModel.kt` for UI-related data persistence[cite: 22].
* `database/`: Includes `AppDatabase.kt` and `TaskDao.kt` for Room Database implementation[cite: 23].
* `ui/`: Contains Activity and Adapter classes for the user interface.
* `services/`: Handles API calls, email, and SMS functionalities[cite: 22].
* `utils/`: Utility classes and constants.
