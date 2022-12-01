package chucknorris

fun isSequenceContinued(i: Int, a: String, count: Int, c: Char): Boolean {
    return i < a.lastIndex && a[i + 1] == c && count > 0
}

fun validEncodedMessages(str: String): Boolean {
    val s = str.split(' ')
    if (s.size % 2 != 0) return false
    var check = ""
    for (i in 0 until s.lastIndex step 2){
        if (s[i] == "0" || s[i] == "00") {
            check += s[i+1]
            continue
        } else {
            return false
        }
    }

    if (check.length % 7 != 0) return false

    for (i in str) {
        if (i != '0' && i != ' ') {
            return false
        }
    }

    return true
}

fun encode() {
    println("Input string:")
    val str = readln()
    var a = ""
    var s = ""
    for (i in str) a = "$a${"%07d".format(Integer.toBinaryString(i.code).toInt())}"

    var check = a[0] == '0'
    var count = 1
    for (i in a.indices) {
        check = if (check) if (isSequenceContinued(i, a, ++count, '0')) continue else false
        else if (isSequenceContinued(i, a, ++count, '1')) continue else true

        s += "${if (check) "0" else "00"} ${"%0${--count}d".format(0)} "
        count = 1
    }

    println("Encoded string:")
    println(s)
}

fun decode() {
    println("Input encoded string:")
    val str = readln()
    val s = str.split(' ')
    if (!validEncodedMessages(str)) {
        println("Encoded string is not valid.\n")
    } else {
        println("Decoded string:")
        var a = ""
        for (i in 0 until s.lastIndex step 2) {
            a = "${a}${if (s[i] == "0") "1".repeat(s[i + 1].length) else "0".repeat(s[i + 1].length)}"
        }
        a.chunked(7).map { print(it.toInt(2).toChar()) }
        println("\n")
    }
}

fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        when (val input = readln()) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> break
            else -> println("There is no '$input' operation")
        }
    }
    println("Bye!")

} 
