package com.example.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdit: EditText
    lateinit var noteDescriptionEdit: EditText
    lateinit var addUpdateBtn: Button
    lateinit var noteModalHeader: TextView
    lateinit var viewModal: NoteViewModal
    var noteID = -1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        noteTitleEdit = findViewById(R.id.idEditNoteTitle)
        noteDescriptionEdit = findViewById(R.id.idEditNoteDescription)
        addUpdateBtn = findViewById(R.id.idBtnAddUpdate)
        noteModalHeader = findViewById(R.id.AddingEditTextHeader)
        viewModal = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModal::class.java)

        val noteType = intent.getStringExtra("noteType")

        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1)
            addUpdateBtn.setText("UPDATE")
            noteModalHeader.setText("UPDATE NOTE")
            noteTitleEdit.setText(noteTitle)
            noteDescriptionEdit.setText(noteDescription)



        }else{
            addUpdateBtn.setText("SAVE NOTE")

        }

        addUpdateBtn.setOnClickListener{
            val noteTitle = noteTitleEdit.text.toString();
            val noteDescription = noteDescriptionEdit.text.toString();

            if(noteType.equals("Edit")) {
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:MM")
                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteID
                    viewModal.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated....", Toast.LENGTH_LONG).show()
                }
            }else {
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:MM")
                    val currentDate:String = sdf.format(Date())
                    viewModal.addNote(Note(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added...", Toast.LENGTH_LONG).show()
                }
            }

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}