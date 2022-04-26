package br.com.jpstudent.contactsviewpager.usecase.exception

class DataEmptyException(val errorMessage : String) : Exception(errorMessage)
class NameLenghtException(val errorMessage : String) : Exception(errorMessage)
class FormatNumberInvalidException(val errorMessage : String) : Exception(errorMessage)