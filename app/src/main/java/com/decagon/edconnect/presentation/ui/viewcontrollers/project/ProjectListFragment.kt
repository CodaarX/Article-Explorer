package com.decagon.edconnect.presentation.ui.viewcontrollers.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.edconnect.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProjectListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ProjectListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupUI()
//        requestInitialAnimalsList()


    }


//    private fun setupUI() {
//        val adapter = createAdapter()
//        setupRecyclerView(adapter)
//        observeViewStateUpdates(adapter)
//    }
//
//    private fun createAdapter(): AnimalsAdapter {
//        return AnimalsAdapter()
//    }
//
//    private fun setupRecyclerView(animalsNearYouAdapter: AnimalsAdapter) {
//        binding.animalsRecyclerView.apply {
//            adapter = animalsNearYouAdapter
//            layoutManager = GridLayoutManager(requireContext(), ITEMS_PER_ROW)
//            setHasFixedSize(true)
//            addOnScrollListener(createInfiniteScrollListener(layoutManager as GridLayoutManager))
//        }
//    }
//
//    private fun createInfiniteScrollListener(layoutManager: GridLayoutManager): RecyclerView.OnScrollListener {
//        return object : InfiniteScrollListener(layoutManager, AnimalsNearYouFragmentViewModel.UI_PAGE_SIZE) {
//            override fun loadMoreItems() {
//                requestMoreAnimals()
//            }
//            override fun isLoading(): Boolean = viewModel.isLoadingMoreAnimals
//            override fun isLastPage(): Boolean = viewModel.isLastPage
//        }
//    }
//
//    private fun requestMoreAnimals() {
//        viewModel.onEvent(AnimalsNearYouEvent.RequestMoreAnimals)
//    }
//
//    private fun observeViewStateUpdates(adapter: AnimalsAdapter) {
//        viewModel.state.observe(viewLifecycleOwner) {
//            updateScreenState(it, adapter)
//        }
//    }
//
//    private fun updateScreenState(state: AnimalsNearYouViewState, adapter: AnimalsAdapter) {
//        binding.progressBar.isVisible = state.loading
//        adapter.submitList(state.animals)
//        handleNoMoreAnimalsNearby(state.noMoreAnimalsNearby)
//        handleFailures(state.failure)
//    }
//
//    private fun handleNoMoreAnimalsNearby(noMoreAnimalsNearby: Boolean) {
//        // Show a warning message and a prompt for the user to try a different
//        // distance or postcode
//    }
//
//    private fun handleFailures(failure: Event<Throwable>?) {
//        val unhandledFailure = failure?.getContentIfNotHandled() ?: return
//
//        val fallbackMessage = getString(R.string.an_error_occurred)
//
//        val snackbarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
//            fallbackMessage
//        }
//        else {
//            unhandledFailure.message!! }
//        if (snackbarMessage.isNotEmpty()) {
//            Snackbar.make(requireView(), snackbarMessage, Snackbar.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun requestInitialAnimalsList() {
//        viewModel.onEvent(AnimalsNearYouEvent.RequestInitialAnimalsList)
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }






//    if (state.data is List<Coin>){
//        items(state.data){ coin ->
//            CoinListItem(coin = coin, onItemClick = {
//                navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
//            })
//        }
//    }
//    if(state.error.isNotBlank()){
//        progressDialog.hideProgressDialog()
//        handleApiError(it, mainRetrofit, requireView(), sessionManager, database)
//
//    }
//    if (state.isLoading){
//        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProjectListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProjectListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}