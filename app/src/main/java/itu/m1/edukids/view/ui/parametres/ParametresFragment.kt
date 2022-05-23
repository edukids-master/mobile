package itu.m1.edukids.view.ui.parametres

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import itu.m1.edukids.R
import itu.m1.edukids.controller.UserController
import itu.m1.edukids.databinding.FragmentNotificationsBinding
import itu.m1.edukids.view.GameListActivity
import itu.m1.edukids.view.LoginActivity
import itu.m1.edukids.view.ProfilActivity

class ParametresFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ParametresViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var profilCard : CardView = binding.paramProfilCard
        profilCard.setOnClickListener{
            val intent = Intent(context, ProfilActivity::class.java)
            startActivity(intent)
        }

        var deconnexion : CardView = binding.paramLogoutCard
        deconnexion.setOnClickListener{
            UserController.localAccess.deconnexion()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}