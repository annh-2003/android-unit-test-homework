package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.No8Member
import com.sun.training.ut.ui.exercise_eight.ExerciseEightViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import kotlin.Throws
import com.sun.training.ut.data.Constant.DayOfWeek.*
import java.lang.reflect.Member

class ExerciseEightViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ExerciseEightViewModel
    lateinit var member: No8Member
    var expectedValue: Int? = 0

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        member = No8Member()
        viewModel = ExerciseEightViewModel()
    }

    // Phí vào sân cầu lông của KH có 0 < tuổi < 120, ngày trong tuần là thứ 3.
    @Test
    fun totalPrice_AgeHigher0AndLower120_Tuesday_Return1200() {
        expectedValue = 1200
        viewModel.apply {
            dayOfWeek = TUESDAY
            ageChanged(2)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có 13 < tuổi < 65, ngày trong tuần là thứ 6.
    @Test
    fun totalPrice_AgeLower65AndHigher13_Friday_Return1800() {
        expectedValue = 1800
        viewModel.apply {
            dayOfWeek = FRIDAY
            ageChanged(14)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có 13 < tuổi < 65, giới tính Nữ, ngày trong tuần là thứ sáu.
    @Test
    fun totalPrice_AgeLower65AndHigher13_Friday_Female_Return1400() {
        expectedValue = 1400
        viewModel.apply {
            dayOfWeek = FRIDAY
            ageChanged(14)
            genderChangedFemale(true)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có 13 < tuổi < 65, ngày trong tuần là thứ hai.
    @Test
    fun totalPrice_AgeLower65AndHigher13_Monday_Return1800() {
        expectedValue = 1800
        viewModel.apply {
            dayOfWeek = MONDAY
            ageChanged(14)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi > 65, ngày trong tuần là thứ Hai.
    @Test
    fun totalPrice_AgeHigher65_Monday_Return1600() {
        expectedValue = 1600
        viewModel.apply {
            dayOfWeek = MONDAY
            ageChanged(66)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi > 65, ngày trong tuần là thứ Sáu.
    @Test
    fun totalPrice_AgeHigher65_Friday_Male_Return1600() {
        expectedValue = 1600
        viewModel.apply {
            dayOfWeek = FRIDAY
            ageChanged(66)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi > 65,giới tính nữ, ngày trong tuần là thứ Sáu.
    @Test
    fun totalPrice_AgeHigher65_Friday_Female_Return1400() {
        expectedValue = 1400
        viewModel.apply {
            dayOfWeek = FRIDAY
            ageChanged(66)
            genderChangedFemale(true)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi > 65, ngày trong tuần là thứ Ba.
    @Test
    fun totalPrice_AgeHigher65_Tuesday_Return1200() {
        expectedValue = 1200
        viewModel.apply {
            dayOfWeek = TUESDAY
            ageChanged(66)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi < 13, ngày trong tuần là thứ ba.
    @Test
    fun totalPrice_AgeLower13_Tuesday_Return1200() {
        expectedValue = 1200
        viewModel.apply {
            dayOfWeek = TUESDAY
            ageChanged(12)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi < 13 ,giới tính nam, ngày trong tuần là thứ Sáu.
    @Test
    fun totalPrice_AgeLower13_Tuesday_ReturnNull() {
        expectedValue = null
        viewModel.apply {
            ageChanged(12)
            genderChangedMale(true)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi < 13
    @Test
    fun totalPrice_AgeLower13_Return900() {
        expectedValue = 900
        viewModel.apply {
            dayOfWeek = FRIDAY
            ageChanged(12)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi < 13, ngày thứ hai
    @Test
    fun totalPrice_AgeLower13_Monday_Return900() {
        expectedValue = 900
        viewModel.apply {
            dayOfWeek = MONDAY
            ageChanged(12)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }


    // Phí vào sân cầu lông của KH có tuổi < 0.
    @Test
    fun totalPrice_AgeLower0_ReturnNull() {
        expectedValue = null
        viewModel.apply {
            dayOfWeek = FRIDAY
            ageChanged(-2)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    // Phí vào sân cầu lông của KH có tuổi < 0.
    @Test
    fun totalPrice_AgeHigher120_ReturnNull() {
        expectedValue = null
        viewModel.apply {
            dayOfWeek = FRIDAY
            ageChanged(222)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    @Test
    fun totalPrice_DateOfWeekNull_ReturnNull() {
        expectedValue = null
        viewModel.apply {
            ageChanged(222)
            calculateBadmintonFee()
        }
        Assert.assertEquals(expectedValue, viewModel.fee.value)
    }

    @Test
    fun genderChangedIsFemaleTest_ReturnFemale() {
        viewModel.genderChangedFemale(true)
        Assert.assertEquals(Constant.Gender.FEMALE, viewModel.member.value?.gender)
    }

    @Test
    fun genderChangedIsNotFemaleTest_ReturnNull() {
        viewModel.genderChangedFemale(false)
        Assert.assertEquals(null, viewModel.member.value?.gender)
    }

    @Test
    fun genderChangedIsMaleTest_ReturnMale() {
        viewModel.genderChangedMale(true)
        Assert.assertEquals(Constant.Gender.MALE, viewModel.member.value?.gender)
    }

    @Test
    fun genderChangedIsNotMaleTest_ReturnNull() {
        viewModel.genderChangedMale(false)
        Assert.assertEquals(null, viewModel.member.value?.gender)
    }

    @Test
    fun dateOfWeekValue_ReturnTuesday() {
        viewModel.dayOfWeek = TUESDAY
        Assert.assertEquals(TUESDAY, viewModel.dayOfWeek)
    }

    @Test
    fun ageChangedTest_Return12AndFEMALE() {
        viewModel.apply {
            member.value = No8Member(age = 12, gender = Constant.Gender.FEMALE)
            ageChanged(12)
            Assert.assertEquals(
                No8Member(age = 12, gender = Constant.Gender.FEMALE),
                viewModel.member.value
            )
        }
    }

}