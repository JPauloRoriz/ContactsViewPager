package br.com.jpstudent.contactsviewpager.iu.component

import android.text.TextWatcher
import android.text.Editable
import br.com.jpstudent.contactsviewpager.iu.component.MaskWatcher

class MaskWatcher(private val mask: String) : TextWatcher {
    private var isRunning = false
    private var isDeleting = false
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true
        val editableLength = editable.length
        if (editableLength < mask.length) {
            if (mask[editableLength] != '#') {
                editable.append(mask[editableLength])
            } else if (mask[editableLength - 1] != '#') {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }
        isRunning = false
    }

    companion object {
        fun buildCpf() = MaskWatcher("###.###.###-##")
        fun buildPhoneNumber() = MaskWatcher("(##) # ####-####")
    }
}