package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.ui.excercise_six.ExerciseSixViewModel
import com.sun.training.ut.ui.exercise_seven.ExerciseSevenViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import kotlin.Throws

class ExerciseSevenViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ExerciseSevenViewModel
    private var expectedValue = 0

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseSevenViewModel()
    }

    @Test
    fun totalMoney_Return0(){
        Assert.assertEquals(expectedValue,viewModel.money)
    }

    @Test
    fun validateErrorLiveData_ReturnTrue(){
        viewModel.isErrorLiveData.value = true
        Assert.assertEquals(true,viewModel.isErrorLiveData.value)
    }

    @Test
    fun validateErrorLiveData_ReturnFalse(){
        viewModel.isErrorLiveData.value = false
        Assert.assertEquals(false,viewModel.isErrorLiveData.value)
    }

    // KH không phải là premium, tổng tiền mua hàng nhỏ hơn hoặc bằng 0 yên và chọn giao hàng thông thường.
    @Test
    fun totalPriceShip_NotPremium_TotalLowerOrEqual0_NormalShip_Return0(){
        viewModel.apply {
            money = 0
            onFastShippingChecked(false)
            onPremiumChecked(false)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH không phải là premium, tổng tiền mua hàng nhỏ hơn 5000 yên và chọn giao hàng thông thường.
    @Test
    fun totalPriceShip_NotPremium_TotalLowerThan5000_NormalShip_Return500(){
        expectedValue = 500
        viewModel.apply {
            money = 4000
            onFastShippingChecked(false)
            onPremiumChecked(false)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH không phải là premium, tổng tiền mua hàng lớn hơn 5000 yên và chọn giao hàng thông thường.
    @Test
    fun totalPriceShip_NotPremium_TotalHigherThan5000_NormalShip_Return0(){
        viewModel.apply {
            money = 5000
            onFastShippingChecked(false)
            onPremiumChecked(false)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH không phải là premium, tổng tiền mua hàng nhỏ hơn hoặc bằng 0 yên và chọn giao hàng nhanh.
    @Test
    fun totalPriceShip_NotPremium_TotalLowerOrEqual0_FastShip_Return0(){
        viewModel.apply {
            money = 0
            onFastShippingChecked(true)
            onPremiumChecked(false)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH không phải là premium, tổng tiền mua hàng nhỏ hơn 5000 yên và chọn giao hàng nhanh.
    @Test
    fun totalPriceShip_NotPremium_TotalLowerThan5000_FastShip_Return1000(){
        expectedValue = 1000
        viewModel.apply {
            money = 4000
            onFastShippingChecked(true)
            onPremiumChecked(false)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH không phải là premium, tổng tiền mua hàng lớn hơn 5000 yên và chọn giao hàng nhanh.
    @Test
    fun totalPriceShip_NotPremium_TotalHigherThan5000_FastShip_Return500(){
        expectedValue = 500
        viewModel.apply {
            money = 6000
            onFastShippingChecked(true)
            onPremiumChecked(false)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH là premium, tổng tiền mua hàng nhỏ hơn hoặc bằng 0 yên và chọn giao hàng thông thường.
    @Test
    fun totalPriceShip_isPremium_TotalLowerOrEqual0_NormalShip_Return0(){
        viewModel.apply {
            money = 0
            onFastShippingChecked(false)
            onPremiumChecked(true)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH là premium, tổng tiền mua hàng nhỏ hơn 5000 yên và chọn giao hàng thông thường.
    @Test
    fun totalPriceShip_isPremium_TotalLowerThan5000_NormalShip_Return0(){
        viewModel.apply {
            money = 4000
            onFastShippingChecked(false)
            onPremiumChecked(true)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH là premium, tổng tiền mua hàng lớn hơn 5000 yên và chọn giao hàng thông thường.
    @Test
    fun totalPriceShip_isPremium_TotalHigherThan5000_NormalShip_Return0(){
        viewModel.apply {
            money = 5000
            onFastShippingChecked(false)
            onPremiumChecked(true)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH là premium, tổng tiền mua hàng nhỏ hơn hoặc bằng 0 yên và chọn giao hàng nhanh.
    @Test
    fun totalPriceShip_isPremium_TotalLowerOrEqual0_FastShip_Return0(){
        viewModel.apply {
            money = 0
            onFastShippingChecked(true)
            onPremiumChecked(true)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH là premium, tổng tiền mua hàng nhỏ hơn 5000 yên và chọn giao hàng nhanh.
    @Test
    fun totalPriceShip_isPremium_TotalLowerThan5000_FastShip_Return500(){
        expectedValue = 500
        viewModel.apply {
            money = 4000
            onFastShippingChecked(true)
            onPremiumChecked(true)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }

    // KH là premium, tổng tiền mua hàng lớn hơn 5000 yên và chọn giao hàng nhanh.
    @Test
    fun totalPriceShip_isPremium_TotalHigherThan5000_FastShip_Return500(){
        expectedValue = 500
        viewModel.apply {
            money = 6000
            onFastShippingChecked(true)
            onPremiumChecked(true)
            calculateFee()
        }
        Assert.assertEquals(expectedValue,viewModel.feeLiveData.value)
    }
}