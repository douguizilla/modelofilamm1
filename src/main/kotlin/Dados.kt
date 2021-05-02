import java.io.File
import java.lang.Exception

/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013
 */

fun converteStringParaLista(dados: String?): MutableList<Double> {
    if (dados == null || dados.isEmpty() || dados.equals(" ")){
        throw Exception("(O arquivo está vazio)")
    }else{
        val l = splitLineBySeparator(dados,",")
        val lista: MutableList<Double> = mutableListOf()
        l.forEach { dado ->
            lista.add(dado.toDouble())
        }
        lista.sort()
        return lista
    }
}

fun lerEntrada(path: String): String? {
    try {
        return File(path).bufferedReader().readLine()
    }catch (e: Exception){
        throw Exception(e.message.toString())
    }
}

fun splitLineBySeparator(line: String, separator: String): MutableList<String> {
    val list: List<String> = line.split(separator).map {
        it.trim()
    }
    return list.toMutableList()
}



