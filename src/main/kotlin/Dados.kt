import java.io.File
import java.lang.Exception

/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013

    Resumo: Esse arquivo possui as funções necessárias para tratamento dos dados de entrada
 */

//Distribuição de frequência



    //funções baseadas na regra de Sturges

fun calculaAmplitudeClasses(dados: MutableList<Double>): Int {
    val ac = calculaAmplitudeTotal(dados) / calculaNumeroClasses(dados)
    val parteInteira = ac.toInt()
    val parteDecimal = ac - parteInteira

    //arredondando o valor para cima
    if (parteDecimal == 0.0) {
        return parteInteira
    } else {
        return parteInteira + 1
    }
}

fun calculaNumeroClasses(dados: MutableList<Double>): Int {
    val n = dados.size
    val log = Math.log10(n.toDouble())
    val k = 1 + 3.3 * log

    //arredondando o valor para cima
    val parteInteira = k.toInt()
    val parteDecimal = k - parteInteira

    if (parteDecimal == 0.0) {
        return parteInteira
    } else {
        return parteInteira + 1
    }
}

fun calculaAmplitudeTotal(
    dados: MutableList<Double>
) = dados[dados.size-1] - dados[0]


//Conversão dos dados lidos para uma estrutura de dados:
fun converteStringParaLista(dados: String?): MutableList<Double> {
    if (dados == null || dados.isEmpty() || dados.equals(" ")) {
        throw Exception("(O arquivo está vazio)")
    } else {
        val l = splitLineBySeparator(dados, ",")
        val lista: MutableList<Double> = mutableListOf()
        l.forEach { dado ->
            lista.add(dado.toDouble())
        }
        lista.sort()
        return lista
    }
}

fun splitLineBySeparator(line: String, separator: String): MutableList<String> {
    val list: List<String> = line.split(separator).map {
        it.trim()
    }
    return list.toMutableList()
}

//Leitura dos dados:

fun lerEntrada(path: String): String? {
    try {
        return File(path).bufferedReader().readLine()
    } catch (e: Exception) {
        throw Exception(e.message.toString())
    }
}



