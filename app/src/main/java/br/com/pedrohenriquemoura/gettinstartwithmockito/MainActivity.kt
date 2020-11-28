package br.com.pedrohenriquemoura.gettinstartwithmockito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.pedrohenriquemoura.gettinstartwithmockito.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val mainViewModel: MainActivityViewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewModel = mainViewModel
        }

        getError()
    }

    private fun getError() {
        mainViewModel.getError().observe(this, Observer { value ->
            if(value != null) {
                Toast.makeText(application, value.toString(), Toast.LENGTH_LONG).show();
            }
        })
    }
}