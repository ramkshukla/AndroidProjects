package com.example.androidapplication

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var buttonStart: Button
    lateinit var buttonStop: Button
    lateinit var buttonPlayLastRecordAudio: Button
    lateinit var buttonStopPlayingRecording: Button
    var audioSavePathInDevice: String? = null
    lateinit var mediaRecorder: MediaRecorder
    lateinit var random: Random
    private var randomAudioFileName: String = "ABCDEFGHIJKLMNOP"
    private val requestPermissionCode: Int = 1
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart = findViewById(R.id.button)
        buttonStop = findViewById(R.id.button2)
        buttonPlayLastRecordAudio = findViewById(R.id.button3)
        buttonStopPlayingRecording = findViewById(R.id.button4)

        buttonStop.isEnabled = false
        buttonPlayLastRecordAudio.isEnabled = false
        buttonStopPlayingRecording.isEnabled = false
        random = Random()

        buttonStart.setOnClickListener {
            if (checkPermission()) {
                audioSavePathInDevice =
                    Environment.getExternalStorageDirectory().absolutePath + "/" + createRandomAudioFileName(
                        5
                    ) + "AudioRecording.3gp";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mediaRecorderReady()
                }
                try {
                    mediaRecorder.prepare()
                    mediaRecorder.start()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                buttonStart.isEnabled = false
                buttonStop.isEnabled = true
                Toast.makeText(applicationContext, "Recording Started", Toast.LENGTH_SHORT)
                    .show()
            } else {
                requestPermission()
            }
        }

        buttonStop.setOnClickListener { //                mediaRecorder.stop()
            buttonStop.isEnabled = false
            buttonPlayLastRecordAudio.isEnabled = true
            buttonStart.isEnabled = true
            buttonStopPlayingRecording.isEnabled = false

            Toast.makeText(applicationContext, "Recording Completed", Toast.LENGTH_SHORT).show()
        }

        buttonPlayLastRecordAudio.setOnClickListener {
            buttonStop.isEnabled = false
            buttonStart.isEnabled = false
            buttonStopPlayingRecording.isEnabled = true

            mediaPlayer = MediaPlayer()
            try {
                mediaPlayer.setDataSource(audioSavePathInDevice)
                mediaPlayer.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mediaPlayer.start()
            Toast.makeText(applicationContext, "Recording Playing", Toast.LENGTH_SHORT).show()
        }

        buttonStopPlayingRecording.setOnClickListener {
            buttonStop.isEnabled = false
            buttonStart.isEnabled = true
            buttonStopPlayingRecording.isEnabled = false
            buttonPlayLastRecordAudio.isEnabled = true

            if (mediaPlayer != null) {
                mediaPlayer.stop()
                mediaPlayer.release()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mediaRecorderReady()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mediaRecorderReady() {
        mediaRecorder = MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        mediaRecorder.setOutputFile(audioSavePathInDevice)

    }

    fun createRandomAudioFileName(string: Int): String {
        var stringBuilder: StringBuilder = StringBuilder(string)
        var i: Int = 0
        while (i < string) {
            stringBuilder.append(randomAudioFileName.get(random.nextInt(randomAudioFileName.length)))
            i++
        }
        return stringBuilder.toString()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(WRITE_EXTERNAL_STORAGE, RECORD_AUDIO),
            requestPermissionCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            requestPermissionCode -> {
                if(grantResults.isNotEmpty()){
                    var storagePermission: Boolean = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    var recordPermission:Boolean = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if(storagePermission && recordPermission){
                        Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun checkPermission():Boolean{
        var result = ContextCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE)
        var result1 = ContextCompat.checkSelfPermission(applicationContext, RECORD_AUDIO)
        return result == PackageManager.PERMISSION_DENIED && result1 == PackageManager.PERMISSION_GRANTED
    }
}