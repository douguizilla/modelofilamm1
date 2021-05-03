/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013

    Resumo: Esse arquivo possui as funções necessárias para tratamento dos dados de entrada, ele pegará um
            arquivo.csv com os dados em apenas na PRIMEIRA linha e transformará em uma tabela de distribuição
            de frequências.
 */

// Variáveis do Modelo de Simulação:

var cliente = 1
var TR = 0.0 // Tempo de Simulação (minutos)
var ES = 0 // Servidor está ocioso (0) ou ocupado (1)
var TF = 0 // Tamanho da fila
var HC = 0.0 // Tempo agendado para a próxima chegada (minutos)
var HS = Double.MAX_VALUE // Tempo agendado para a próxima saída (minutos)

// Algoritmo para processar um evento de saída

fun processarEventoSaida(
    tabelaTEC: MutableList<DF>,
    tabelaTS: MutableList<DF>
) {
    //Atualizar relógio para o horário da próxima saída
    TR = HS

    if(TF > 0){ //Se o tamanho da fila é maior que zero
        //Atualizar o tamanho da fila
        TF -= 1

        //Gerar o tempo de serviço
        var TS = obtemTempoUsandoMMC(tabelaTS)

        //Agendar a próxima saída
        HS = TR + TS
    }else{
        //Atualizar o estado do servidor
        ES = 0

        //Agendar uma saída fictícia
        HS = Double.MAX_VALUE
    }
}

// Algoritmo para processar um evento de chegada

fun processarEventoChegada(
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

