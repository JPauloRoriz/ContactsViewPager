package br.com.jpstudent.contactsviewpager.usecase

import br.com.jpstudent.contactsviewpager.domain.entities.Contact
import br.com.jpstudent.contactsviewpager.model.repository.ContactsRepository

class GetAllContactsUseCase(
    private val repository : ContactsRepository
) {

    fun getAllContacts() : List<Contact>{
        return repository.getAllContact()
    }
}