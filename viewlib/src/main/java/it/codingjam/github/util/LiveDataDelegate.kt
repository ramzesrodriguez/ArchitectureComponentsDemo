package it.codingjam.github.util


import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

class LiveDataDelegate<T: Any>(
        private val coroutines: Coroutines,
        initialState: T,
        private val liveData: MutableLiveData<T> = MutableLiveData()
): LiveDataObservable<T> {

    init {
        liveData.value = initialState
    }

    override fun observe(owner: LifecycleOwner, observer: (T) -> Unit) =
            liveData.observe(owner, Observer { observer(it!!) })

    override fun observeForever(observer: (T) -> Unit) =
            liveData.observeForever { observer(it!!) }

    suspend fun update(f: (T) -> T) {
        coroutines.onUi {
            updateOnUi(f)
        }
    }

    fun updateOnUi(f: (T) -> T) {
        liveData.value = f(this())
    }

    operator fun invoke() = liveData.value!!
}
