package com.marciarocha.dormmanager.ui.bedpicker


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.domain.model.Dorm
import kotlinx.android.synthetic.main.fragment_item_list.*


class BedPickerFragment : DialogFragment() {

    private val columnCount = 4
    private lateinit var selectedDorm: Dorm

    private val bedPickerAdapter by lazy {
        BedPickerAdapter(
            this::onSelect,
            Range(0, selectedDorm.availableBeds).toList()
        )
    }
    private var onResultListener: OnDialogResultListener? = null

    companion object {

        const val SELECTED_DORM = "selected-dorm"

        @JvmStatic
        fun newInstance(dorm: Dorm) =
            BedPickerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SELECTED_DORM, dorm)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onResultListener = activity as? OnDialogResultListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            selectedDorm = (it.getSerializable(SELECTED_DORM) as Dorm)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bedpicker_list.layoutManager = GridLayoutManager(context, columnCount)
        bedpicker_list.adapter = bedPickerAdapter
    }

    private fun onSelect(number: Int) {
        onResultListener?.onDialogResult(selectedDorm, number)
        dismiss()
    }
}
