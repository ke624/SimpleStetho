package com.roogle.simple.stetho

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.roogle.stetho.mqtt.StethoMqttClient

class MainActivity : AppCompatActivity() {

    val serverUri = "tcp://iot.eclipse.org:1883"
    val clientId = "paho-30505377805850"
    lateinit var stethoMqttClient: com.roogle.stetho.mqtt.StethoMqttClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("clientId: ${clientId}")
        val simpleStetho = SimpleStetho(this)
        simpleStetho.logcatProvider.startGatherLogcatInfo()

        simpleStetho.databaseName = "youzi.db"

        stethoMqttClient = StethoMqttClient(simpleStetho, serverUri, clientId, "topic/server/${clientId}", "topic/client/${clientId}")
        stethoMqttClient.connect()
    }

    override fun onDestroy() {
        super.onDestroy()
        stethoMqttClient.disconnect()
    }
}
