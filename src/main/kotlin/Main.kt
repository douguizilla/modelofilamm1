/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013
 */
import java.lang.Exception
import kotlin.random.Random

fun main(){
    try {

        val path = "C:\\Users\\dougl\\Desktop\\UFU\\MS\\trabalhos\\trabalho1\\src\\main\\resources\\TEC.csv"
        val path1 = ""
        val line = lerEntrada(path)
        val dados = converteStringParaLista(line)
        println(dados.toString())
    }catch (e: Exception){
        println("Houve um erro...\nMotivo:${e.message.toString()}\nVerifique e tente novamente")
    }
}

fun menu(){
    showOptions()
}

fun showOptions() {
    TODO("Mostrar as opções disponiveis para manipulação do sistema")
}
