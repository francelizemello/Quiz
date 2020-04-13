package br.com.quiz.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.quiz.R
import br.com.quiz.ui.sobre.SobreActivity


class SplashFragment : Fragment() {

    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(activity,SobreActivity::class.java))

            // close this activity
            activity?.finish()
        }, SPLASH_TIME_OUT)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    companion object {
        fun newInstance(): SplashFragment {
            val fragment = SplashFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }
}
