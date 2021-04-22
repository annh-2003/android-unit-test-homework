package com.sun.training.ut.ui.exercise_four


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.Calendar
import com.sun.training.ut.ui.base.BaseViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoField

class ExerciseFourViewModel : BaseViewModel() {
    val colorLiveData: MutableLiveData<String> = MutableLiveData()
    private var holidays = arrayOf("2/9", "1/1", "30/4", "1/5", "3/10", "4/10") // 3/10: fake holidays in saturday, 4/10 fake holidays in sunday
    private var dayOfMonth: Int = 0
    private var monthOfYear: Int = 0

    init {
        java.util.Calendar.getInstance().apply {
            dayOfMonth = get(java.util.Calendar.DAY_OF_MONTH)
            monthOfYear = get(java.util.Calendar.MONTH) + 1
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateColor() {
        val calendar = Calendar(dayOfMonth = dayOfMonth, monthOfYear = monthOfYear)
        val color = if (checkHoliday(
                dayOfMonth = calendar.dayOfMonth,
                monthOfYear = calendar.monthOfYear
            )
        )
            Constant.Color.RED
        else
            checkDay(
                dayOfMonth = calendar.dayOfMonth,
                monthOfYear = calendar.monthOfYear
            )
        colorLiveData.postValue(color.name)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkDay(dayOfMonth: Int, monthOfYear: Int): Constant.Color {
        val day = LocalDate.of(2020, monthOfYear, dayOfMonth)
        val d = DayOfWeek.of(day.get(ChronoField.DAY_OF_WEEK))
        return dayOfWeekToColor(d)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dayOfWeekToColor(day: DayOfWeek): Constant.Color {
        return when (day) {
            DayOfWeek.SATURDAY -> Constant.Color.BLUE
            DayOfWeek.SUNDAY -> Constant.Color.RED
            else -> Constant.Color.BLACK
        }
    }

    fun onDateChanged(monthOfYear: Int, dayOfMonth: Int) {
        this.monthOfYear = monthOfYear + 1
        this.dayOfMonth = dayOfMonth
    }

    private fun checkHoliday(dayOfMonth: Int, monthOfYear: Int): Boolean {
        val date = String.format("%d/%d", dayOfMonth, monthOfYear)
        return holidays.contains(date)
    }

}