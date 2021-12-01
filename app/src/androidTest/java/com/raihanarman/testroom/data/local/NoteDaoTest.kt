package com.raihanarman.testroom.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValue
import com.androiddevs.shoppinglisttestingyt.launchFragmentInHiltContainer
import com.google.common.truth.Truth.assertThat
import com.raihanarman.testroom.ui.ListNoteFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class NoteDaoTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("note_db")
    lateinit var database: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setup(){
        hiltRule.inject()
        dao = database.noteDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun testLaunchFragmentInHiltContainer(){
        launchFragmentInHiltContainer<ListNoteFragment> {

        }
    }

    @Test
    fun insertNote() = runBlockingTest {
        val note = Note("name", 1, id = 1)
        dao.insertNote(note)

        val allNotes = dao.observeAllNotes().getOrAwaitValue()

        assertThat(allNotes).contains(note)
    }

}