package br.com.jpstudent.contactsviewpager.ui.fragments

import androidx.fragment.app.Fragment

abstract class BasePageFragment : Fragment() {
    abstract fun getTabName() : String
}