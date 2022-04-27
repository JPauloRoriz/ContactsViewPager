package br.com.jpstudent.contactsviewpager.iu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.jpstudent.contactsviewpager.databinding.FragmentFavoriteContactsBinding
import br.com.jpstudent.contactsviewpager.iu.adapter.ContactsAdapter
import br.com.jpstudent.contactsviewpager.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FavoritesContactsFragment : BasePageFragment() {

    override fun getTabName(): String {
        return TAB_NAME
    }

    private lateinit var binding: FragmentFavoriteContactsBinding
    private val adapter by lazy { ContactsAdapter() }
    private val viewModel: ContactsViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContacts.adapter = adapter
        setupListeners()
        setupObservers()
        viewModel.getFavorites()
    }


    private fun setupListeners() {
        adapter.clickStar = {
            viewModel.tapOnChangeFavorite(it)
        }
    }

    private fun setupObservers() {
        viewModel.favoritesLiveData.observe(viewLifecycleOwner) {
            adapter.contacts = it
        }
    }

    companion object {
        private const val TAB_NAME = "Favoritos"
        fun newInstance(): FavoritesContactsFragment {
            val args = Bundle()
            val fragment = FavoritesContactsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}