/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013
 */
import java.lang.Exception

fun main(){
    try {

        val path = "C:\\Users\\dougl\\Desktop\\UFU\\MS\\trabalhos\\trabalho1\\src\\main\\resources\\TEC.csv"
        val path1 = ""
        val line = lerEntrada(path)
        val dados = converteStringParaLista(line)
        val TEC = criaTabelaDF(dados)
        var valorTEC = 0.0
        for (i in 1..100){
            valorTEC = obtemTempoUsandoMMC(TEC)
            println("Valor TEC $i: " + valorTEC.toString())
        }
    }catch (e: Exception){
        println("Houve um erro...\nMotivo:${e.message.toString()}\nVerifique e tente novamente")
    }
}

fun menu(){
    showOptions()
}

fun showOptions() {
    println("Modelo de fila M/M/1")
    println("1 - Inserir base de dados TEC")
    println("2 - Inserir base de dados TS")
    println("3 - Simular sistema")
    println("4 - Imprimir simulação do sistema")
    println("5 - Ver relatório estatístico")

}
