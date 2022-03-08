package com.example.sortoutlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.Menu
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sortoutlayout.databinding.FragmentFaqBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FAQFragment : Fragment() {

    private var mainMenu: Menu? = null
    private var _binding: FragmentFaqBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFaqBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_Second3Fragment_to_First3Fragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mainMenu?.findItem(R.id.action_settings)?.isVisible   = true
        _binding = null
    }

}