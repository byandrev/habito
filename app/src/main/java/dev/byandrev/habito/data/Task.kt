package dev.byandrev.habito.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String? = null,
    @ColumnInfo(name = "date") val date: LocalDateTime? = null,
    var checked: Boolean,
    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP") var createdAt: Long? = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at", defaultValue = "CURRENT_TIMESTAMP") var updatedAt: Long? = System.currentTimeMillis()
)
