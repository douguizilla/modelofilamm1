import java.io.File
import java.io.FileNotFoundException
import java.lang.Exception

/*
    Trabalho 1 - Modelo de fila M/M/1
    Aluno: Douglas Gomes de Paula
    Matrícula: 11621BCC013
 */

fun lerEntrada(path: String): String? {
    try {
        return File(path).bufferedReader().readLine()
    }catch (e: Exception){
        throw Exception(e.message.toString())
    }
}


