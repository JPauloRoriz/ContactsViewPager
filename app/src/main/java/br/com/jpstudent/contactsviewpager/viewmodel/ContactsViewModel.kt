package br.com.jpstudent.contactsviewpager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jpstudent.contactsviewpager.domain.entities.Contact
import br.com.jpstudent.contactsviewpager.usecase.GetAllContactsUseCase
import br.com.jpstudent.contactsviewpager.usecase.GetFavoriteContactsUseCase
import br.com.jpstudent.contactsviewpager.usecase.UpdateContactUseCase

class ContactsViewModel(
    private val updateContactUseCase: UpdateContactUseCase,
    private val useCaseGetAllContacts: GetAllContactsUseCase,
    private val getFavoriteContactsUseCase: GetFavoriteContactsUseCase,

    ) : ViewModel() {
    val allContactsLiveData = MutableLiveData<List<Contact>>()
    val favoritesLiveData = MutableLiveData<List<Contact>>()

    fun getAll() {
        val allContacts = useCaseGetAllContacts.getAllContacts()
        allContactsLiveData.value = allContacts
    }


    fun getFavorites() {
        val favorites = getFavoriteContactsUseCase.getFavoriteContacts()
        favoritesLiveData.value = favorites
    }


    fun tapOnChangeFavorite(contact: Contact) {
        contact.isFavorite = !contact.isFavorite
        updateContactUseCase.updateContact(contact)
        getFavorites()
        getAll()
    }
}