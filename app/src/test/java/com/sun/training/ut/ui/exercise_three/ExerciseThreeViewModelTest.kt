package com.sun.training.ut.ui.exercise_three

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class ExerciseThreeViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ExerciseThreeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseThreeViewModel()
    }

    // Item nhỏ hơn 7, không check Shirt, Tie
    @Test
    fun calculateDiscount_ItemLowerThanSeven_NoShirt_NoTie_Return0() {
        viewModel.apply {
            numberOfItems = 6
            calculate()
        }
        assertEquals(0, viewModel.discountLiveData.value)
    }

    // Item nhỏ hơn 7, chỉ check Shirt
    @Test
    fun calculateDiscount_ItemLowerThanSeven_CheckShirt_NoTie_Return0() {
        viewModel.apply {
            numberOfItems = 6
            onChangedShirt(true)
            calculate()
        }
        assertEquals(0, viewModel.discountLiveData.value)
    }

    // Item nhỏ hơn 7, chỉ check Tie
    @Test
    fun calculateDiscount_ItemLowerThanSeven_NoShirt_CheckTie_Return0() {
        viewModel.apply {
            numberOfItems = 6
            onChangedTie(true)
            calculate()
        }
        assertEquals(0, viewModel.discountLiveData.value)
    }

    // Item nhỏ hơn 7, có check Shirt và check Tie
    @Test
    fun calculateDiscount_ItemLowerThanSeven_CheckShirt_CheckTie_Return5() {
        viewModel.apply {
            numberOfItems = 6
            onChangedShirt(true)
            onChangedTie(true)
            calculate()
        }
        assertEquals(5, viewModel.discountLiveData.value)
    }

    // Số lượng Item >= 7, không check Shirt, Tie
    @Test
    fun calculateDiscount_ItemGreaterThanOrEqualSeven_NoShirt_NoTie_Return7() {
        viewModel.apply {
            numberOfItems = 7
            calculate()
        }
        assertEquals(7, viewModel.discountLiveData.value)
    }

    // Số lượng Item >= 7, chỉ check Shirt
    @Test
    fun calculateDiscount_ItemGreaterThanOrEqualSeven_CheckShirt_NoTie_Return7() {
        viewModel.apply {
            numberOfItems = 8
            onChangedShirt(true)
            calculate()
        }
        assertEquals(7, viewModel.discountLiveData.value)
    }

    // Số lượng Item >= 7, chỉ check Tie
    @Test
    fun calculateDiscount_ItemGreaterThanOrEqualSeven_NoShirt_CheckTie_Return7() {
        viewModel.apply {
            numberOfItems = 12
            onChangedTie(true)
            calculate()
        }
        assertEquals(7, viewModel.discountLiveData.value)
    }

    // Số lượng Item >= 7, check Shirt, Tie
    @Test
    fun calculateDiscount_ItemGreaterThanOrEqualSeven_checkShirt_checkTie_Return12() {
        viewModel.apply {
            numberOfItems = 9
            onChangedTie(true)
            onChangedShirt(true)
            calculate()
        }
        assertEquals(12, viewModel.discountLiveData.value)
    }
}