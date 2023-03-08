package id.ac.ubaya.informatika.adv160420015week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    var score = 0
    var failed = 0
    var  calculate = 0

    fun proccesRandom(
    ) {
        var randomNumber1 = Random.nextInt(1,99)
        var randomNumber2 = Random.nextInt(1,99)
        var randomOperation = Random.nextInt(0,3)
        val operation = arrayOf('+','-','*','/')

        txtNumber1.text = randomNumber1.toString()
        txtNumber2.text = randomNumber2.toString()
        txtOperator.text = operation[randomOperation].toString()

        if(randomOperation == 0){
            calculate = randomNumber1  + randomNumber2
        }else if(randomOperation == 1){
            calculate = randomNumber1  - randomNumber2
        }else if(randomOperation == 2){
            calculate = randomNumber1  * randomNumber2
        }else if(randomOperation == 3){
            calculate = randomNumber1  / randomNumber2
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            textTurn2.text = "$playerName's Turn"
        }

        proccesRandom()
        btnSubmitAnswer.setOnClickListener{
            val inputAnswer = editInputAnswer.text.toString()
            if(inputAnswer == calculate.toString()){
                score += 10
            }else {
                failed++
                Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }

            if(failed == 3){
                val action = GameFragmentDirections.actionResult(score.toString())
                Navigation.findNavController(it).navigate(action)
            }else{
                proccesRandom()
                editInputAnswer.setText("")
            }
        }
    }
}