import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T: ViewModel> Fragment.viewModel(noinline creator: (() -> T)?= null) : T {

    return if (creator == null) {
        ViewModelProvider(this)[T::class.java]
    }else {
        ViewModelProvider(this, BaseViewModelFactory(creator))[T::class.java]
    }

}

@Suppress("UNCHECKED_CAST")
class BaseViewModelFactory<T>(val creator: (() -> T)) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator as T
    }

}
