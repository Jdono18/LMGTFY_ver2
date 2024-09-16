package com.example.lmgtfy_ver2

import android.content.Intent  // import the following
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var searchText: EditText  // initializes a variable for an edit text widget
    private lateinit var searchButton: Button  // initializes a variable for searchButton Button
    private lateinit var searchConfirmation: TextView  // initializes a variable to hold text displayed



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.enter_search_text)  // listed variables tied to listed ids
        searchButton = findViewById(R.id.search_button)
        searchConfirmation = findViewById(R.id.show_search_text)

        searchText.doAfterTextChanged {  // after text is changed in search text edit text widget the echoUserSearchTerm function is called
            echoUserSearchTerm()
        }

        searchButton.setOnClickListener {  // on click listener function tied to searchButton Button.  Calls the launchSearch function
            launchSearch()
        }
    }

    private fun launchSearch() {  // defines launchSearch function
        val text = searchText.text
        if (text.isBlank()) {  // if text input is blank do the follwing
            // TODO show user a message to enter text
            makeText(this, getString(R.string.no_text_error_message), LENGTH_SHORT).show()  // displays error message using Toast
        } else {
            // TODO show user a Toast confirmation
            makeText(this, getString(R.string.searching_confirmation_message, text), LENGTH_LONG).show() // displays confirmation message using Toast
            // TODO launch a web browser to search google
            googleSearch(text.toString())  // calls googleSearch function and converts data in text variable to a String
        }
    }
    private fun googleSearch(text: String) {  // defines googleSearch function
        val webAddress = "https://www.google.com/search?q=$text"  // initializes webAddress variable to hold google search url with user input value
        val uri = Uri.parse(webAddress)  // parses uri reference
        val browserIntent = Intent(Intent.ACTION_VIEW, uri) // feature request to take action to open the uri (web address) android will send request
        startActivity((browserIntent))
    }

    private fun echoUserSearchTerm() {  // defines echoUserSearchTerm function
        val text = searchText.text
        if (text.isNotBlank()) {  // if text is not blank
            searchConfirmation.text = getString(R.string.search_display_text, text) // display confirmation text in searchConfirmation TextView
        } else {
            searchConfirmation.text = getString(R.string.search_display_text, "...")  // displays ... text in searchConfirmation TextView

        }
    }
}