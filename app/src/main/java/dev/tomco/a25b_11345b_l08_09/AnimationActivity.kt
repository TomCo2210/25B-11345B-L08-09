package dev.tomco.a25b_11345b_l08_09

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import dev.tomco.a25b_11345b_l08_09.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startAnimation(binding.animLOTTIELottie)
    }

    private fun startAnimation(lottie: LottieAnimationView) {
        lottie.resumeAnimation()
        lottie.addAnimatorListener(
            object :Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator) {
                    // Fetch data from internet.
                }

                override fun onAnimationEnd(animation: Animator) {
                    transactToMainActivity()
                }

                override fun onAnimationCancel(animation: Animator) {
                    // cleanUp
                }

                override fun onAnimationRepeat(animation: Animator) {
                    // check if data is fully received
                    // if true: cancel repeats
                }

            }
        )
    }

    private fun transactToMainActivity() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}