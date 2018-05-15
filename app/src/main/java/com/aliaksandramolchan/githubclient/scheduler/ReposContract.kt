package com.aliaksandramolchan.githubclient.scheduler

import com.aliaksandramolchan.githubclient.model.Repo

interface ReposContract {
    interface ReposPresenter {
        fun onCreate()
        fun onDestroy()
        fun onReceivedQuery(query: String?)
        fun onReceivedCloseEvent(isClosed: Boolean)
    }

    interface ReposView {
        fun update(repos: List<Repo>)
    }
}

