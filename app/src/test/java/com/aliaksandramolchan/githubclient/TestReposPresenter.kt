package com.aliaksandramolchan.githubclient

import com.aliaksandramolchan.githubclient.api.GithubApi
import com.aliaksandramolchan.githubclient.model.Repo
import com.aliaksandramolchan.githubclient.presenter.ReposPresenter
import com.aliaksandramolchan.githubclient.scheduler.ReposContract
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class TestReposPresenter {
    private lateinit var presenter: ReposPresenter
    private lateinit var scheduler: TestScheduler

    private val view: ReposContract.ReposView = mock(ReposContract.ReposView::class.java)
    private val githubApi: GithubApi = mock(GithubApi::class.java)

    private var repos: List<Repo>? = null

    @Before
    fun setUp() {
        scheduler = TestScheduler()
        presenter = ReposPresenter(view, TestSchedulerProvider(scheduler), githubApi)
    }

    @Test
    fun onCreate_success() {
        val repo = mock(Repo::class.java)
        repos = listOf(repo)
        doReturn(Observable.just(repos)).`when`(githubApi).getRepos()
        presenter.onCreate()
        scheduler.triggerActions()
        verify(view).update(repos.orEmpty())
    }

    @Test
    fun onCreate_error() {
        val response = mock(Throwable::class.java)
        doReturn(Observable.just(response)).`when`(githubApi).getRepos()
        presenter.onCreate()
        scheduler.triggerActions()
        assertEquals(response.message, readLine())
    }

    fun onReceivedCloseEvent(isClosed: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onReceivedQuery(query: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @After
    fun onDestroy() {
        presenter.onDestroy()
    }

}

