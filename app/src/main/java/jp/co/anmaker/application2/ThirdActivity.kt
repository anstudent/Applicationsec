package jp.co.anmaker.application2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_third.*
import java.io.*
import kotlin.math.round

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val spinget = intent.getIntExtra("SPIN",0)
        val spinget2 = intent.getIntExtra("SPIN2",0)

        //val spinget3 = intent.getIntExtra("SPINS",0)
        //var spinner = findViewById<View>(R.id.spinner) as Spinner
        spinner3.setSelection(spinget)
        button4.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("SPINS",spinget)
            intent.putExtra("SPIN2S",spinget2)
            startActivity(intent)
        }
    }


    fun loadCount(view: View){
        var text: String? = ""
        var winnum: Int = 0
        var losenum: Int =0
        var percent: Double = 0.0
        var percent2:Int = 0
        var spinner3 = findViewById<View>(R.id.spinner3) as Spinner
        var item = spinner3.selectedItem as String


        try {
            openFileInput("winlose.txt").use { fileInputStream ->
                BufferedReader(
                        InputStreamReader(fileInputStream, "UTF-8") as Reader?).use { reader ->

                    var line: String?

                    do {
                        line = reader.readLine()
                        if(line != null) {
                            text += line + "\n"

                            if(line.startsWith(" Win   $item")) {
                                winnum++
                            }
                            else if(line.startsWith(" Lose $item") ) {
                                losenum++
                            }
                        }
                        if (line == null)
                            break

                    } while (true)



                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if(text == "")
            text = "NO DATA"

        percent = winnum.toDouble()/(winnum+losenum).toDouble()
        percent2 = round(percent*100).toInt()

        textViewPer.text = "Win "+"$winnum"+" Lose " + "$losenum" + "  $percent2" +"%"

    }
}
