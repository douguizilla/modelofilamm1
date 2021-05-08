/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013

    Resumo: Esse arquivo possui as funções necessárias para tratamento dos dados de entrada, ele pegará um
            arquivo.csv com os dados em apenas na PRIMEIRA linha e transformará em uma tabela de distribuição
            de frequências.
 */
import java.io.File
import java.lang.Exception
import java.util.*
import kotlin.random.Random

//Distribuição de frequência

fun obtemTempoUsandoMMC(tabela: MutableList<DF>): Double {
    val numeroAleatorioSorteado = Random.nextDouble(0.0, 1.0)
    var valorRetorno = 0.0
    tabela.forEach { linha ->
        if (numeroAleatorioSorteado >= linha.intervaloValores!!.inicio &&
            numeroAleatorioSorteado < linha.intervaloValores!!.fim
        ) {
            valorRetorno = linha.pontoMedio
        }
    }
    return valorRetorno
}

//construção da tabela de distribuição de frequência
fun criaTabelaDF(dados: MutableList<Double>): MutableList<DF> {
    val tabela: MutableList<DF> = mutableListOf()
    defineClasses(tabela, calculaNumeroClasses(dados), calculaAmplitudeClasses(dados))
    defineFrequencias(tabela, dados)
    return tabela
}

fun defineFrequencias(
    tabela: MutableList<DF>,
    dados: MutableList<Double>
) {
    var inicio = 0.0
    var fim = 0.0
    var i = 0
    var j = 0
    val tamanho = dados.size
    var cont = tamanho
    var soma = 0.0
    while (cont-- > 0) {
        fim = tabela[i].classe!!.fim

        if (dados[j] >= fim) {
            //atualização da frequencia relativa acumulada
            tabela[i].freqRelativaAcumulada = soma + tabela[i].freqRelativa
            soma = tabela[i].freqRelativaAcumulada

            //atualização do intervalo de valores
            tabela[i].intervaloValores = Classe(inicio, tabela[i].freqRelativaAcumulada)
            inicio = tabela[i].freqRelativaAcumulada

            i++
        }

        tabela[i].freqAbsoluta = tabela[i].freqAbsoluta + 1.0
        tabela[i].freqRelativa = tabela[i].freqAbsoluta / tamanho


        j++
    }
    //atualizando a ultima linha da tabela
    tabela[i].freqRelativaAcumulada = soma + tabela[i].freqRelativa
    tabela[i].intervaloValores = Classe(inicio, tabela[i].freqRelativaAcumulada)
}

fun defineClasses(
    tabela: MutableList<DF>,
    numeroClasses: Int,
    amplitudeClasse: Int
) {
    var cont = numeroClasses
    var inicio = 0.0
    var fim = 0.0
    while (cont-- > 0) {
        fim = inicio + amplitudeClasse
        val df = DF(
            Classe(inicio, fim),
            0.0,
            0.0,
            0.0,
            calculaPontoMedio(fim, inicio),
            null
        )
        tabela.add(df) //adiciona a linha a tabela
        inicio = fim + 0.1 //incrementa o inicio para a próxima iteração
    }
}

fun calculaPontoMedio(fim: Double, inicio: Double) = (fim + inicio) / 2

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
) = dados[dados.size - 1] - dados[0]


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

//Funções de impressão na tela

fun MutableList<DF>.imprimeTabela() {
    println("Classes\t\t\tFA\t\tFR\t\tFRA\t\tPM\t\tIV")
    this.forEach {
        it.imprimeDF()
        print("\n")
    }
    println(
        "FA  - Frequência Absoluta | " +
                "FR  - Frequência Relativa | " +
                "FRA - Frequência Relativa Acumulada | " +
                "PM  - Ponto Médio | " +
                "IV  - Intervalo de Valores"
    )
}

fun DF.imprimeDF() {
    var inicio = this.classe?.inicio
    var fim = this.classe?.fim

    print(inicio!!.format() + " |- " + fim!!.format() + "\t")

    print(this.freqAbsoluta.format() + "\t")
    print(this.freqRelativa.format() + "\t")
    print(this.freqRelativaAcumulada.format() + "\t")
    print(this.pontoMedio.format() + "\t")

    inicio = this.intervaloValores?.inicio
    fim = this.intervaloValores?.fim

    print(inicio!!.format() + " |- " + fim!!.format() + "\t")
}

fun Double.format() = String.format(Locale.US, "%2.2f", this)

fun MutableList<Simulacao>.mostrarSimulacao(){
    println("EVENTO\tCLIENTE\tTR\tES\tTF\tHC\tHS")
    this.forEach {
        println("${it.evento}\t" +
                "${it.cliente}\t" +
                "${it.TR}\t" +
                "${it.ES}\t" +
                "${it.TF}\t" +
                "${it.HC}\t" +
                "${it.HS}")
    }
}

//Estruturas auxiliares

data class DF( //linha da tabela de Distribuição de Frequência
    var classe: Classe?,
    var freqAbsoluta: Double,
    var freqRelativa: Double,
    var freqRelativaAcumulada: Double,
    var pontoMedio: Double,
    var intervaloValores: Classe?
)

data class Classe(
    var inicio: Double,
    var fim: Double
)

data class Simulacao(
    val evento: String,
    val cliente: Int,
    val TR: Double,
    val ES: Int,
    val TF: Int,
    val HC: Double,
    val HS: Double
)