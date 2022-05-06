package com.example.capstone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.capstone.Database.NotesDatabase
import com.example.capstone.adapter.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

    var selectedColor = "#171C26"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,
        StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let{
                var notes = NotesDatabase.getDatabase(it).notesDao().getAllNotes()
                recyclerView.adapter = NotesAdapter(notes)
            }

        }
        addNote.setOnClickListener{
            replaceFragment(CreateNotesFragment.newInstance(), true)
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
            .commit()
    }
}