package com.troll.trollbucketimport troll.eth.base.ViewModelExclass MainViewModel() : ViewModelEx() {    val repo = MainRepo()    val live = repo.live    fun test() {        repo.test()    }}