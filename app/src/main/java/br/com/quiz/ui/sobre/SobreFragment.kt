package br.com.quiz.ui.sobre

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.source.model.Usuario
import br.com.quiz.ui.home.HomeActivity
import br.com.quiz.util.*
import kotlinx.android.synthetic.main.fragment_sobre.*


class SobreFragment : Fragment(), ContratoSobre.View {

    override lateinit var presenter: ContratoSobre.Presenter

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

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.btComecar -> irJogo()
        }
    }

    fun irJogo() {
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    override fun exibirDialog() {
            val dialog = Dialog(activity!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setTitle("Informe seu nome, antes de começar!")
            dialog.setContentView(R.layout.cutsom_layout)

            val yesBtn = dialog.findViewById(R.id.btConfirmar) as AppCompatButton
            val edit = dialog.findViewById(R.id.editNome) as EditText
            yesBtn.setOnClickListener {
                if (edit.text.toString().isEmpty()) {
                    exibirAviso()
                } else {
                    presenter.salvarUsuario(Usuario(edit.text.toString()))
                    dialog.dismiss()
                }
            }
            dialog.show()

    }

    private fun exibirAviso() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Aviso")
                setMessage("Você precisa preencher o campo para poder começar!")
                setPositiveButton(
                    "OK"
                ) { _, id ->

                }

            }

            builder.create()
        }
        alertDialog!!.show()
    }

    override fun isExibirDialog(): Int {
        if (PreferencesUtil.getPref(activity, NIVEL1, "0").toInt() > 0)
            return 1
        if (PreferencesUtil.getPref(activity, NIVEL2, "0").toInt() > 0)
            return 1
        if (PreferencesUtil.getPref(activity, NIVEL3, "0").toInt() > 0)
            return 1
        if (PreferencesUtil.getPref(activity, NIVEL4, "0").toInt() > 0)
            return 1
        return 0
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
