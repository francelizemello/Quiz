package br.com.quiz.ui.nivel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.ui.questoes.QuestoesActivity
import br.com.quiz.util.NIVEL
import kotlinx.android.synthetic.main.fragment_nivel.*


class NivelFragment : Fragment() {
    var nivel =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(arguments!=null) {
            nivel = arguments!!.getString(NIVEL).toString()
            when (arguments!!.getString(NIVEL)) {
               "0"->return inflater.inflate(R.layout.fragment_nivel, container, false)
               "1"->return inflater.inflate(R.layout.layout_pergunta_two, container, false)
               "2"->return inflater.inflate(R.layout.layout_pergunta_three, container, false)
               "3"->return inflater.inflate(R.layout.layout_pergunta_four, container, false)
            }
        }
       return inflater.inflate(R.layout.fragment_nivel, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btIniciarJogo.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener {view ->
        when (view.getId()) {
            R.id.btIniciarJogo -> irJogo()
        }
    }
    fun irJogo(){
        var intent = Intent(activity, QuestoesActivity::class.java)
        intent.putExtra(NIVEL,nivel)
        startActivity(intent)
        activity?.finish()
    }
    companion object {
        fun newInstance(nivel: String): NivelFragment {
            val fragment = NivelFragment()
            val args = Bundle()
            args.putString(NIVEL,nivel)
            fragment.arguments = args
            return fragment
        }

    }
}
