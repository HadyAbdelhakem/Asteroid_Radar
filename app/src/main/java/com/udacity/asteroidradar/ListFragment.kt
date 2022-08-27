package com.udacity.asteroidradar

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.databinding.FragmentListBinding
import com.udacity.asteroidradar.main.ListAdapter

class ListFragment : Fragment() {

    private lateinit var mAsteroidViewModel: AsteroidViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        /*val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())*/

        /*mAsteroidViewModel = ViewModelProvider(this).get(AsteroidViewModel::class.java)
        mAsteroidViewModel.readAllData.observe(viewLifecycleOwner , Observer { asteroid ->
            adapter.setData(asteroid)
        })*/

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete){
            mAsteroidViewModel.deleteAllAsteroid()
            Toast.makeText(requireContext() , "EveryThing deleted" , Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}