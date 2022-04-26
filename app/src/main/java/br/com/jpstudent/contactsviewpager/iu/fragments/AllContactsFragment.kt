package br.com.jpstudent.contactsviewpager.iu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.jpstudent.contactsviewpager.databinding.FragmentAllContactsBinding
import br.com.jpstudent.contactsviewpager.iu.adapter.ContactsAdapter
import br.com.jpstudent.contactsviewpager.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AllContactsFragment : BasePageFragment() {

    private lateinit var binding: FragmentAllContactsBinding
    private val adapter by lazy { ContactsAdapter() }
    private val viewModel: ContactsViewModel by sharedViewModel()

    override fun getTabName(): String {
        return TAB_NAME
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContacts.adapter = adapter
        setupListeners()
        setupObservers()
        viewModel.getAll()
    }


    private fun setupListeners() {
        adapter.clickStar = {
            viewModel.tapOnChangeFavorite(it)
        }
    }

    private fun setupObservers() {
        viewModel.allContactsLiveData.observe(viewLifecycleOwner) {
            adapter.contacts = it
        }
    }

    companion object {
        private const val TAB_NAME = "Todos"
        fun newInstance(): AllContactsFragment {
            val args = Bundle()
            val fragment = AllContactsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
