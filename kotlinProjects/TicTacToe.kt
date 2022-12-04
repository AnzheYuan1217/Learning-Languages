package tictactoe

fun main() {
    var runs = 0
    var endGame = false
    val a = mutableListOf(" ", " ", " ", " ", " ", " ", " ", " ", " ")

    fun printGraph(a: MutableList<String>){
        println(
            """
            ---------
            | ${a[0]} ${a[1]} ${a[2]} |
            | ${a[3]} ${a[4]} ${a[5]} |
            | ${a[6]} ${a[7]} ${a[8]} |
            ---------
            """.trimIndent()
        )
    }
    printGraph(a)

    game@while (true) {
        val b = readln().uppercase().filter { !it.isWhitespace() }
        val x = b[0].toString().toInt() - 1
        val y = b[1].toString().toInt() - 1
        val p = 3 * x + y

        when {
            !b.all { it in '0'..'9' } -> println("You should enter numbers!")
            x > 2 || y > 2 -> println("Coordinates should be from 1 to 3!")
            a[p] == "X" || a[p] == "O" -> println("This cell is occupied! Choose another one!")
            else -> {  // run the game
                a[p] = if (runs++ % 2 == 0) "X" else "O"
                printGraph(a)
                fun check(d: String = "") {
                    when (d) {
                        "X" -> println("X wins")
                        "O" -> println("O wins")
                        else -> println("Draw")
                    }
                    endGame = true
                }

                when {
                    (a[0] == a[1] && a[1] == a[2] && a[0] != " ") -> check(a[0]) // top   row
                    (a[3] == a[4] && a[4] == a[5] && a[3] != " ") -> check(a[3]) // mid   row
                    (a[6] == a[7] && a[7] == a[8] && a[6] != " ") -> check(a[6]) // bot   row
                    (a[0] == a[3] && a[3] == a[6] && a[0] != " ") -> check(a[0]) // left  col
                    (a[1] == a[4] && a[4] == a[7] && a[1] != " ") -> check(a[1]) // mid   col
                    (a[2] == a[5] && a[5] == a[8] && a[2] != " ") -> check(a[2]) // right col
                    (a[0] == a[4] && a[4] == a[8] && a[4] != " ") -> check(a[4]) // diagonals \
                    (a[2] == a[4] && a[4] == a[6] && a[4] != " ") -> check(a[4]) // diagonals /
                    runs == 9 -> check()  //draw
                }
                if (endGame) break@game
            }
        }
    }
}
