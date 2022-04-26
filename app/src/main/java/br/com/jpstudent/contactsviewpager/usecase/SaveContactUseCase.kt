package br.com.jpstudent.contactsviewpager.usecase

import android.content.Context
import br.com.jpstudent.contactsviewpager.R
import br.com.jpstudent.contactsviewpager.model.repository.ContactsRepository
import br.com.jpstudent.contactsviewpager.usecase.exception.DataEmptyException
import br.com.jpstudent.contactsviewpager.usecase.exception.FormatNumberInvalidException
import br.com.jpstudent.contactsviewpager.usecase.exception.NameLenghtException

class SaveContactUseCase(
    private val repository : ContactsRepository,
    private val context : Context
) {
    fun saveContact(name: String, number: String) {
        if(name.isEmpty() || number.isEmpty()){
            throw DataEmptyException(context.getString(R.string.data_isempty))
        }
        if(name.length < 3){
            throw NameLenghtException(context.getString(R.string.lengt_name))
        }
        if(number.length != 11){
            throw FormatNumberInvalidException(context.getString(R.string.format_invalid))
        }

        repository.addContact(name, number)
    }
}