package br.com.quiz.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.source.model.Perfil
import br.com.quiz.source.model.Question
import br.com.quiz.source.model.questoes
import br.com.quiz.ui.questoes.ContratoQuestoes
import br.com.quiz.ui.questoes.PresenterQuestoes
import br.com.quiz.ui.questoes.QuestoesActivity
import br.com.quiz.ui.resultado.ResultadoActivity
import br.com.quiz.util.FileParser
import br.com.quiz.util.NIVEL
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_questoes.*


class QuestoesFragment : Fragment(), ContratoQuestoes.View {

    var nivel =""
    override lateinit var presenter: ContratoQuestoes.Presenter
    var gson = Gson()
    var scoreTotal = 0
    lateinit var listaQuestoes: List<Question>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_questoes, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btResponder.setOnClickListener(clickListener)
        if(arguments!=null) {
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

    var position=0
    override fun responderPergunta() {
        if (radioGroupQuestao.checkedRadioButtonId == -1)
            showDialogAviso()
        else {
            if(listaQuestoes!=null){
                    for (questao in listaQuestoes){
                    if(questao.question.equals(tvPergunta.text.toString())){
                        position++
                        when(radioGroupQuestao.checkedRadioButtonId){
                            R.id.questaoA -> somarScore(questao,0,position)
                            R.id.questaoB -> somarScore(questao,1,position)
                            R.id.questaoC -> somarScore(questao,2,position)
                            R.id.questaoD -> somarScore(questao,3,position)
                        }

                    }
                }
            }

        }
    }

    private fun somarScore(question: Question,number: Int,position: Int){
        if(question.answer==number){
            scoreTotal++
        }
        presenter.salvarPerfil(Perfil(0,scoreTotal,position,if (nivel.equals("0")) scoreTotal else 0,if (nivel.equals("1")) scoreTotal else 0,if (nivel.equals("2")) scoreTotal else 0,if (nivel.equals("3")) scoreTotal else 0))
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


    override fun exibirEfeitoQuestaoCerta() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exibirEfeitoQuestaoErrada() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isFimJogo(): Int {
        when (arguments!!.getString(NIVEL)) {
            "0"->return 5
            "1"->return 10
            "2"->return 15
            "3"->return 20
        }
        return  0
    }

    override  fun irTelaResultado(){
        var intent = Intent(activity, ResultadoActivity::class.java)
        intent.putExtra(NIVEL,nivel)
        startActivity(intent)
        activity?.finish()
    }

    override fun carregarQuestao(indice: Int) {
        var questionSet = FileParser().lerArquivo(activity?.assets!!.open("Question.json"))
        listaQuestoes = gson.fromJson(questionSet, questoes::class.java).questoesQuiz
        if (listaQuestoes != null) {
            if(indice <listaQuestoes.size) {
                tvPergunta.text = listaQuestoes[indice].question
                questaoA.text = listaQuestoes[indice].optiona
                questaoB.text = listaQuestoes[indice].optionb
                questaoC.text = listaQuestoes[indice].optionc
                questaoD.text = listaQuestoes[indice].optiond
            }
        }
    }

    companion object {
        fun newInstance(nivel: String): QuestoesFragment {
            val fragment = QuestoesFragment()
            val args = Bundle()
            args.putString(NIVEL,nivel)
            fragment.arguments = args
            return fragment
        }

    }
}
