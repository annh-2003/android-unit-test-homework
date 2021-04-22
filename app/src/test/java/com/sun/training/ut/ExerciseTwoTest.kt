package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ExerciseTwoTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        viewModel = ExerciseTwoViewModel()
    }

    @Test
    fun calculateFee_VipMember_Return0() {
        viewModel.apply {
            onTimeChanged(8, 10)
            onDateChanged(26, 10)
            onVipChecked(true)
            calculateFee()
        }
        Assert.assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_NormalDay_WorkingHour_NonVipMember_Return0() {
        viewModel.apply {
            onTimeChanged(9, 10)
            onDateChanged(26, 10)
            onVipChecked(false)
            calculateFee()
        }
        Assert.assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_NormalDay_NotWorkingHour_NonVipMember_Return110() {
        viewModel.apply {
            onTimeChanged(19, 30)
            onDateChanged(26, 10)
            onVipChecked(false)
            calculateFee()
        }
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_Holidays_WorkingHour_NonVipMember_Return110() {
        viewModel.apply {
            onTimeChanged(9, 30)
            onDateChanged(2, 8)
            onVipChecked(false)
            calculateFee()
        }
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_Holidays_NotWorkingHour_NonVipMember_Return110() {
        viewModel.apply {
            onTimeChanged(22, 30)
            onDateChanged(2, 8)
            onVipChecked(false)
            calculateFee()
        }
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }

    @Test
    fun calculateFee_NormalDay_WorkingHourEqual845_NonVipMember_Return110() {
        viewModel.apply {
            onTimeChanged(8, 45)
            onDateChanged(26, 10)
            onVipChecked(false)
            calculateFee()
        }
        Assert.assertEquals(Constant.FEE_110, viewModel.feeLiveData.value)
    }
}