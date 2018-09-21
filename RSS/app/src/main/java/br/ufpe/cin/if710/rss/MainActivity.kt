package br.ufpe.cin.if710.rss

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.itemlista.view.*
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import android.support.v7.widget.DividerItemDecoration



//import android.webkit.WebView
//import android.webkit.WebViewClient



class MainActivity : Activity() {

   // private val RSS_FEED = "http://leopoldomt.com/if1001/g1brasil.xml"

   // var feedRSS: List<ItemRSS> = emptyList()


    // private var conteudoRSS: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // this.conteudoRSS = findViewById(R.id.conteudoRSS)

       // conteudoRSS!!.text = "bbbb"

        conteudoRSS.layoutManager = LinearLayoutManager(this)

       // Log.d("TESTE-RSSFEED", getString(R.string.rssfeed))

    }

    fun teste(view: View){

    }



    override fun onStart() {
        super.onStart()

        getRssFeed(getString(R.string.rssfeed))


      /*  try {
            //doAsync para o app não bugar ao carregar o RSS_FEED




            doAsync {

                //Passando o link para o getRssFeed
               // val feedRSS: List<ItemRSS> = ParserRSS.parse(getRssFeed(getString(R.string.rssfeed)))

                //  val XML =

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
        }*/

    }



    //Solução para a tradução da fun getRssFeed para Kotlin
   /* private fun getRssFeed(feed: String): String {
        return URL(feed).readText()
    }*/

    //@Throws(IOException::class)
    private fun getRssFeed(feed: String) {

        //doAsync para o app não bugar ao carregar o RSS_FEED

        doAsync {



            var `in`: InputStream? = null
            var rssFeed = ""
            try {
                val url = URL(feed)
                val conn = url.openConnection() as HttpURLConnection
                `in` = conn.inputStream
                val out = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var count: Int = `in`!!.read(buffer)
//                println("print" + count)
                while (count != -1) {
                    out.write(buffer, 0, count)
                    count = `in`!!.read(buffer)
                }
                val response = out.toByteArray()
                rssFeed = String(response)

                uiThread {
                    //Log.i("MSG", "AAAAAA")


                    //recebendo o link e passando para o Parse e atribuindo ao feed
                    val feed: List<ItemRSS> = ParserRSS.parse(rssFeed)

                    conteudoRSS.backgroundColor = Color.rgb(130, 212, 247)
                }


            } catch (e: IOException){
                e.printStackTrace()

            } finally {
                `in`?.close()
            }
            //  return rssFeed

        }


    }


}




