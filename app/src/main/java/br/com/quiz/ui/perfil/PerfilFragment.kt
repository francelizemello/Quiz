package br.com.quiz.ui.sobre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.source.model.Perfil
import br.com.quiz.source.model.Usuario
import br.com.quiz.ui.perfil.ContratoPerfil
import br.com.quiz.util.*
import kotlinx.android.synthetic.main.fragment_perfil.*


class PerfilFragment : Fragment(), ContratoPerfil.View {

    override lateinit var presenter: ContratoPerfil.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        presenter.getPerfil()
    }

    override fun exibirDadosUsuario(usuario: Usuario) {
        title.text=usuario.nome
        title.text="Oi "+usuario.nome + " , segue teu Score!"
    }

    override fun exibirDadosPerfil(perfil: Perfil) {
        ptNivel1.text=perfil.scoreNivel1.toString()
        ptNivel2.text=perfil.scoreNivel2.toString()
        ptNivel3.text=perfil.scoreNivel3.toString()
        ptNivel4.text=perfil.scoreNivel4.toString()
        var total = perfil.scoreNivel1.toInt()+perfil.scoreNivel2.toInt()+perfil.scoreNivel3.toInt()+perfil.scoreNivel4.toInt()
        ptTotal.text= total.toString()
    }

    companion object {
        fun newInstance(): PerfilFragment {
            val fragment = PerfilFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }
}
