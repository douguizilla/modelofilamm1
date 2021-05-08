/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013

    Resumo: Esse arquivo possui as funções necessárias para tratamento dos dados de entrada, ele pegará um
            arquivo.csv com os dados em apenas na PRIMEIRA linha e transformará em uma tabela de distribuição
            de frequências.
 */

// Variáveis do Modelo de Simulação:

var evento = ""
var cliente = 1
var TR = 0.0 // Tempo de Simulação (minutos)
var ES = 0 // Servidor está ocioso (0) ou ocupado (1)
var TF = 0 // Tamanho da fila
var HC = 0.0 // Tempo agendado para a próxima chegada (minutos)
var HS = Double.MAX_VALUE // Tempo agendado para a próxima saída (minutos)
val tabelaSimulacao: MutableList<Simulacao> = mutableListOf()

// Algoritmo de Simulação

//processamento especificado com o caso de parada

fun processarSimulacaoPorCliente(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>,
    limiteCliente: Int
){
    while (cliente < limiteCliente) {
        processarSimulacao(tabelaTEC, tabelaTS)
    }
    calcularImprimirEstatisticas()
}

fun processarSimulacaoPorCliente(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>,
    limiteCliente: Int,
    limiteFila: Int
){
    while (cliente < limiteCliente) {
        processarSimulacao(tabelaTEC, tabelaTS, limiteFila)
    }
    calcularImprimirEstatisticas()
}

fun processarSimulacaoPorTempo(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>,
    tempo: Double
){
    while (TR < tempo) {
        processarSimulacao(tabelaTEC, tabelaTS)
    }
    calcularImprimirEstatisticas()
}

fun processarSimulacaoPorTempo(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>,
    tempo: Double,
    limiteFila: Int
){
    while (TR < tempo) {
        processarSimulacao(tabelaTEC, tabelaTS, limiteFila)
    }
    calcularImprimirEstatisticas()
}

//processamento geral
private fun processarSimulacao(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>
) {
    if (HC < HS) {
        evento = "CHEGADA"
        processarEventoChegada(tabelaTEC, tabelaTS)
    } else {
        evento = "SAIDA"
        processarEventoSaida(tabelaTS)
    }
    //guardar estado atual para atualização das estatisticas
    tabelaSimulacao.add(
        Simulacao(
            evento,
            cliente,
            TR,
            ES,
            TF,
            HC,
            HS
        )
    )
}

private fun processarSimulacao(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>,
    limiteFila: Int
) {
    if (HC < HS && TF <= limiteFila) {
        evento = "CHEGADA"
        processarEventoChegada(tabelaTEC, tabelaTS)
    } else {
        evento = "SAIDA"
        processarEventoSaida(tabelaTS)
    }
    //guardar estado atual para atualização das estatisticas
    tabelaSimulacao.add(
        Simulacao(
            evento,
            cliente,
            TR,
            ES,
            TF,
            HC,
            HS
        )
    )
}

//Calculo e Impressao dos valores gerados

fun calcularImprimirEstatisticas() {
    //calculo
    val numeroMedioDeEntidadesNaFila = calculaNumeroMedioDeEntidadesNaFila()
    val taxaMediaDeUmaEntidadeNaFila = calculaTaxaMediaDeUmaEntidadeNaFila()
    val tempoMedioDeUmaEntidadeNaFila = calculaTempoMedioDeUmaEntidadeNaFila()
    val tempoMediaNoSistema = calculaTempoMedioNoSistema()

    //impressao
    println("ESTATISTICAS:")
    println("Número Médio de Entidades nas Filas: \t" + numeroMedioDeEntidadesNaFila.toString())
    println("Taxa Média de Ocupação dos Servidores: \t" + taxaMediaDeUmaEntidadeNaFila.toString())
    println("Tempo Médio de uma Entidade na Fila: \t" + tempoMedioDeUmaEntidadeNaFila.toString())
    println("Tempo Médio no Sistema: \t" + tempoMediaNoSistema.toString())
}

private fun calculaTempoMedioNoSistema(): Double {
    TODO("Not yet implemented")
}

private fun calculaTempoMedioDeUmaEntidadeNaFila(): Double {
    TODO("Not yet implemented")
}

private fun calculaTaxaMediaDeUmaEntidadeNaFila(): Double {
    TODO("Not yet implemented")
}

private fun calculaNumeroMedioDeEntidadesNaFila(): Double {
    TODO("Not yet implemented")
}


// Algoritmo para processar um evento de saída

private fun processarEventoSaida(
    tabelaTS: MutableList<DF>
) {
    //Atualizar relógio para o horário da próxima saída
    TR = HS

    if (TF > 0) { //Se o tamanho da fila é maior que zero
        //Atualizar o tamanho da fila
        TF -= 1

        //Gerar o tempo de serviço
        var TS = obtemTempoUsandoMMC(tabelaTS)

        //Agendar a próxima saída
        HS = TR + TS
    } else {
        //Atualizar o estado do servidor
        ES = 0

        //Agendar uma saída fictícia
        HS = Double.MAX_VALUE
    }
}

// Algoritmo para processar um evento de chegada

private fun processarEventoChegada(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>,
) {
    //Atualizar relógio para o horário da próxima chegada
    TR = HC

    if (ES == 0) { //Se o servidor está ocioso
        //Marcar como ocupado
        ES = 1

        //Gerar o tempo de serviço
        var TS = obtemTempoUsandoMMC(tabelaTS)

        //Agendar a próxima saída
        HS = TR + TS

    } else {
        //Atualizar o tamanho da fila
        TF += 1
    }
    //Gerar o tamanho do intervalo da próxima chegada
    var TEC = obtemTempoUsandoMMC(tabelaTEC)

    //Agendar a próxima chegada
    HC = TR + TEC
}
