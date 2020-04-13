package br.com.quiz.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.ui.home.HomeActivity
import br.com.quiz.ui.questoes.QuestoesActivity
import kotlinx.android.synthetic.main.fragment_sobre.*


class SobreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sobre, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btComecar.setOnClickListener(clickListener)

    }

    val clickListener = View.OnClickListener {view ->
        when (view.getId()) {
            R.id.btComecar -> irJogo()
        }
    }
    fun irJogo(){
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }
    companion object {
        fun newInstance(): SobreFragment {
            val fragment = SobreFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }
}
