package com.aliaksandramolchan.githubclient

import com.aliaksandramolchan.githubclient.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val testScheduler: TestScheduler): SchedulerProvider {
    override fun mainThread(): Scheduler= testScheduler
    override fun newThread(): Scheduler=testScheduler

}
