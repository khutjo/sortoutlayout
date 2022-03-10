package com.example.sortoutlayout

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sortoutlayout.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import kotlinx.coroutines.NonCancellable.start
import org.json.JSONObject
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.android.volley.RequestQueue
import com.android.volley.toolbox.*
import org.json.JSONException
import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class homeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val accesscode = "MyPrefs"
    private val codekey = "key"
    private val hostname = "hostip"
    private var url: String? = null
    private var key: String? = null


    private var requestQueue: RequestQueue? = null
    private var parentLinearLayout: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        parentLinearLayout = activity?.findViewById(R.id.parent_linear_layout)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonadd.setOnClickListener {
            val inflater = activity?.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.field, null)
             parentLinearLayout!!.addView(rowView, parentLinearLayout!!.childCount - 1)
        }









    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun sendrequest(massage: String,view: View){
        requestQueue = Volley.newRequestQueue(activity)
        val url = "https://espwebapi.azurewebsites.net/posttest"
        Log.i("shit","im just in")
//        val url = "https://api.myjson.com/bins/xbspb"
        val request = object : JsonObjectRequest(Method.POST, url, null, Response.Listener {
                response ->try {
                    val jsonArray = response.getJSONArray("extra")
            Log.i("json",response.toString())
            Log.i("firstName",response.getString("firstName"))
            Log.i("lastName",response.getString("lastName"))
                    for (i in 0 until jsonArray.length()) {

                        val employee = jsonArray.getJSONObject(i)
                        Log.i("exfirstName",employee.getString("exfirstName"))
                        Log.i("exlastName",employee.getString("exlastName"))
                    }
//                        val firstName = employee.getString("firstname")
//                        val age = employee.getInt("age")
//                        val mail = employee.getString("mail")
//
//                        textView.append("$firstName, $age, $mail\n\n")
//                    }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("JSONException",e.printStackTrace().toString())
        }
        }, Response.ErrorListener {
                error -> error.printStackTrace()
            Log.e("ErrorListener",error.printStackTrace().toString())
        }){
            // Providing Request Headers

            override fun getHeaders(): Map<String, String> {
                // Create HashMap of your Headers as the example provided below

                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["app_key"] = codekey

                return headers
            }
        }
        requestQueue?.add(request)
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