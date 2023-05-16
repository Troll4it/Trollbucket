package troll.btc.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter

/**
 * 输入框的防抖
 */
fun EditText.textChangeFlow(): Flow<Editable> = callbackFlow {
    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            s?.let { trySend(it).isSuccess }
        }

    }

    addTextChangedListener(watcher)
    awaitClose { removeTextChangedListener(watcher) }
}.filter {
    it.isNotEmpty()
}.debounce(500)



