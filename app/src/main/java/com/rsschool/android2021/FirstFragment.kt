package com.rsschool.android2021

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.math.max

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minEditText: EditText? = null
    private var maxEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...
        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)
        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment
            if ((minEditText?.text?.isNotEmpty()
                    ?: 0) as Boolean && (maxEditText?.text?.isNotEmpty() ?: 0) as Boolean
            ) {

                val min: Int = Integer.parseInt(minEditText?.text.toString())
                val max = Integer.parseInt(maxEditText?.text.toString())
if (min<=max) {
    val fragment = SecondFragment()
    val args = Bundle()
    args.putInt(MIN_VALUE_KEY, min)
    args.putInt(MAX_VALUE_KEY, max)
    fragment.arguments = args
    var fr = parentFragmentManager.beginTransaction()
    fr?.replace(R.id.container, fragment)
    fr?.commit()
}
else {
   Toast.makeText(requireContext(), "Wrong numbers", Toast.LENGTH_LONG)
}

            } else {
                Toast.makeText(requireContext(), "Empty fields", Toast.LENGTH_LONG)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}