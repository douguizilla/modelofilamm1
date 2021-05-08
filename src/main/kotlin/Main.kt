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
                if(TEC != null && TS != null){
                    var maxCliente = 0
                    do {
                        println("Digite a quantidade MAXIMA de clientes para a simulação: (valor > 0)")
                        maxCliente = readLine()!!.toIntOrNull()!!
                    }while (maxCliente == null || maxCliente < 1)
                    processarSimulacaoPorCliente(TEC!!, TS!!,maxCliente)

                }else{
                    println("É preciso ter passado pelas opções 1 e 2 antes...\n")
                }
            }
            4 -> {
                if(TEC != null && TS != null){
                    var maxTempo = 0.0
                    do {
                        println("Digite o tempo limite em minutos para a simulação: (valor > 0)")
                        maxTempo = readLine()!!.toDoubleOrNull()!!
                    }while (maxTempo == null || maxTempo < 1)
                    processarSimulacaoPorTempo(TEC!!, TS!!,maxTempo)

                }else{
                    println("É preciso ter passado pelas opções 1 e 2 antes...\n")
                }
            }
            5 -> {
                if(TEC != null && TS != null){
                    var maxCliente = 0
                    var limiteFila = 0
                    do {
                        println("Digite a quantidade MAXIMA de clientes para a simulação: (valor > 0)")
                        maxCliente = readLine()!!.toIntOrNull()!!
                    }while (maxCliente == null || maxCliente < 1)

                    do {
                        println("Digite o limite da fila para a simulação: (valor > 0)")
                        limiteFila = readLine()!!.toIntOrNull()!!
                    }while (limiteFila == null || limiteFila < 1)
                    processarSimulacaoPorCliente(TEC!!, TS!!, maxCliente, limiteFila)

                }else{
                    println("É preciso ter passado pelas opções 1 e 2 antes...\n")
                }
            }
            6 -> {
                if(TEC != null && TS != null){
                    var maxTempo = 0.0
                    var limiteFila = 0
                    do {
                        println("Digite a quantidade MAXIMA de clientes para a simulação: (valor > 0)")
                        maxTempo = readLine()!!.toDoubleOrNull()!!
                    }while (maxTempo == null || maxTempo < 1)

                    do {
                        println("Digite o limite da fila para a simulação: (valor > 0)")
                        limiteFila = readLine()!!.toIntOrNull()!!
                    }while (limiteFila == null || limiteFila < 1)
                    processarSimulacaoPorTempo(TEC!!, TS!!, maxTempo, limiteFila)

                }else{
                    println("É preciso ter passado pelas opções 1 e 2 antes...\n")
                }
            }
            7 -> {
                if(simulacaoFeita){
                    tabelaSimulacao.mostrarSimulacao()
                }else{
                    println("A simulação ainda não foi realizada, sendo assim não há o que mostrar..." +
                            "\nA ordem de execução deste programa é o uso das opções:\n " +
                            "1 -> 2 -> (3 ou 4)\n " +
                            "Depois do uso dessas opções nesta sequência haverão dados para serem mostrados...\n")
                }
            }
            8 -> {
                if(simulacaoFeita){
                    calcularImprimirEstatisticas()
                }else{
                    println("A simulação ainda não foi realizada, sendo assim não há o que mostrar..." +
                            "\nA ordem de execução deste programa é o uso das opções:\n " +
                            "1 -> 2 -> (3 ou 4)\n " +
                            "Depois do uso dessas opções nesta sequência haverão dados para serem mostrados...\n")
                }
            }
            9 -> {
                if (TEC != null) {
                    TEC!!.imprimeTabela()
                } else {
                    println("É preciso ter passado pela opção 1 antes...\n")
                }
            }
            10 -> {
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
    println("3 - Simular sistema /parada: qtd max clientes (fila infinita)")
    println("4 - Simular sistema /parada: tempo (fila infinita)")
    println("5 - Simular sistema /parada: qtd max clientes (fila limitada)")
    println("6 - Simular sistema /parada: tempo (fila limitada)")
    println("7 - Imprimir simulação do sistema")
    println("8 - Ver relatório estatístico")
    println("9 - Ver tabela TEC (Tempo entre chegadas)")
    println("10 - Ver tabela TS (Tempo serviço)")
    println("0 - Sair\n")
    print("Digite a opção desejada: ")


}
