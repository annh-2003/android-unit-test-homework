package com.sun.training.ut.ui.exercise_one

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class ExerciseOneViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ExerciseOneViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseOneViewModel()
    }

    // Không check voucher, TimeCoupon
    @Test
    fun totalPrice_NoVoucher_NoTimeCoupon_Return490() {
        viewModel.apply {
            numberBeer = 1
            calculatePrice()
        }
        Assert.assertEquals(490, viewModel.priceLiveData.value)
    }

    // Chỉ check TimeCoupon
    @Test
    fun totalPrice_NoVoucher_CheckTimeCoupon_Return290() {
        viewModel.apply {
            numberBeer = 1
            onTimeCouponChecked(true)
            onVoucherChecked(false)
            calculatePrice()
        }
        Assert.assertEquals(290, viewModel.priceLiveData.value)
    }

    // Chỉ check Voucher
    @Test
    fun totalPrice_CheckVoucher_NoTimeCoupon_Return590() {
        viewModel.apply {
            numberBeer = 2
            onTimeCouponChecked(false)
            onVoucherChecked(true)
            calculatePrice()
        }
        Assert.assertEquals(590, viewModel.priceLiveData.value)
    }

    // Check cả hai
    @Test
    fun totalPrice_CheckVoucher_CheckTimeCoupon_Return390() {
        viewModel.apply {
            numberBeer = 2
            onTimeCouponChecked(true)
            onVoucherChecked(true)
            calculatePrice()
        }
        Assert.assertEquals(390, viewModel.priceLiveData.value)
    }
}