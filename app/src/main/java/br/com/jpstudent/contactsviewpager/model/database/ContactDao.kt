package br.com.jpstudent.contactsviewpager.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.jpstudent.contactsviewpager.model.entitymodel.ContactModel

@Dao
interface ContactDao {
    @Insert
    fun addContact(contactModel : ContactModel)

    @Update
    fun updateContact(contactModel : ContactModel)

    @Query("select * from ContactModel")
    fun getAllContacts():List<ContactModel>

    @Query("select * from ContactModel where isFavorite = (:isFavorite)")
    fun getFavoriteContacts(isFavorite: Boolean = true):List<ContactModel>
}