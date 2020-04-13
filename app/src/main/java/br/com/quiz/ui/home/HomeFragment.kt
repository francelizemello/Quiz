package br.com.quiz.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.ui.nivel.NivelActivity
import br.com.quiz.util.NIVEL
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rdNivel1.setOnClickListener(clickListener)
        rdNivel2.setOnClickListener(clickListener)
        rdNivel3.setOnClickListener(clickListener)
        rdNivel4.setOnClickListener(clickListener)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    val clickListener = View.OnClickListener {view ->
        when (view.getId()) {
            R.id.rdNivel1 -> irNivel("0")
            R.id.rdNivel2 -> irNivel("1")
            R.id.rdNivel3 -> irNivel("2")
            R.id.rdNivel4 -> irNivel("3")
        }
    }

    fun irNivel(nivel: String){
        var intent = Intent(activity,NivelActivity::class.java)
        intent.putExtra(NIVEL,nivel)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }

    }
}
