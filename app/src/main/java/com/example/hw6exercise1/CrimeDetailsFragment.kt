package com.example.hw6exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hw6exercise1.databinding.FragmentCrimeDetailBinding
import junit.runner.Version.id
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.regex.Pattern.matches


class CrimeDetailsFragment :Fragment() {


private lateinit var crime: Crime
//private lateinit var binding: FragmentCrimeDetailBinding
private var _binding: FragmentCrimeDetailBinding? = null



  val binding
 get() = checkNotNull(_binding){
     "Cannot access binding because it is null. Is the view Visible?"
 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crime = Crime(

            UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false

        )
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        binding = FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        _binding = FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //Wiring up views in a fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                crime = crime.copy(title = text.toString())
            }

            crimeDate.apply {
                text = crime.date.toString()
                isEnabled = false
            }

            //Listener for TextBox change
            crimeSolved.setOnCheckedChangeListener{_, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

@RunWith(AndroidJUnit4::class)
class MyTestSuite {
    @Test fun testEventFragment() {
        val scenario = launchFragmentInContainer<CrimeDetailsFragment>()
        scenario.recreate()
    }
}
//
//@RunWith(AndroidJUnit4::class)
//class MyTestSuite2 {
//    @Test fun testEventFragment() {
//        val scenario = launchFragmentInContainer<CrimeDetailsFragment>()
//        onCreateView(withId(R.id.crime))
//            .check(matches(isDisplayed()))
//    }
//
//
//}