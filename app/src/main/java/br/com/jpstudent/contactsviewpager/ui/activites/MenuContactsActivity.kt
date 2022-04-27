package br.com.jpstudent.contactsviewpager.ui.activites

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.jpstudent.contactsviewpager.databinding.ActivityMenuContactsBinding
import br.com.jpstudent.contactsviewpager.ui.adapter.ViewPagerAdapter
import br.com.jpstudent.contactsviewpager.ui.bottomsheet.AddContactBottomSheet
import br.com.jpstudent.contactsviewpager.ui.fragments.AllContactsFragment
import br.com.jpstudent.contactsviewpager.ui.fragments.FavoritesContactsFragment
import br.com.jpstudent.contactsviewpager.viewmodel.MenuContactsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuContactsActivity : AppCompatActivity() {
    private val listFragments by lazy {
        listOf(
            AllContactsFragment.newInstance(),
            FavoritesContactsFragment.newInstance()
        )
    }
    private val bottomSheet by lazy { AddContactBottomSheet() }
    private lateinit var binding: ActivityMenuContactsBinding
    private val viewModel: MenuContactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configViewPager()
        setupListners()
        setupObeservers()

    }

    private fun configViewPager() {
        refreshViewPager()
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = listFragments[position].getTabName()
        }.attach()
    }


    private fun setupListners() {
        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewModel.selectTab(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.btnOppenBottomSheet.setOnClickListener {
            viewModel.taponAddContact()
        }

        bottomSheet.clickSave = { name, number ->
            viewModel.tapOnSave(name, number)
            refreshViewPager()
        }
    }

    private fun setupObeservers() {
        viewModel.oppenBottomSheetLiveData.observe(this) {
            bottomSheet.show(supportFragmentManager, null)
        }

        viewModel.messageAddContactLiveData.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }
    }

    private fun refreshViewPager(){
        binding.viewpager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle ,listFragments)
    }
}