package br.com.jpstudent.contactsviewpager.domain.entities

data class Contact(
    val id: Int,
    val name: String,
    val numberOfContact: String,
    var isFavorite: Boolean
)
