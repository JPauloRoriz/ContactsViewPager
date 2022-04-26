package br.com.jpstudent.contactsviewpager.usecase

import br.com.jpstudent.contactsviewpager.domain.entities.Contact
import br.com.jpstudent.contactsviewpager.model.repository.ContactsRepository

class GetFavoriteContactsUseCase(
    private val repository : ContactsRepository
) {

    fun getFavoriteContacts() : List<Contact>{
        return repository.getFavoriteContacts()
    }
}