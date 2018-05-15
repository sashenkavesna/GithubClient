package com.aliaksandramolchan.githubclient.scheduler

import com.aliaksandramolchan.githubclient.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImpl : SchedulerProvider {

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
    override fun newThread(): Scheduler = Schedulers.newThread()
}

