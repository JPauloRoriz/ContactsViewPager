package br.com.jpstudent.contactsviewpager.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jpstudent.contactsviewpager.R
import br.com.jpstudent.contactsviewpager.domain.entities.TabsTypeContacts
import br.com.jpstudent.contactsviewpager.usecase.SaveContactUseCase
import br.com.jpstudent.contactsviewpager.usecase.exception.DataEmptyException
import br.com.jpstudent.contactsviewpager.usecase.exception.FormatNumberInvalidException
import br.com.jpstudent.contactsviewpager.usecase.exception.NameLenghtException

class MenuContactsViewModel(
    private val context : Context,
    private val useCaseSaveContact: SaveContactUseCase,
) : ViewModel() {



    private val tabSelectedLiveData = MutableLiveData<TabsTypeContacts>(TabsTypeContacts.TabAll)
    val oppenBottomSheetLiveData = MutableLiveData<Unit?>()
    val messageAddContactLiveData = MutableLiveData<String>()


    fun selectTab(position: Int) {
            tabSelectedLiveData.value = TabsTypeContacts.getTabByPosition(position)

    }

    fun taponAddContact() {
        oppenBottomSheetLiveData.value = null
    }

        fun tapOnSave(name: String, number: String) {
            try {
                useCaseSaveContact.saveContact(name, number)
                messageAddContactLiveData.value = context.getString(R.string.contact_save)
                refresh()

            } catch (exception: DataEmptyException) {
                messageAddContactLiveData.value = context.getString(R.string.data_isempty)
            } catch (exception: NameLenghtException) {
                messageAddContactLiveData.value = context.getString(R.string.lengt_name)
            } catch (exception: FormatNumberInvalidException) {
                messageAddContactLiveData.value = context.getString(R.string.format_invalid)
            }
    }

    private fun refresh(){
        tabSelectedLiveData.value = tabSelectedLiveData.value
    }


}