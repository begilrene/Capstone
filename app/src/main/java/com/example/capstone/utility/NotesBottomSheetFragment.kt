package com.example.capstone.utility

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.capstone.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_notes_bottom.*

class NotesBottomSheetFragment : BottomSheetDialogFragment (){
    var selectedColor = "#4E33FF"
    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_notes_bottom, null)

        dialog.setContentView(view)

        val param = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams

        val behavior = param.behavior

        if(behavior is BottomSheetBehavior<*>){
            behavior.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
                override fun onSlide(bottomSheet: View, slideOffset: Float){
                    TODO("Not implemented")
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                   TODO("Not Implemented")
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener(){
        frameLayoutNotesLP.setOnClickListener{
            imgLightPurpleNoteTick.setImageResource(R.drawable.ic_check_box)
            imgTealNoteTick.setImageResource(0)
            imgYellowNoteTick.setImageResource(0)
            imgPurpleNoteTick.setImageResource(0)
            imgOrangeNoteTick.setImageResource(0)
            selectedColor = "#4E33FF"

            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", "Light_Purple")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }
        frameLayoutNotesYE.setOnClickListener{
            imgLightPurpleNoteTick.setImageResource(0)
            imgTealNoteTick.setImageResource(0)
            imgYellowNoteTick.setImageResource(R.drawable.ic_check_box)
            imgPurpleNoteTick.setImageResource(0)
            imgOrangeNoteTick.setImageResource(0)

            selectedColor = "#FFD633"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", "Yellow")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }
        frameLayoutNotesPU.setOnClickListener{
            imgLightPurpleNoteTick.setImageResource(0)
            imgTealNoteTick.setImageResource(0)
            imgYellowNoteTick.setImageResource(0)
            imgPurpleNoteTick.setImageResource(R.drawable.ic_check_box)
            imgOrangeNoteTick.setImageResource(0)

            selectedColor = "#4E33FF"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", "Purple")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }
        frameLayoutNotesTE.setOnClickListener{
            imgLightPurpleNoteTick.setImageResource(0)
            imgTealNoteTick.setImageResource(R.drawable.ic_check_box)
            imgYellowNoteTick.setImageResource(0)
            imgPurpleNoteTick.setImageResource(0)
            imgOrangeNoteTick.setImageResource(0)

            selectedColor = "#FF03DAC5"
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", "Teal")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }
        frameLayoutNotesOR.setOnClickListener{
            imgLightPurpleNoteTick.setImageResource(0)
            imgTealNoteTick.setImageResource(0)
            imgYellowNoteTick.setImageResource(0)
            imgPurpleNoteTick.setImageResource(0)
            imgOrangeNoteTick.setImageResource(R.drawable.ic_check_box)

            selectedColor = "#FF7746"
            Log.d("COLOR", selectedColor)
            val intent = Intent("bottom_sheet_action")
            intent.putExtra("actionColor", "Orange")
            intent.putExtra("selectedColor", selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }

    }

    companion object{
        fun newInstance(noteID: Int): NotesBottomSheetFragment{
            val args = Bundle()
            val fragment = NotesBottomSheetFragment()
            fragment.arguments = args
            return fragment
        }
    }
}