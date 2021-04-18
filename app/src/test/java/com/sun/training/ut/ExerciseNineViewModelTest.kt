package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.model.No9Input
import com.sun.training.ut.data.model.No9Result
import com.sun.training.ut.ui.excercise_six.ExerciseSixViewModel
import com.sun.training.ut.ui.exercise_nine.ExerciseNineViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import kotlin.Throws

class ExerciseNineViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: ExerciseNineViewModel
    lateinit var no9Input : No9Input
    lateinit var no9ResultExpect: No9Result

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseNineViewModel()
        no9Input = No9Input(false,false)
        no9ResultExpect = No9Result(false)
    }

    //Không có vật dùng nào.
    @Test
    fun RoomFound_WithoutMagicWandAndMaster_ReturnRoomIsNotFound(){
        no9ResultExpect = No9Result(false,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(false)
            onMagicWandChecked(false)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có kiếm.
    @Test
    fun RoomFound_WithSword_ReturnRoomIsNotFound(){
        no9ResultExpect = No9Result(false,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(true)
            onMagicWandChecked(false)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có chìa khoá.
    @Test
    fun RoomFound_WithKey_ReturnRoomIsNotFound(){
        no9ResultExpect = No9Result(false,null,null)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(false)
            onMagicWandChecked(false)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có đũa phép.
    @Test
    fun RoomFound_WithWand_ReturnRoomCannotEnter(){
        no9ResultExpect = No9Result(true,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(false)
            onMagicWandChecked(true)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có quân sư.
    @Test
    fun RoomFound_WithMaster_ReturnRoomCannotEnter(){
        no9ResultExpect = No9Result(true,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(false)
            onMagicWandChecked(false)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Có đũa phép, có kiếm.
    @Test
    fun RoomFound_CannotIn_WithoutMagicWand_WithSword_ReturnRoomCannotEnter(){
        no9ResultExpect = No9Result(true,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(true)
            onMagicWandChecked(true)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có quân sư và kiếm.
    @Test
    fun RoomFound_CannotIn_WithMaster_WithSword_ReturnRoomCannotEnter(){
        no9ResultExpect = No9Result(true,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(true)
            onMagicWandChecked(false)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có đũa phép và chìa khoá.
    @Test
    fun RoomFound_WithWand_WithKey_ReturnBossCannotBeBeatean(){
        no9ResultExpect = No9Result(true,true,null)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(false)
            onMagicWandChecked(true)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có quân sư, và chìa khoá.
    @Test
    fun RoomFound_WithMaster_WithKey_ReturnBossCannotBeBeatean(){
        no9ResultExpect = No9Result(true,true,null)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(false)
            onMagicWandChecked(false)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Có cả quân sư, đũa phép.
    @Test
    fun RoomFound_WithWandAndMaster_ReturnRoomCannotEnter(){
        no9ResultExpect = No9Result(true,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(false)
            onMagicWandChecked(true)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Có cả chìa khoá, kiếm.
    @Test
    fun RoomFound_WithSwordAndKey_ReturnRoomIsNotFound(){
        no9ResultExpect = No9Result(false,null,null)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(true)
            onMagicWandChecked(false)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có quân sư,đũa phép và kiếm.
    @Test
    fun RoomFound_CannotIn_WithMaster_WithWand_WithSword_ReturnRoomCannotEnter(){
        no9ResultExpect = No9Result(true,null,null)
        viewModel.apply {
            onKeyChecked(false)
            onLightSwordChecked(true)
            onMagicWandChecked(true)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Chỉ có đũa phép, chìa khoá, và kiếm.
    @Test
    fun RoomFound_WithWand_WithKey_WithSword_ReturnYouWin(){
        no9ResultExpect = No9Result(true,true,true)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(true)
            onMagicWandChecked(true)
            onMasterChecked(false)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Có quân sư, chìa khoá và kiếm.
    @Test
    fun RoomFound_WithMaster_WithKey_WithSword_ReturnYouWin(){
        no9ResultExpect = No9Result(true,true,true)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(true)
            onMagicWandChecked(false)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    // Có cả quân sư, đũa phép, có chìa khoá.
    @Test
    fun RoomFound_WithWandAndMaster_WithKey_ReturnBossCannotBeBeatean(){
        no9ResultExpect = No9Result(true,true,null)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(false)
            onMagicWandChecked(true)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }

    //Có quân sư, có đũa phép, có chìa khoá, có kiếm.
    @Test
    fun RoomFound_WithWandAndMaster_WithKey_WithSword_ReturnYouWin(){
        no9ResultExpect = No9Result(true,true,true)
        viewModel.apply {
            onKeyChecked(true)
            onLightSwordChecked(true)
            onMagicWandChecked(true)
            onMasterChecked(true)
            checkResultExerciseNine()
        }
        Assert.assertEquals(no9ResultExpect,viewModel.resultBeatBoss.value)
    }
}