package com.example.contactappnextget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.contactappnextget.fragments.AddContact
import com.example.contactappnextget.fragments.ContactList

class MainActivity : AppCompatActivity(), ContactList.Callbacks, AddContact.Callbacks{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
        if (currentFragment == null ){
            val fragment = ContactList.newInstance()
            supportFragmentManager.commit{
                add(R.id.container_fragment, fragment)
            }
        }
    }


    private fun changeFragments(fragment: Fragment){
        supportFragmentManager.commit{
            setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,
                android.R.animator.fade_in, android.R.animator.fade_out)
            replace(R.id.container_fragment,fragment)
            addToBackStack(null)
        }
    }


    override fun onAddContact() {
        val fragment = AddContact.newInstance()
        supportFragmentManager.commit {
            setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            addToBackStack(null)
            replace(R.id.container_fragment, fragment)
        }
    }

    override fun onContactList() {
        val fragment = ContactList.newInstance()
        supportFragmentManager.commit {
            setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            addToBackStack(null)
            replace(R.id.container_fragment, fragment)
        }
    }

}