package cn.law.im.client.protocal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AppBaseFragmentV4 : Fragment() {
    abstract fun setLayoutId(): Int

    abstract fun initVariables()

    abstract fun initListeners()

    abstract fun init()

    abstract fun destroyTask()

    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            if (setLayoutId() <= 0) {
                throw IllegalArgumentException("wrong layout id!")
            } else {
                rootView = inflater.inflate(setLayoutId(), container, false)
            }
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListeners()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (rootView != null) {
            rootView = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyTask()
    }

}