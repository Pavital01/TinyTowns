package com.example.tinytowns

//import android.content.Intent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToGame(view : View){
        val intent = Intent(this,Game::class .java)
        startActivity(intent)
        print(Resource.Brick.name)
    }
}