package com.udacity.asteroidradar.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var mAsteroidViewModel: AsteroidViewModel
    private lateinit var binding : FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater)
        binding.lifecycleOwner = this

        mAsteroidViewModel = ViewModelProvider(this).get(AsteroidViewModel::class.java)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val id = binding.editTextId.text.toString().toLong()
        val codename = binding.editTextCodename.text.toString()
        val closeApproachDate = binding.editTextCloseApproachDate.text.toString()
        val absoluteMagnitude = binding.editTextAbsoluteMagnitude.text.toString().toDouble()
        val estimatedDiameter = binding.editTextEstimatedDiameter.text.toString().toDouble()
        val relativeVelocity = binding.editTextRelativeVelocity.text.toString().toDouble()
        val distanceFromEarth = binding.editTextDistanceFromEarth.text.toString().toDouble()
        val isPotentiallyHazardous = binding.editTextIsPotentiallyHazardous.text.toString().toBoolean()

        val asteroid = Asteroid(id , codename , closeApproachDate , absoluteMagnitude , estimatedDiameter,
            relativeVelocity , distanceFromEarth , isPotentiallyHazardous)

        mAsteroidViewModel.addAsteroid(asteroid)
        Toast.makeText(requireContext() , "Success" , Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}