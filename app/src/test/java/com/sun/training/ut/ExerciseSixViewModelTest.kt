package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.ui.excercise_six.ExerciseSixViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import kotlin.Throws

class ExerciseSixViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ExerciseSixViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseSixViewModel()
    }

    @Test
    fun totalPurchased_Return1000(){
        Assert.assertEquals(1000,viewModel.totalPurchased)
    }

    // KH không xem phim và tổng tiền mua hàng nhỏ 2000
    @Test
    fun totalMinute_TotalPurchasedLower2000_DontWatchFilm_Return0(){
        viewModel.apply {
            totalPurchased = 1000
            onWatchMovieChecked(false)
            calculateMinute()
        }
        Assert.assertEquals(0,viewModel.freeParkingInMinute.value)
    }

    // KH không xem phim và tổng tiền mua hàng là 2000
    @Test
    fun totalMinute_TotalPurchasedHigherOrEqual2000_DontWatchFilm_Return60(){
        viewModel.apply {
            totalPurchased = 2000
            onWatchMovieChecked(false)
            calculateMinute()
        }
        Assert.assertEquals(60,viewModel.freeParkingInMinute.value)
    }

    // KH không xem phim và tổng tiền mua hàng là 5000
    @Test
    fun totalMinute_TotalPurchasedHigherOrEqual5000_DontWatchFilm_Return120(){
        viewModel.apply {
            totalPurchased = 5000
            onWatchMovieChecked(false)
            calculateMinute()
        }
        Assert.assertEquals(120,viewModel.freeParkingInMinute.value)
    }

    // KH có xem phim và tổng tiền mua hàng là < 2000
    @Test
    fun totalMinute_TotalPurchasedLower2000_WatchFilm_Return180(){
        viewModel.apply {
            totalPurchased = 0
            onWatchMovieChecked(true)
            calculateMinute()
        }
        Assert.assertEquals(180,viewModel.freeParkingInMinute.value)
    }

    // KH có xem phim và tổng tiền mua hàng là >= 2000
    @Test
    fun totalMinute_TotalPurchasedHigherOrEqual2000_WatchFilm_Return240(){
        viewModel.apply {
            totalPurchased = 2000
            onWatchMovieChecked(true)
            calculateMinute()
        }
        Assert.assertEquals(240,viewModel.freeParkingInMinute.value)
    }

    // KH có xem phim và tổng tiền mua hàng là >= 5000
    @Test
    fun totalMinute_TotalPurchasedHigherOrEqual5000_WatchFilm_Return300(){
        viewModel.apply {
            totalPurchased = 5001
            onWatchMovieChecked(true)
            calculateMinute()
        }
        Assert.assertEquals(300,viewModel.freeParkingInMinute.value)
    }

}