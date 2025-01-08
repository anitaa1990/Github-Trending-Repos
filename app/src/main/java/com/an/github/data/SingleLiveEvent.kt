package com.an.github.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)


//    fun observe(owner: LifecycleOwner, observer: Observer<T>) {
//
//        // Observe the internal MutableLiveData
//        super.observe(owner, Observer { t ->
//            if (mPending.compareAndSet(true, false)) {
//                observer.onChanged(t)
//            }
//        })
//    }
//
//
//    override fun setValue(t: T?) {
//        mPending.set(true)
//        super.value = t
//    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */

    fun call() {
        value = null
    }
}
