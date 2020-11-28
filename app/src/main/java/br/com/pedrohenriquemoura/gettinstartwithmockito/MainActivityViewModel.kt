package br.com.pedrohenriquemoura.gettinstartwithmockito

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    public val distancia: ObservableField<String> = ObservableField()
    public val preco: ObservableField<String> = ObservableField()
    public val autonomia: ObservableField<String> = ObservableField()

    val resultado: ObservableField<String> = ObservableField()

    val errorInformation: MutableLiveData<String> = MutableLiveData<String>()

    fun handleCalculateButtonClick() {
        handleCalculate()
    }

    /**
     * Função responsável por realizar o cálculo dos gastos com a viagem
     * Baseado na distância percorrida * preço médio do combustível / pela autonomia do veículo
     */

    fun handleCalculate() {
        if (isValid()) {
            try {

                // (distancia * preço) / autonomia
                val distance = distancia?.get().toString().toFloat()
                val price = preco?.get().toString().toFloat()
                val autonomy = autonomia?.get().toString().toFloat()

                // Realiza o cálculo ((distancia * preço) / autonomia)

                val result: Float = ((distance * price) / autonomy)

                // Seta o valor calculado na tela

                resultado.set("Total: R$ $result")

            } catch (nfe: NumberFormatException) {

                // Caso ocorra erro de conversão numérica, solicita ao usuário para preencher com valores válidos

                // Toast.makeText(getApplication(), "Por Favor Informe valores válidos", Toast.LENGTH_LONG).show()

                errorInformation.value = "Por Favor Informe valores válidos"
            }

        } else {

            // Caso não tenha preenchido todos os campos, solicita o preenchimento

            // Toast.makeText(getApplication(), "Por Favor Informe valores válidos", Toast.LENGTH_LONG).show()

            errorInformation.value = "Por Favor Informe valores válidos"

        }


    }

    /**
     * Verifica se todos os campos foram preenchidos
     */

    fun isValid(): Boolean {
        return distancia?.get() != ""
                && preco?.get() != ""
                && autonomia?.get() != ""
                && autonomia?.get() != "0"

    }

    fun getError(): LiveData<String> {
        return errorInformation
    }
}