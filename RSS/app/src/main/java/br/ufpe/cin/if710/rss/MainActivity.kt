package br.ufpe.cin.if710.rss

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import java.io.IOException
import java.net.URL

import org.jetbrains.anko.*

class MainActivity : Activity() {

    private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"

    private var conteudoRSS: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.conteudoRSS = findViewById(R.id.conteudoRSS)
    }

    override fun onStart() {
        super.onStart()
        try {
            //doAsync para o app não bugar ao carregar o RSS_FEED

            doAsync {
                val feedXML = getRssFeed(RSS_FEED)
                uiThread {
                   // this.conteudoRSS.setText(feedXML)
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
