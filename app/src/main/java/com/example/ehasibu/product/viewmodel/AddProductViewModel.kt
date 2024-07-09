import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ehasibu.product.data.EditRequest
import com.example.ehasibu.product.data.ProductRequest
import com.example.ehasibu.product.repo.ProductRepository
import kotlinx.coroutines.launch

private const val TAG = "addproduct"

class AddProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _isProductAdded = MutableLiveData<Boolean>()
    val isProductAdded: LiveData<Boolean> = _isProductAdded

    fun addProduct(product: ProductRequest) {
        viewModelScope.launch {
            try {
                val response = repository.addProduct(product)
                if (response.isSuccessful) {
                    _isProductAdded.postValue(true)
                    val message = response.body()?.message
                    if (message != null) {
                        Log.d(TAG, message)
                    }
                } else {
                    Log.d(TAG, "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.message}", e)
            }
        }
    }

    fun updateProduct(product: EditRequest) {
        viewModelScope.launch {
            try {
                val response = repository.updateProduct(product)
                if (response.isSuccessful) {
                    _isProductAdded.postValue(true)
                    val message = response.body()?.message
                    if (message != null) {
                        Log.d(TAG, message)
                    }
                } else {
                    Log.d(TAG, "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.message}", e)
            }
        }
    }

    class AddProductProvider(private val rep: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddProductViewModel(rep) as T
        }
    }
}
