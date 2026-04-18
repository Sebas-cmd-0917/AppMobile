package com.jaimes.helloandroidjaimessebastian.ui

import android.os.Build
import android.provider.Settings
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.jaimes.helloandroidjaimessebastian.R
import com.jaimes.helloandroidjaimessebastian.receiver.ReminderReceiver
import java.util.Calendar

class ReminderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_reminder, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        val tvStatus = view.findViewById<TextView>(R.id.tvReminderStatus)

        view.findViewById<Button>(R.id.btnScheduleReminder).setOnClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute

            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                if (before(Calendar.getInstance())) add(Calendar.DAY_OF_MONTH, 1)
            }

            scheduleReminder(calendar.timeInMillis)
            tvStatus.text = "Recordatorio activado para ${"%02d".format(hour)}:${"%02d".format(minute)}"
        }
    }

    private fun scheduleReminder(triggerMillis: Long) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(requireContext(), ReminderReceiver::class.java).apply {
            putExtra("task_title", "¡Tienes tareas pendientes!")
        }
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(), 100, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Para Android 12+ verificar permiso
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerMillis, pendingIntent)
            } else {
                // Abrir configuración para que el usuario active el permiso
                val intent2 = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent2)
            }
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerMillis, pendingIntent)
        }
    }
}