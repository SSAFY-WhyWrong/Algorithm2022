import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val N = reader.readLine().toInt()

    if(N==1){
        print("B")
    }else{
        print("A")
    }




}