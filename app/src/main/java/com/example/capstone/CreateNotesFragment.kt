package com.example.capstone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.capstone.Database.NotesDatabase
import com.example.capstone.Entities.Notes
import com.example.capstone.utility.NotesBottomSheetFragment
import kotlinx.android.synthetic.main.fragment_create_notes.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateNotesFragment : BaseFragment() {
    var currentDate: String? = null
    var selectedColor = "#171C26"
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteId = requireArguments().getInt("noteId", -1)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_notes, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNotesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadcastReceiver, IntentFilter("bottom_sheet_action"))
        val sdf = SimpleDateFormat("M/dd/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())
        Log.d("COLOR", selectedColor)
        colorView.setBackgroundColor(Color.parseColor(selectedColor))

        tvDateTime.text = currentDate

        imgDoneCN.setOnClickListener{
            saveNote()
        }

        imgBackCN.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        moreIV.setOnClickListener {
            var noteBottomSheetFragment = NotesBottomSheetFragment.newInstance(noteId)
            noteBottomSheetFragment.show(requireActivity().supportFragmentManager, "Notes bottom fragment")

        }
    }

    private fun saveNote(){
        if (editTextNotesTitle.text.isNullOrEmpty())
            Toast.makeText(context, "Title required!", Toast.LENGTH_LONG).show()
        if (editTextNotesSubject.text.isNullOrEmpty())
            Toast.makeText(context, "Subject line required!", Toast.LENGTH_LONG).show()
        if(editTextNotesDescription.text.isNullOrEmpty())
            Toast.makeText(context, "Body required!", Toast.LENGTH_LONG).show()

        launch {
            var notes = Notes()
            notes.title = editTextNotesTitle.text.toString()
            notes.subject = editTextNotesSubject.text.toString()
            notes.dateTime = currentDate
            notes.noteText = editTextNotesSubject.text.toString()
            notes.color = selectedColor
            Log.d("Saving a COLOR", "$selectedColor")
            context?.let{
                NotesDatabase.getDatabase(it).notesDao().insertNotes(notes)
                editTextNotesTitle.setText("")
                editTextNotesDescription.setText("")
                editTextNotesSubject.setText("")

            }
        }

    }

    fun replaceFragment(frag: Fragment, transition:Boolean){
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if(transition){
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.frameLayout, frag)
            .addToBackStack(frag.javaClass.simpleName)
    }

    private val broadcastReceiver : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d("COLOR", "Intent recieved! Color: ${p1!!.getStringExtra("selectedColor")}")
            var actionColor = p1!!.getStringExtra("actionColor")
            when(actionColor){
                "Light_Purple" -> {
                     selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                 }
                "Yellow" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Purple" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Teal" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Orange" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }

                else -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }

            }
        }

    }
    override fun onDestroy(){
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }
}