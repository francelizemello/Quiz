package br.com.quiz.ui.resultado

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.util.NIVEL
import br.com.quiz.util.PONTOS
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_resultado.*


class ResultadoFragment : Fragment() {
    var valor = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resultado, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        valor = arguments!!.getString(PONTOS).toString()
        when (arguments!!.getString(NIVEL)) {
            "0" -> addValores("1")
            "1" -> addValores("2")
            "2" -> addValores("3")
            "3" -> addValores("4")
        }
    }

    private fun addValores(string: String) {
        Log.e("meu nivel ", string)
        if (!string.equals("4")) {
            if (valor.toInt() >= 5) {
                tvQuiz.text = "Parabéns!"
                tvNovoNivel.text = "Você passou para o próximo nível!"
                Glide.with(context).load(R.drawable.alegre).into(imgEmotion)

            } else {
                tvQuiz.text = "Poxa!"
                tvNovoNivel.text = "Você pode tentar novamente!"
                Glide.with(context).load(R.drawable.sentido).into(imgEmotion)
            }
            tvPontos.text = "Você fez " + valor + " pontos."
        } else {
            if (valor.toInt() >= 5) {
                tvQuiz.text = "Parabéns!"
                tvNovoNivel.text =
                    "Você superou todos os desafios sobre evolução humana e mostrou que é um verdadeiro Homo sapiens. Muito bem! Você concluiu este Quiz e conheceu que A EVOLUÇÃO NÃO PARA!"
                Glide.with(context).load(R.drawable.palminhas).into(imgEmotion)
                tvPontos.text = ""

            }else{
                tvQuiz.text = "Poxa!"
                tvNovoNivel.text = "Você pode tentar novamente!"
                Glide.with(context).load(R.drawable.sentido).into(imgEmotion)
                tvPontos.text = "Você fez " + valor + " pontos."

            }

        }
    }

    companion object {
        fun newInstance(nivel: String?, pontos: String?): ResultadoFragment {
            val fragment = ResultadoFragment()
            val args = Bundle()
            args.putString(NIVEL, nivel)
            args.putString(PONTOS, pontos)
            fragment.arguments = args
            return fragment
        }

    }
}
