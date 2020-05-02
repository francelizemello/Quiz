package br.com.quiz.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.source.model.Perfil
import br.com.quiz.ui.home.ContratoHome
import br.com.quiz.ui.nivel.NivelActivity
import br.com.quiz.ui.perfil.PerfilActivity
import br.com.quiz.ui.questoes.ContratoQuestoes
import br.com.quiz.util.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), ContratoHome.View {

    override lateinit var presenter: ContratoHome.Presenter
    lateinit var meuPerfil: Perfil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        card_nivel1.setOnClickListener(clickListener)
        card_nivel2.setOnClickListener(clickListener)
        card_nivel3.setOnClickListener(clickListener)
        card_nivel4.setOnClickListener(clickListener)
        btPerfil.setOnClickListener(clickListener)
        btNovoJogo.setOnClickListener(clickListener)

    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun ativarBotaoNovoJogo(perfil: Perfil) {
        meuPerfil = perfil
        Log.e("sucesso meuPerfil  ", " " + meuPerfil.perguntaAtual)

        if (perfil.scoreNivel1 >= 5 &&
            perfil.scoreNivel2 >= 5 &&
            perfil.scoreNivel3 >= 5 &&
            perfil.scoreNivel4 >= 5
        ) {
            btNovoJogo.visibility = View.VISIBLE
        } else
            btNovoJogo.visibility = View.GONE
    }

    override fun ativarCard(perfil: Perfil) {
        if (perfil.scoreNivel1 == 5)
            card_nivel1.setCardBackgroundColor(Color.parseColor("#DCDCDC"))
        if (perfil.scoreNivel2 < 5 && perfil.scoreNivel1 >= 5)
            card_nivel2.setCardBackgroundColor(Color.parseColor("#ffffff"))
        if (perfil.scoreNivel3 < 5 && perfil.scoreNivel2 >= 5)
            card_nivel3.setCardBackgroundColor(Color.parseColor("#ffffff"))
        if (perfil.scoreNivel4 < 5 && perfil.scoreNivel3 >= 5)
            card_nivel4.setCardBackgroundColor(Color.parseColor("#ffffff"))
    }

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.card_nivel1 -> verificarNivel("0")
            R.id.card_nivel2 -> verificarNivel("1")
            R.id.card_nivel3 -> verificarNivel("2")
            R.id.card_nivel4 -> verificarNivel("3")
            R.id.btPerfil -> irPerfil()
            R.id.btNovoJogo -> novoJogo()
        }
    }


    override fun novoJogo() {
        presenter.deletarPerfil()
        cardPadrao()
        btNovoJogo.visibility = View.GONE
        presenter.start()

    }

    private fun cardPadrao() {
        card_nivel1.setCardBackgroundColor(Color.parseColor("#ffffff"))
    }

    override fun verificarNivel(nivel: String) {
        when (nivel) {
            "0" -> if (meuPerfil.scoreNivel1 >= 5) return
            "1" -> if (meuPerfil.scoreNivel2 >= 5 || meuPerfil.scoreNivel1 < 5) return
            "2" -> if (meuPerfil.scoreNivel3 >= 5 || meuPerfil.scoreNivel2 < 5) return
            "3" -> if (meuPerfil.scoreNivel4 >= 5 || meuPerfil.scoreNivel3 < 5) return
        }
        irNivel(nivel)
    }

    override fun irNivel(nivel: String) {
        var intent = Intent(activity, NivelActivity::class.java)
        intent.putExtra(NIVEL, nivel)
        startActivity(intent)
        activity?.finish()
    }

    override fun irPerfil() {
        var intent = Intent(activity, PerfilActivity::class.java)
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
