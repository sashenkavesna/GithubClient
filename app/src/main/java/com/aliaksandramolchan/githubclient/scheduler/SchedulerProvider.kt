package com.aliaksandramolchan.githubclient.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun newThread(): Scheduler
    fun mainThread(): Scheduler
}

