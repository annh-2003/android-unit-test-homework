package com.sun.training.ut.ui.exercise_five

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class ExerciseFiveViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ExerciseFiveViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseFiveViewModel()
    }

    // Price = 0, Không check voucher, delivery
    @Test
    fun totalDiscount_PriceZero_NoVoucher_NoDelivery_ReturnEmpty() {
        viewModel.apply {
            totalPrice = 0
            calculateCouponWithPizza()
        }
        Assert.assertEquals("", viewModel.discountLiveData.value)
    }

    //Price = 0, chỉ check delivery
    @Test
    fun totalDiscount_PriceZero_NoVoucher_CheckDelivery_ReturnEmpty() {
        viewModel.apply {
            totalPrice = 0
            onChangedDelivery(true)
            calculateCouponWithPizza()
        }
        Assert.assertEquals("", viewModel.discountLiveData.value)
    }

    //Price = 0, check All
    @Test
    fun totalDiscount_PriceZero_CheckVoucher_CheckDelivery_ReturnEmpty() {
        viewModel.apply {
            totalPrice = 0
            onChangedDelivery(true)
            onChangedVoucher(true)
            calculateCouponWithPizza()
        }
        Assert.assertEquals("", viewModel.discountLiveData.value)
    }

    // Price < 1500, Không check voucher, delivery
    @Test
    fun totalDiscount_PriceLowerThan1500_NoVoucher_NoDelivery_ReturnSecondPizza() {
        viewModel.apply {
            totalPrice = 1000
            calculateCouponWithPizza()
        }
        Assert.assertEquals(Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }

    // Price < 1500, chỉ check delivery
    @Test
    fun totalDiscount_PriceLowerThan1500_NoVoucher_CheckDelivery_ReturnRegularFee() {
        viewModel.apply {
            totalPrice = 1000
            onChangedDelivery(true)
            calculateCouponWithPizza()
        }

        Assert.assertEquals(Constant.Coupon.REGULAR_FEE.coupon, viewModel.discountLiveData.value)
    }

    // Price < 1500, check All
    @Test
    fun totalDiscount_PriceLowerThan1500_CheckVoucher_CheckDelivery__ReturnSale20Percent() {
        viewModel.apply {
            totalPrice = 1000
            onChangedDelivery(true)
            onChangedVoucher(true)
            calculateCouponWithPizza()
        }
        Assert.assertEquals(Constant.Coupon.OFF_20.coupon, viewModel.discountLiveData.value)
    }

    // Price > 1500, không check voucher, delivery
    @Test
    fun totalDiscount_PriceGreaterThan1500_NoVoucher_NoDelivery_ReturnSecondsPizzaAndPotato() {
        viewModel.apply {
            totalPrice = 1600
            calculateCouponWithPizza()
        }
        Assert.assertEquals(Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }

    // Price > 1500, chỉ check delivery
    @Test
    fun totalDiscount_PriceGreaterThan1500_NoVoucher_CheckDelivery_ReturnRegularFeeAndPotato() {
        viewModel.apply {
            totalPrice = 1600
            onChangedDelivery(true)
            calculateCouponWithPizza()
        }
        Assert.assertEquals(Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.REGULAR_FEE.coupon, viewModel.discountLiveData.value)
    }

    // Price > 1500, check All
    @Test
    fun totalDiscount_PriceGreaterThan1500_CheckVoucher_CheckDelivery_ReturnPotatoAndSale20Percent() {
        viewModel.apply {
            totalPrice = 1600
            onChangedDelivery(true)
            onChangedVoucher(true)
            calculateCouponWithPizza()
        }
        Assert.assertEquals(Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.OFF_20.coupon, viewModel.discountLiveData.value)
    }
}