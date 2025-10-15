# Habito

A modern Android habit and task tracking application built with Jetpack Compose and Material Design 3.

## Overview

Habito helps you build better habits and manage your daily tasks efficiently. Track your habits with customizable schedules, manage tasks with deadlines, and monitor your progress over time.

## Features

### Habit Tracking
- **Create Custom Habits**: Define habits with names and descriptions
- **Flexible Scheduling**: Set which days of the week each habit should be performed
- **Daily Habits View**: See all habits scheduled for today
- **Progress Tracking**: Monitor weekly completion percentages for each habit
- **Completion Status**: Mark habits as completed for the current day

### Task Management
- **Task Creation**: Add tasks with names, descriptions, and optional due dates/times
- **Task Organization**: View tasks in "To Do" and "Completed" tabs
- **Task Status**: Mark tasks as complete or incomplete with checkboxes
- **Date/Time Support**: Set specific due dates and times for tasks
- **Task Filtering**: Filter tasks by completion status

### User Interface
- **Material Design 3**: Modern, clean interface following Material You guidelines
- **Bottom Navigation**: Easy navigation between Habits, Tasks, and Settings
- **Dark/Light Mode**: Supports system theme preferences
- **Intuitive Forms**: Simple forms for creating habits and tasks with date/time pickers

## Tech Stack

### Core Technologies
- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern declarative UI framework
- **Material Design 3**: UI components and design system

### Architecture & Libraries
- **Room Database**: Local data persistence
- **ViewModel**: UI state management
- **Kotlin Coroutines & Flow**: Asynchronous programming and reactive data streams
- **Navigation Compose**: In-app navigation
- **Firebase Authentication**: User authentication (in development)

### Build & Tools
- **Gradle with Kotlin DSL**: Build configuration
- **KSP (Kotlin Symbol Processing)**: Annotation processing for Room
- **Android SDK 26+**: Minimum API level 26 (Android 8.0)
- **Target SDK 36**: Latest Android features

## Requirements

- Android device or emulator running Android 8.0 (API 26) or higher
- Android Studio Hedgehog or later (for development)
- JDK 11 or higher

## Installation

### For Users
1. Download the latest APK from the [Releases](../../releases) page
2. Enable "Install from Unknown Sources" in your device settings
3. Install the APK
4. Launch Habito

### For Developers

1. **Clone the repository**
   ```bash
   git clone https://github.com/byandrev/habito.git
   cd habito
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository and select it

3. **Sync Gradle**
   - Android Studio will automatically prompt to sync Gradle
   - Wait for the sync to complete

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`

## Project Structure

```
app/src/main/java/dev/byandrev/habito/
├── data/              # Data layer (entities, DAOs, database)
│   ├── Habit.kt       # Habit entity and utility functions
│   ├── Task.kt        # Task entity
│   ├── TaskDao.kt     # Room DAO for tasks
│   ├── AppDatabase.kt # Room database configuration
│   └── Converters.kt  # Type converters for Room
├── ui/                # UI layer
│   ├── components/    # Reusable Compose components
│   │   ├── Header.kt
│   │   ├── NavigationBar.kt
│   │   └── FormTask.kt
│   └── screens/       # Screen composables
│       ├── Home.kt    # Habits screen
│       ├── Tasks.kt   # Tasks screen
│       ├── NewHabit.kt
│       ├── Settings.kt
│       ├── Login.kt
│       └── Loading.kt
├── viewmodel/         # ViewModels for state management
├── utils/             # Utility classes
└── MainActivity.kt    # Main activity
```

## Database Schema

### Habits Table
- `id`: Unique identifier (auto-generated)
- `name`: Habit name
- `description`: Optional description
- `schedule`: Days of the week (Set<DayOfWeek>)
- `created_at`: Creation timestamp
- `updated_at`: Last update timestamp

### Tasks Table
- `id`: Unique identifier (auto-generated)
- `name`: Task name
- `description`: Optional description
- `date`: Optional due date and time (LocalDateTime)
- `checked`: Completion status (Boolean)
- `created_at`: Creation timestamp
- `updated_at`: Last update timestamp

## Building

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

## Author

**byandrev**

## Acknowledgments

- Material Design 3 for the beautiful UI components
- Jetpack Compose team for the modern UI framework
- Room persistence library for robust local data storage
