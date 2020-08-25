/*
 * Copyright (C) 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.kotlincoroutines.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.kotlincoroutines.fakes.MainNetworkFake
import com.example.android.kotlincoroutines.fakes.TitleDaoFake
import com.google.common.truth.Truth
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class TitleRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun whenRefreshTitleSuccess_insertsRows() {
        // TODO: Write this test
        // launch starts a coroutine then immediately returns
        // since this is asynchronous code, this may be called *after* the test completes
        // test function returns immediately, and
        // doesn't see the results of refreshTitle
        //so use runBlockingTest

        //runBlockingTest will always block the caller, just like a regular function call.
        // The coroutine will run synchronously on the same thread.

        //If you don't want to change the behavior of coroutines
        // – for example in an integration test –
        // you can instead use runBlocking with the default implementations of all dispatchers.
        runBlockingTest {
            val titleDao = TitleDaoFake("title")
            val subject = TitleRepository(
                    MainNetworkFake("OK"),
                    titleDao
            )

            //testing timeouts
            //launch another coroutine
            launch {
                subject.refreshTitle()
            }

            //advance of 5 sec (timeout of coroutine)
            advanceTimeBy(5_000)
            Truth.assertThat(titleDao.nextInsertedOrNull()).isEqualTo("OK")
        }
    }

    @Test(expected = TitleRefreshError::class)
    fun whenRefreshTitleTimeout_throws() {
        // TODO: Write this test
        throw TitleRefreshError("Remove this – made test pass in starter code", null)
    }
}