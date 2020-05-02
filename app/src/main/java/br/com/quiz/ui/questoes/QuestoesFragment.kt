package br.com.quiz.ui.questoes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.source.model.Perfil
import br.com.quiz.source.model.Question
import br.com.quiz.source.model.questoes
import br.com.quiz.ui.resultado.ResultadoActivity
import br.com.quiz.util.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_questoes.*


class QuestoesFragment : Fragment(), ContratoQuestoes.View {

    var nivel = ""
    override lateinit var presenter: ContratoQuestoes.Presenter
    var gson = Gson()
    var scoreTotal = 0
    lateinit var listaQuestoes: List<Question>
    private val SPLASH_TIME_OUT: Long = 2000 // 1 sec


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_questoes, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btResponder.setOnClickListener(clickListener)
        if (arguments != null) {
            nivel = arguments!!.getString(NIVEL).toString()
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.btResponder -> responderPergunta()
        }
    }

    var position = 0
    override fun responderPergunta() {
        if (radioGroupQuestao.checkedRadioButtonId == -1)
            showDialogAviso()
        else {
            if (listaQuestoes != null) {
                for (questao in listaQuestoes) {
                    if (questao.question.equals(tvPergunta.text)) {
                        position++
                        when (radioGroupQuestao.checkedRadioButtonId) {
                            R.id.questaoA -> somarScore(questao, 0, position, R.id.questaoA)
                            R.id.questaoB -> somarScore(questao, 1, position, R.id.questaoB)
                            R.id.questaoC -> somarScore(questao, 2, position, R.id.questaoC)
                            R.id.questaoD -> somarScore(questao, 3, position, R.id.questaoD)
                        }

                    }
                }
            }

        }
    }

    private fun somarScore(question: Question, number: Int, position: Int, radio: Int) {
        val radioButton = activity!!.findViewById<AppCompatRadioButton>(radio)
        if (question.answer == number) {
            scoreTotal++
            exibirEfeitoQuestaoCerta(radioButton)
        } else {
            exibirEfeitoQuestaoErrada(radioButton)
        }
        Log.e("atualizar position " ,""+position)
        Handler().postDelayed({
            presenter.atualizarPerfil(
                Perfil(
                    1,
                    scoreTotal,
                    position,
                    if (nivel.equals("0")) scoreTotal else 0,
                    if (nivel.equals("1")) scoreTotal else 0,
                    if (nivel.equals("2")) scoreTotal else 0,
                    if (nivel.equals("3")) scoreTotal else 0
                )
            )
            desabilitaBotao()
        }, SPLASH_TIME_OUT)
    }


    override fun showDialogAviso() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Aviso")
                setMessage("Você precisa marcar uma opção para poder responder!")
                setPositiveButton(
                    "OK"
                ) { _, id ->

                }

            }

            builder.create()
        }
        alertDialog!!.show()
    }


    override fun irProximaPergunta() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun defaultColorText() {
        if (activity != null && !activity!!.isFinishing) {
            questaoA.setTextColor(
                ContextCompat.getColor(
                    activity!!.baseContext,
                    R.color.colorBlack
                )
            )
            questaoB.setTextColor(
                ContextCompat.getColor(
                    activity!!.baseContext,
                    R.color.colorBlack
                )
            )
            questaoC.setTextColor(
                ContextCompat.getColor(
                    activity!!.baseContext,
                    R.color.colorBlack
                )
            )
            questaoD.setTextColor(
                ContextCompat.getColor(
                    activity!!.baseContext,
                    R.color.colorBlack
                )
            )
        }

    }

    override fun exibirEfeitoQuestaoCerta(radioButton: AppCompatRadioButton) {
        radioButton.setTextColor(ContextCompat.getColor(activity!!.baseContext, R.color.colorGreen))
    }

    override fun exibirEfeitoQuestaoErrada(radioButton: AppCompatRadioButton) {
        radioButton.setTextColor(ContextCompat.getColor(activity!!.baseContext, R.color.colorRed))
    }

    override fun isFimJogo(): Int {
        when (arguments!!.getString(NIVEL)) {
            "0" -> return 5
            "1" -> return 10
            "2" -> return 15
            "3" -> return 20
        }
        return 0
    }

    override fun irTelaResultado() {

        var intent = Intent(activity, ResultadoActivity::class.java)
        intent.putExtra(NIVEL, arguments!!.getString(NIVEL))
        intent.putExtra(PONTOS, scoreTotal.toString())
        startActivity(intent)
        activity?.finish()
    }

    override fun carregarQuestao(indice: Int) {
        position = indice
        if (activity != null && !activity!!.isFinishing) {
            var questionSet = FileParser().lerArquivo(activity?.assets!!.open("Question.json"))
            listaQuestoes = gson.fromJson(questionSet, questoes::class.java).questoesQuiz
            if (listaQuestoes != null) {
                if (indice < listaQuestoes.size) {
                    tvPergunta.text =  listaQuestoes[indice].question
                    questaoA.text = listaQuestoes[indice].optiona
                    questaoB.text = listaQuestoes[indice].optionb
                    questaoC.text = listaQuestoes[indice].optionc
                    questaoD.text = listaQuestoes[indice].optiond
                }
            }
        }
    }

    override fun habilitarBotao() {
        btResponder.isEnabled = true
    }

    override fun desabilitaBotao() {
        btResponder.isEnabled = false

    }

    override fun desmarcarRadio() {
        radioGroupQuestao.clearCheck()
    }

    companion object {
        fun newInstance(nivel: String): QuestoesFragment {
            val fragment = QuestoesFragment()
            val args = Bundle()
            args.putString(NIVEL, nivel)
            fragment.arguments = args
            return fragment
        }

    }
}
