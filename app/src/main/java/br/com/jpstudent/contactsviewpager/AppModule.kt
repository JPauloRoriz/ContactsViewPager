package br.com.jpstudent.contactsviewpager

import androidx.room.Room
import br.com.jpstudent.contactsviewpager.model.database.AppDatabase
import br.com.jpstudent.contactsviewpager.model.repository.ContactsRepository
import br.com.jpstudent.contactsviewpager.usecase.GetAllContactsUseCase
import br.com.jpstudent.contactsviewpager.usecase.GetFavoriteContactsUseCase
import br.com.jpstudent.contactsviewpager.usecase.SaveContactUseCase
import br.com.jpstudent.contactsviewpager.usecase.UpdateContactUseCase
import br.com.jpstudent.contactsviewpager.viewmodel.ContactsViewModel
import br.com.jpstudent.contactsviewpager.viewmodel.MenuContactsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
//database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database"
        ).allowMainThreadQueries().build()
    }

    //Dao
    factory { get<AppDatabase>().contactDao() }

    //repository
    factory { ContactsRepository(get()) }

    //usecase
    single { GetAllContactsUseCase(get()) }
    single { SaveContactUseCase(get(), get()) }
    single { GetFavoriteContactsUseCase(get()) }
    single { UpdateContactUseCase(get()) }


    //viewmodel
    viewModel { MenuContactsViewModel(get(), get()) }
    viewModel { ContactsViewModel(get(), get(), get()) }


}