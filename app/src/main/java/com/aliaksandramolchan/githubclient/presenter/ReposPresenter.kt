package com.aliaksandramolchan.githubclient.presenter

import com.aliaksandramolchan.githubclient.api.GithubApi
import com.aliaksandramolchan.githubclient.scheduler.ReposContract
import com.aliaksandramolchan.githubclient.scheduler.SchedulerProvider
import com.aliaksandramolchan.githubclient.model.Repo
import io.reactivex.disposables.Disposable

class ReposPresenter(val reposView: ReposContract.ReposView,
                     val scheduler: SchedulerProvider,
                     val githubApi: GithubApi) : ReposContract.ReposPresenter {

    private var repos: List<Repo>? = null

    private var disposable: Disposable? = null

    override fun onCreate() {
        disposable = githubApi.getRepos()
                .subscribeOn(scheduler.newThread())
                .observeOn(scheduler.mainThread())
                .subscribe({ result ->
                    run {
                        reposView.update(result)
                        repos = result
                    }
                },
                        { error -> print(error.message) }
                )

    }

    override fun onDestroy() {
        disposable?.dispose();
    }

    override fun onReceivedQuery(query: String?) {
        reposView.update(repos?.filter { repo -> repo.name.equals(query) } as List<Repo>)
    }

    override fun onReceivedCloseEvent(isClosed: Boolean) {
        if (isClosed == true)
            reposView.update(repos as List<Repo>)
    }
}


