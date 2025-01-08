package com.an.github.ui.extensions

inline fun <E : Any, T : Collection<E>> T?.withNotNullNorEmpty(func: T.() -> Unit): Otherwise {
    return if (this != null && this.isNotEmpty()) {
        with (this) { func() }
        OtherwiseIgnore
    } else {
        OtherwiseInvoke
    }
}

inline fun  <E : Any, T : Collection<E>> T?.whenNotNullNorEmpty(func: (T) -> Unit): Otherwise {
    return if (this != null && this.isNotEmpty()) {
        func(this)
        OtherwiseIgnore
    } else {
        OtherwiseInvoke
    }
}

inline fun <E : Any, T : Collection<E>> T?.withNullOrEmpty(func: () -> Unit): OtherwiseWithValue<T> {
    return if (this == null || this.isEmpty()) {
        func()
        OtherwiseWithValueIgnore<T>()
    } else {
        OtherwiseWithValueInvoke(this)
    }
}

inline fun <E : Any, T : Collection<E>> T?.whenNullOrEmpty(func: () -> Unit): OtherwiseWhenValue<T> {
    return if (this == null || this.isEmpty()) {
        func()
        OtherwiseWhenValueIgnore<T>()
    } else {
        OtherwiseWhenValueInvoke(this)
    }
}

interface Otherwise {
    fun otherwise(func: () -> Unit): Unit
}

object OtherwiseInvoke : Otherwise {
    override fun otherwise(func: () -> Unit): Unit {
        func()
    }
}

object OtherwiseIgnore : Otherwise {
    override fun otherwise(func: () -> Unit): Unit {
    }
}

interface OtherwiseWithValue<T> {
    fun otherwise(func: T.() -> Unit): Unit
}

class OtherwiseWithValueInvoke<T>(val value: T) : OtherwiseWithValue<T> {
    override fun otherwise(func: T.() -> Unit): Unit {
        with (value) { func() }
    }
}

class OtherwiseWithValueIgnore<T> : OtherwiseWithValue<T> {
    override fun otherwise(func: T.() -> Unit): Unit {
    }
}

interface OtherwiseWhenValue<T> {
    fun otherwise(func: (T) -> Unit): Unit
}

class OtherwiseWhenValueInvoke<T>(val value: T) : OtherwiseWhenValue<T> {
    override fun otherwise(func: (T) -> Unit): Unit {
        func(value)
    }
}

class OtherwiseWhenValueIgnore<T> : OtherwiseWhenValue<T> {
    override fun otherwise(func: (T) -> Unit): Unit {
    }
}