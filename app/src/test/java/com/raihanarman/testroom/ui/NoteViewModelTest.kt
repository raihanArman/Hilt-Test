package com.raihanarman.testroom.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.shoppinglisttestingyt.other.Status
import com.google.common.truth.Truth.assertThat
import com.raihanarman.testroom.MainCoroutineRule
import com.raihanarman.testroom.getOrAwaitValueTest
import com.raihanarman.testroom.repositories.FakeNoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup(){
        viewModel = NoteViewModel(FakeNoteRepository())
    }

    @Test
    fun `insert note with empty field, returns error`(){
        viewModel.insertNote("name", "")

        val value = viewModel.insertNoteStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert note with age is not number, return error`(){
        viewModel.insertNote("note", "kevin")

        val value = viewModel.insertNoteStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert note with valid input, returns success`(){
        viewModel.insertNote("note", "5")

        val value = viewModel.insertNoteStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)

    }


}