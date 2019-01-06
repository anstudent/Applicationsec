package jp.co.anmaker.application2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Spinner
import jp.co.anmaker.application2.R
import jp.co.anmaker.application2.R.string.*
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinget = intent.getIntExtra("SPINS",0)
        val spinget2 = intent.getIntExtra("SPIN2S",0)

        var spinner = findViewById<View>(R.id.spinner) as Spinner
        spinner.setSelection(spinget)
        var spinner2 = findViewById<View>(R.id.spinner2) as Spinner
        spinner2.setSelection(spinget2)

        button.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            var spinnerG = findViewById<View>(R.id.spinner) as Spinner
            // 選択されているアイテムを取得
            var idxG = spinnerG.selectedItemPosition as Int

            var spinnerG2 = findViewById<View>(R.id.spinner2) as Spinner
            // 選択されているアイテムを取得
            var idxG2 = spinnerG2.selectedItemPosition as Int
            intent.putExtra("SPIN",idxG)
            intent.putExtra("SPIN2",idxG2)

            startActivity(intent)
        }

    }

    fun delete(view: View){
        AlertDialog.Builder(this).apply {
            setTitle(delete_view)
            setMessage(alart_view)
            setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                // OKの場合

                try {
                    deleteFile("winlose.txt")
                } catch ( e : IOException) {
                    e.printStackTrace();
                }
            })
            setNegativeButton("Cancel", null)
            show()
        }


    }

    fun saveFile(view: View){
        var spinner = findViewById<View>(R.id.spinner) as Spinner

        // 選択されているアイテムのIndexを取得

        // 選択されているアイテムを取得
        var item = spinner.selectedItem as String

        var saveData : String = "$item"

        try {
            // 追記書き込みでオープン (=Context.MODE_APPEND)
            val outputstream: FileOutputStream = openFileOutput("winlose.txt", Context.MODE_APPEND)
            outputstream.write(saveData.toByteArray())
            outputstream.write("\n".toByteArray())
            outputstream.close()
        } catch (e: IOException) {
            e.printStackTrace();
        }
    }



    fun winSave(view: View){
        //messageTextView.text = "prepear"

        var spinner = findViewById<View>(R.id.spinner) as Spinner
        // 選択されているアイテムを取得
        var item = spinner.selectedItem as String

        var spinner2 = findViewById<View>(R.id.spinner2) as Spinner
        // 選択されているアイテムを取得
        var item2 = spinner2.selectedItem as String


        var saveData : String = " Win   " + "$item" + " VS " + "$item2"

        AlertDialog.Builder(this).apply {
            setTitle(save_view)
            setMessage(" Win   " + "$item" + " VS " + "$item2")
            setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                // OKの場合
                try {
                    // 追記書き込み (=Context.MODE_APPEND)
                    val outputstream: FileOutputStream = openFileOutput("winlose.txt", Context.MODE_APPEND)
                    outputstream.write(saveData.toByteArray())
                    outputstream.write("\n".toByteArray())
                    outputstream.close()
                } catch (e: IOException) {
                    e.printStackTrace();
                }
            })
            setNegativeButton("Cancel", null)
            show()
        }


    }

    fun loseSave(view: View){
        //messageTextView.text = "prepear"

        var spinner = findViewById<View>(R.id.spinner) as Spinner



        // 選択されているアイテムを取得
        var item = spinner.selectedItem as String

        //var saveData : String = "$item"



        var spinner2 = findViewById<View>(R.id.spinner2) as Spinner



        // 選択されているアイテムを取得
        var item2 = spinner2.selectedItem as String

        var saveData : String = " Lose " + "$item" + " VS " + "$item2"

        AlertDialog.Builder(this).apply {
            setTitle(save_view)
            setMessage(" Lose " + "$item" + " VS " + "$item2")
            setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                // OKの場合
                try {
                    // 追記書き込み (=Context.MODE_APPEND)
                    val outputstream: FileOutputStream = openFileOutput("winlose.txt", Context.MODE_APPEND)
                    outputstream.write(saveData.toByteArray())
                    outputstream.write("\n".toByteArray())
                    outputstream.close()
                } catch (e: IOException) {
                    e.printStackTrace();
                }
            })
            setNegativeButton("Cancel", null)
            show()
        }


    }
}
