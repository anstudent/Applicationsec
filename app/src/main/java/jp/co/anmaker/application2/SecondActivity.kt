package jp.co.anmaker.application2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_second.*
import jp.co.anmaker.application2.R
import java.io.IOException
import java.io.BufferedReader
import java.io.InputStreamReader


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val spinget = intent.getIntExtra("SPIN",0)
        val spinget2 = intent.getIntExtra("SPIN2",0)
        readTextFile(1)
        backMain.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("SPINS",spinget)
            intent.putExtra("SPIN2S",spinget2)


            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    fun readTextFile(x: Int){
        var text: String? = ""

        try {
            openFileInput("winlose.txt").use { fileInputStream ->
                BufferedReader(
                        InputStreamReader(fileInputStream, "UTF-8")).use { reader ->

                    var line: String?

                    do {
                        line = reader.readLine()
                        if(line != null)
                            text += line + "\n"

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

        winTextView.text = "$text"

    }

}
