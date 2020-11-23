package com.assessment_task.sample_test.utill

interface OnModelClickListener<in T : Any> {
    fun onClick(model: T)
}