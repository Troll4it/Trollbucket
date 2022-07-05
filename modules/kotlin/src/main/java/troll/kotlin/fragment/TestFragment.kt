package troll.kotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import troll.btc.constants.EMPTY
import troll.eth.base.viewbinding.BaseFragment
import troll.kotlin.databinding.FragmentTestBinding

class TestFragment : BaseFragment<FragmentTestBinding>() {

    private val TAG = "TestFragment"
    private var type: String = EMPTY

    override fun getBinding(inflater: LayoutInflater): FragmentTestBinding =
        FragmentTestBinding.inflate(inflater)

    override fun flowData() {
        type = FragViewModel.type
        Log.e(TAG, "flowData::::type:::: $type")
        bd.tv.setText(type)
        Log.e(TAG, "flowData:::: bd.tv.text: " + bd.tv.text)
    }

    override fun flowView() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume::::bd.tv.text::::" + bd.tv.text)
        Log.e(TAG, "onResume::::bd.tv.text::::" + bd.tv.text)
        Log.e(TAG, "onResume::::bd.tv.text::::" + bd.tv.text)
        Log.e(TAG, "onResume:::::type:::::: $type")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "onDetach: ")
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "onSaveInstanceState: " + outState.toString())
    }

    override fun setRetainInstance(retain: Boolean) {
        super.setRetainInstance(retain)
        Log.e(TAG, "setRetainInstance: ")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e(TAG, "onViewStateRestored: " + savedInstanceState.toString())
    }
}