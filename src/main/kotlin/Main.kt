/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013
 */
import java.lang.Exception

var TEC: MutableList<DF>? = null
var TS: MutableList<DF>? = null
var pathTEC: String? = null
var pathTS: String? = null
var simulacaoFeita = false


fun main() {
    var op = 0
    do {
        try {
            op = menu()
        } catch (e: Exception) {
            println("Houve um erro...\nMotivo:${e.message.toString()}\nVerifique e tente novamente")
        }
    } while (op != 0)
}

fun menu(): Int {

    var option = 0
    showOptions()
    option = readLine()!!.toIntOrNull()!!
    if (option != null) {
        when (option) {
            1 -> {
                println("Digite o caminho absoluto do arquivo.csv com os dados para TEC: ")
                pathTEC = readLine().toString()
                if (pathTEC != null || pathTEC!!.isEmpty()) {
                    var linha = lerEntrada(pathTEC!!)
                    var dados = converteStringParaLista(linha)
                    TEC = criaTabelaDF(dados)
                } else {
                    println("Caminho indicado inválido, verifique e tente novamente...\n")
                }
                simulacaoFeita = false
            }
            2 -> {
                println("Digite o caminho absoluto do arquivo.csv com os dados para TS: ")
                pathTS = readLine()
                if (pathTS != null || pathTS!!.isEmpty()) {
                    var linha = lerEntrada(pathTS!!)
                    var dados = converteStringParaLista(linha)
                    TS = criaTabelaDF(dados)
                } else {
                    println("Caminho indicado inválido, verifique e tente novamente...\n")
                }
                simulacaoFeita = false
            }
            3 -> {

            }
            4 -> {

            }
            5 -> {
                if(simulacaoFeita){
                    tabelaSimulacao.mostrarSimulacao()
                }else{
                    println("A simulação ainda não foi realizada, sendo assim não há o que mostrar..." +
                            "\nA ordem de execução deste programa é o uso das opções:\n " +
                            "1 -> 2 -> (3 ou 4)\n " +
                            "Depois do uso dessas opções nesta sequência haverão dados para serem mostrados...\n")
                }
            }
            6 -> {
                if(simulacaoFeita){
                    calcularImprimirEstatisticas()
                }else{
                    println("A simulação ainda não foi realizada, sendo assim não há o que mostrar..." +
                            "\nA ordem de execução deste programa é o uso das opções:\n " +
                            "1 -> 2 -> (3 ou 4)\n " +
                            "Depois do uso dessas opções nesta sequência haverão dados para serem mostrados...\n")
                }
            }
            7 -> {
                if (TEC != null) {
                    TEC!!.imprimeTabela()
                } else {
                    println("É preciso ter passado pela opção 1 antes...\n")
                }
            }
            8 -> {
                if (TS != null) {
                    TS!!.imprimeTabela()
                } else {
                    println("É preciso ter passado pela opção 2 antes...\n")
                }
            }
            0 -> {
                return 0
            }
            else -> {
                println("Opção inválida... Tente novamente\n")
            }
        }
    }
    return option
}

fun showOptions() {
    println("Modelo de fila M/M/1")
    println("1 - Inserir base de dados TEC")
    println("2 - Inserir base de dados TS")
    println("3 - Simular sistema (fila infinita)")
    println("4 - Simular sistema (fila limitada)")
    println("5 - Imprimir simulação do sistema")
    println("6 - Ver relatório estatístico")
    println("7 - Ver tabela TEC (Tempo entre chegadas)")
    println("8 - Ver tabela TS (Tempo serviço)")
    println("0 - Sair\n")
    print("Digite a opção desejada: ")


}
