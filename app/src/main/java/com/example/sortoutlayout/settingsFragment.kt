package com.example.sortoutlayout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.Menu
import android.view.ViewGroup
import com.example.sortoutlayout.databinding.FragmentSettingsBinding
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import android.preference.PreferenceManager
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class settingsFragment : Fragment() {

    private var mainMenu: Menu? = null
    private var _binding: FragmentSettingsBinding? = null
    private var EditTexkey: EditText? = null
    private var EditTextlink: EditText? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val accesscode = "MyPrefs"
    val codekey = "key"
    val hostname = "hostip"
    private var url: String? = null
    private var key: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EditTextlink = activity?.findViewById(R.id.editTextTextPersonName)!!
        EditTexkey = activity?.findViewById(R.id.editTextTextPassword)!!
        gethostname()
        getkey()
        binding.buttonSecond.setOnClickListener {
            val sharedpreferences = activity?.getSharedPreferences(accesscode, Context.MODE_PRIVATE);
            val editor = sharedpreferences?.edit()
            editor?.putString(codekey, EditTexkey!!.text.toString())
            editor?.putString(hostname, EditTextlink!!.text.toString())
            editor?.apply();
            Snackbar.make(view, "Accesskey updated", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mainMenu?.findItem(R.id.action_settings)?.isVisible   = true
        _binding = null
    }

    fun gethostname(){
        val sharedpreferences = activity?.getSharedPreferences(accesscode, Context.MODE_PRIVATE);
        url = sharedpreferences?.getString(hostname, "")
        if(url != null && url != "")
            EditTextlink?.setText(url)
    }

    fun getkey(){
        val sharedpreferences = activity?.getSharedPreferences(accesscode, Context.MODE_PRIVATE);
        key = sharedpreferences?.getString(codekey, "")
        if(key != null && key != "")
            EditTexkey?.setText("*************")
    }

    fun checkSharedPreferences(view: View): Boolean {
        var valid = true
        if (key == null || key == "") {
            val sharedpreferences = activity?.getSharedPreferences(accesscode, Context.MODE_PRIVATE);
            key = sharedpreferences?.getString(codekey, "")
            if (key == null || key == "") {
                Snackbar.make(view, "Please set the access key", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                valid = false
            }
        }
        if(url == null || url == "") {
            val sharedpreferences = activity?.getSharedPreferences(accesscode, Context.MODE_PRIVATE)
            url = sharedpreferences?.getString(hostname, "")
            if(url == null || url == "") {
                Snackbar.make(view, "Please set the server link", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                valid = false
            }

        }
        return valid
    }
}