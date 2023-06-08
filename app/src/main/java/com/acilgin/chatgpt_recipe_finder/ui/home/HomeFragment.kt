package com.acilgin.chatgpt_recipe_finder.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.acilgin.chatgpt_recipe_finder.R
import com.acilgin.chatgpt_recipe_finder.data.model.Content
import com.acilgin.chatgpt_recipe_finder.databinding.FragmentHomeBinding
import com.acilgin.chatgpt_recipe_finder.ui.adapter.StepsRecyclerViewAdapter
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // create viewbinding
    private lateinit var _binding: FragmentHomeBinding

    // define viewmodel here
    private val viewModel: HomeViewModel by viewModels()

    // inflate the layout here
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.generateRecipeFromChat(
            type = "Salads",
            ingredients = "erik,elma,marul",
            language = "tr"
        ) */
        setupUi()

        setupObservers()

        setupListeners()
    }

    private fun setupUi(){
        val languageSpinner = _binding.languageSpinner
        val typeSpinner = _binding.typeSpinner

        val languages = arrayOf("English", "Turkish")

        val types = arrayOf("Appetizers", "Main Courses", "Desserts", "Salads", "Soups")

        val languageAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        val typeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)

        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        languageSpinner.adapter = languageAdapter
        typeSpinner.adapter = typeAdapter

        // add hint to search bar
        _binding.searchView.queryHint= "ingredients separated by comma."

        testData()
    }

    private fun testData(){

        val testContent = Content(
            name = "Apple and Meat Crostini",
            language = "en",
            imagePrompt = "Crisp crostini with juicy apple and savory meat",
            type = "appetizers",
            ingredients = arrayListOf(
                "Baguette slices",
                "Thinly sliced apples",
                "Cooked ground meat",
                "Tomato sauce",
                "Shredded mozzarella cheese"
            ),
            steps = arrayListOf(
                "Preheat the oven to 350°F (175°C) and arrange the baguette slices on a baking sheet.",
                "Spread tomato sauce on each slice and top with cooked ground meat.",
                "Add thinly sliced apples on top, then sprinkle with shredded mozzarella cheese.",
                "Bake in the oven for 10-12 minutes, until the cheese is melted and bubbly.",
                "Serve hot and enjoy!"
            )
        )
        _binding.recipeItemCardLayout.recipeName.text = testContent.name
        _binding.recipeItemCardLayout.recipeType.text = testContent.type
        testContent.ingredients.let {
            _binding.recipeItemCardLayout.ingredientsLabel.text = it.joinToString(",")
        }
        _binding.recipeItemCardLayout.stepsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
         val stepsAdapter = StepsRecyclerViewAdapter(testContent.steps)
        _binding.recipeItemCardLayout.stepsRecyclerView.adapter = stepsAdapter

        _binding.recipeItemCardLayout.recipeImage.visibility = View.VISIBLE
        Glide.with(requireContext()).load(Uri.parse("https://oaidalleapiprodscus.blob.core.windows.net/private/org-9Q06RcLmoLVL4aHoWVjUyuVA/user-dB8mCuNvAfZprtT084LMzn1T/img-R2CB4D1hmdRC7XjEViZXTYr4.png?st=2023-05-18T06%3A04%3A39Z&se=2023-05-18T08%3A04%3A39Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-05-17T20%3A33%3A25Z&ske=2023-05-18T20%3A33%3A25Z&sks=b&skv=2021-08-06&sig=gnOGePHphpO49TdhYM3CKlMeVZD7YaxPMsqzCnImXIw%3D")).into(_binding.recipeItemCardLayout.recipeImage)
    }
    private fun setupListeners(){
        _binding.btnSearch.setOnClickListener {
            val language = _binding.languageSpinner.selectedItem.toString()
            val type = _binding.typeSpinner.selectedItem.toString()
            val ingredients = _binding.searchView.query.toString()
            // log the values
            Toast.makeText(requireContext(), "Language: $language, Type: $type, Ingredients: $ingredients", Toast.LENGTH_SHORT).show()
            progressbar(true)
            viewModel.generateRecipeFromChat(
                type = type,
                ingredients = ingredients,
                language = language
            )
        }
    }

    private fun setupObservers() {
        viewModel.contentState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }.launchIn(lifecycleScope)

        viewModel._imageState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleImageStateChange(state) }.launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: ContentState<Content?>) {
        when (state) {
            is ContentState.Initial -> Unit
            is ContentState.Loading -> handleLoding(state.isLoading)
            is ContentState.Success -> handleSuccess(state.data)
            is ContentState.Error -> handleError(state.message)
        }
    }
    private fun handleImageStateChange(state: ImageState<String?>) {
        when (state) {
            is ImageState.Initial -> Unit
            is ImageState.Loading -> handleLoding(state.isLoading)
            is ImageState.Success -> handleImageSuccess(state.data)
            is ImageState.Error -> handleError(state.message)
        }
    }

    private fun handleLoding(isLoading: Boolean) {
       progressbar(isLoading)
    }

    private fun handleError(message: String) {
        _binding.recipeItemCardLayout.root.visibility = View.GONE
        toast(message)
        progressbar(false)
    }

    private fun progressbar(isLoading: Boolean) {
        if (isLoading) {
            _binding.loadingProgressBar.visibility = View.VISIBLE
        } else {
            _binding.loadingProgressBar.visibility = View.GONE
        }
    }

    private fun handleSuccess(data: Content?) {
        _binding.recipeItemCardLayout.root.visibility = View.VISIBLE
        // take the data and set it in included recipe layout
        _binding.recipeItemCardLayout.recipeName.text = data?.name
        _binding.recipeItemCardLayout.recipeType.text = data?.type

        // create image :
        viewModel.generateRecipeImage(data?.imagePrompt ?: "a meal")
        data?.ingredients?.let {
            _binding.recipeItemCardLayout.ingredientsLabel.text = it.joinToString(",")
        }
        _binding.recipeItemCardLayout.stepsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        data?.steps?.let {
            _binding.recipeItemCardLayout.stepsRecyclerView.adapter = StepsRecyclerViewAdapter(it)

        }

    }
    private fun handleImageSuccess(url: String?) {
        _binding.recipeItemCardLayout.root.visibility = View.VISIBLE
        // take the url string and load image with glide
        url?.let {
            _binding.recipeItemCardLayout.recipeImage.visibility = View.VISIBLE
            Glide.with(requireContext()).load(Uri.parse(it)).into(_binding.recipeItemCardLayout.recipeImage)
        }

    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}
