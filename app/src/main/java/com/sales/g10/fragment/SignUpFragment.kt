package com.sales.g10.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sales.g10.R
import com.sales.g10.db.GtenDatabase
import com.sales.g10.db.LoginDetails
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class SignUpFragment : Fragment() {

    //private lateinit var loginViewModel: LoginViewModel
    private lateinit var onFragmentListener: OnListener

    private lateinit var db:GtenDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        db=GtenDatabase.getDatabase(context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener {
            val userName= etUsername.text.toString().trim()
            val password= etPassword.text.toString().trim()

            if (userName.isNotEmpty() && password.isNotEmpty())
            {
                val loginDetails = LoginDetails()
                loginDetails.gUserName= userName
                loginDetails.gPassword= password
                saveUserToDb(loginDetails)
            }
        }
    }

    private fun saveUserToDb(loginDetails: LoginDetails)
    {
        doAsync {

           db.loginDetailsDao().insertLoginDetail(loginDetails)

            uiThread {
                Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show()
                onFragmentListener.onBack()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentListener= activity as OnListener
    }

    interface OnListener{
        fun onBack()
    }

}
