package com.ump.taskmanager.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.ump.taskmanager.R.java
import com.ump.taskmanager.model.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Utility class for handling notifications, emails, and SMS
 */
class NotificationUtils(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)
    private val channelId = "task_channel"

    init {
        createNotificationChannel()
    }

    /**
     * Create notification channel for Android O and above
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Task Notifications"
            val descriptionText = "Notifications for task deadlines and reminders"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Show a notification for an overdue task
     */
    fun showTaskNotification(task: Task) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_task_pending)
            .setContentTitle("Overdue Task: ${task.title}")
            .setContentText("This task is overdue! Due date: ${formatDate(task.dueDate)}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(task.id, notification)
        }
    }

    /**
     * Play a sound when a task is added
     */
    fun playTaskAddedSound() {
        val mediaPlayer = MediaPlayer.create(context, R.raw.task_added)
        mediaPlayer.setOnCompletionListener { mp -> mp.release() }
        mediaPlayer.start()
    }

    /**
     * Show a toast message
     */
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Send an email for an overdue task
     * Opens the default email client with pre-populated fields
     */
    fun sendOverdueTaskEmail(task: Task, emailAddress: String) {
        val subject = "Overdue Task: ${task.title}"
        val body = """
            You have an overdue task that needs attention:

            Title: ${task.title}
            Description: ${task.description}
            Due Date: ${formatDate(task.dueDate)}

            Please complete this task as soon as possible.

            - UMP Task Manager App
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            showToast("No email app found")
        }
    }

    /**
     * Send an SMS reminder for incomplete tasks
     * Checks for SMS permission before sending
     */
    fun sendTaskSms(task: Task, phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)
            == PackageManager.PERMISSION_GRANTED) {

            val message = "Reminder: Your task '${task.title}' is due on ${formatDate(task.dueDate)}. - UMP Task Manager"

            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                showToast("SMS reminder sent")
            } catch (e: Exception) {
                showToast("Failed to send SMS: ${e.message}")
            }
        } else {
            showToast("SMS permission not granted")
        }
    }

    /**
     * Format a date for display
     */
    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
}