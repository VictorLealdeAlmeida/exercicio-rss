package br.ufpe.cin.if710.rss

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import java.io.IOException
import java.net.URL
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.support.v7.widget.LinearLayoutManager



//import android.webkit.WebView
//import android.webkit.WebViewClient



class MainActivity : Activity() {

    private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"

    var feedRSS = ""

   // private var conteudoRSS: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // this.conteudoRSS = findViewById(R.id.conteudoRSS)

       // conteudoRSS!!.text = "bbbb"

        conteudoRSS.layoutManager = LinearLayoutManager(this)


    }

    fun teste(view: View){

    }



    override fun onStart() {
        super.onStart()

        val feedXML = getRssFeed(getString(R.string.rssfeed))

        try {
            //doAsync para o app não bugar ao carregar o RSS_FEED

            doAsync {

                    //Passando o link para o getRssFeed



                //Log.i("TESTE", "bbbbb")
                //Faz as alterações da interface depois de ter o dados
                uiThread {
                   // this.conteudoRSS.setText(feedXML)

                   // conteudoRSS!!.text = getRssFeed(getString(R.string.rssfeed))
                    //conteudoRSS!!.text = "123456789"
                    //Log.v("TESTE", "bbbbb")

                    conteudoRSS.backgroundColor = Color.rgb(130, 212, 247)

                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }



    //Solução para a tradução da fun getRssFeed para Kotlin
    private fun getRssFeed(feed: String): String {
        return URL(feed).readText()
    }


}



