package br.com.quiz.ui.resultado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R


class ResultadoFragment : Fragment() {
    var nivel =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resultado, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance(): ResultadoFragment {
            val fragment = ResultadoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }
}
