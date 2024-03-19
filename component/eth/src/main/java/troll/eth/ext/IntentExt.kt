package troll.eth.ext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf


inline fun <reified T> Context.intentOf(vararg pairs: Pair<String, *>): Intent =
    intentOf<T>(bundleOf(*pairs))

inline fun <reified T> Context.intentOf(bundle: Bundle): Intent =
    Intent(this, T::class.java).putExtras(bundle)
