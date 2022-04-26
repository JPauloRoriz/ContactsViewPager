package br.com.jpstudent.contactsviewpager.usecase

import br.com.jpstudent.contactsviewpager.domain.entities.Contact
import br.com.jpstudent.contactsviewpager.model.repository.ContactsRepository

class UpdateContactUseCase(
    private val repository: ContactsRepository
) {
    fun updateContact(contact: Contact) {
        repository.updateContact(contact)
    }
}