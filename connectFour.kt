package connectfour

fun isNumeric(str: String) = str.all { it in '0'..'9' }

fun main() {
    // Part 1.name
    println("Connect Four")
    println("First player's name:")
    val playerOne = readln()  // Input 1
    println("Second player's name:")
    val playerTwo = readln()  // Input 2
    var scoreOne = 0
    var scoreTwo = 0
    var rows: Int
    var cols: Int
    var games:Int = 1
    val b: Int
    // Part 1.main
    val regex = Regex("""^\d+X\d+$""")
    while (true) {
        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        var input = readln().uppercase().filter { !it.isWhitespace() }   // Input 3
        if (input == "") input = "6X7"
        rows = Character.getNumericValue(input.first())
        cols = Character.getNumericValue(input.last())
        if (!input.matches(regex)) {
            println("Invalid input")
        } else if (rows < 5 || rows > 9) {
            println("Board rows should be from 5 to 9")
        } else if (cols < 5 || cols > 9) {
            println("Board columns should be from 5 to 9")
        } else {
            println(
                """
                Do you want to play single or multiple games?
                For a single game, input 1 or press Enter
                Input a number of games:
                """.trimIndent()
            )
            var a = readln()  // Input 4
            while (!isNumeric(a) || a == "0") {
                println(
                    """
                    Invalid Input
                    Do you want to play single or multiple games?
                    For a single game, input 1 or press Enter
                    Input a number of games:
                    """.trimIndent())
                a = readln()
            }

            println("$playerOne VS $playerTwo")
            println("$rows X $cols board")
            b = if (a == "" || a == "1") {
                println("Single Game")
                "1".toInt()
            } else {
                println("Total $a games")
                a.toInt()
            }
            break
        }
    }


    for (round in 1.. b) {
        if (b > 1) {
            println("Game #${games++}")
        }

        //Game Process

        // Part 2 -> draw
        fun makeGameBoard(): MutableList<MutableList<String>>{
            // Part 2.1 topNumberLines
            val numbers = mutableListOf(" ","1")
            for (i in 2..cols) {
                numbers.add(" ")
                numbers.add("$i")
            }
            val board = mutableListOf(numbers)
            // Part 2.2 midLines
            val lines = mutableListOf("║")
            for (i in 1..cols) {
                lines.add(" ")
                lines.add("║")
            }
            for (i in 1..rows) {board.add(lines.toMutableList()) }
            // Part 2.3 bottomLines
            val bot = mutableListOf("╚")
            for (i in 1 until cols) {bot.add("═╩") }
            bot.add("═╝")
            board.add(bot)

            return board
        }
        val board = makeGameBoard()

        // Part 2.4 Print
        fun printBoard() {
            for (i in board) {println(i.joinToString("")) }
        }
        printBoard()

        // Part 3. Game logic

        val pieces = mutableListOf<Int>()
        for (i in 0..cols) {
            pieces.add(rows)
        }
        var turn: Int = if (games % 2 == 0 || b == 1) 1 else 2

        println( if (turn % 2 == 0) "$playerTwo's turn:" else "$playerOne's turn:")

        var step = readln()
        var n: Int
        var full = rows*cols - 1


        game@ while (step != "end") {
            while (!isNumeric(step) || step=="") {  // 错误
                turn--
                println("Incorrect column number")
                println( if (turn++ % 2 == 0) "$playerOne's turn:" else "$playerTwo's turn:")
                step = readln()
                if (step == "end") { break }
            }
            if (step == "end") { break }
            n = step.toInt()
            if (n > cols || n < 1) {  // 错误
                turn--
                println("The column number is out of range (1 - $cols)")
                println( if (turn++ % 2 == 0) "$playerOne's turn:" else "$playerTwo's turn:")
                step = readln()
            } else if (pieces[n] == 0) {  // 错误
                turn--
                println("Column $step is full")
                println( if (turn++ % 2 == 0) "$playerOne's turn:" else "$playerTwo's turn:")
                step = readln()
            } else {  // 正确运行
                board[pieces[n]][board[0].indexOf(step)] = if (turn % 2 == 0) "*" else "o"
                printBoard()
                // 判断胜利
                var line = ""
                var temp: MutableList<String>
                if (full-- == 0) {
                    println("It is a draw")
                    scoreOne += 1
                    scoreTwo += 1
                    break@game
                }
                for (i in rows downTo 1) {
                    temp = board[i]

                    for (j in 1..cols*2 step 2) {

                        fun printWinner() {
                            if (temp[j] == "o") {
                                println("Player $playerOne won")
                                scoreOne += 2
                            } else {
                                println("Player $playerTwo won")
                                scoreTwo += 2
                            }
                        }

                        if (i >= 4) {  // 判断竖着胜利
                            if (temp[j] != " "
                                && temp[j] == board[i-1][j]
                                && temp[j] == board[i-2][j]
                                && temp[j] == board[i-3][j]) {
                                printWinner()
                                break@game
                            }
                        }  // 判断竖着胜利

                        if (i >= 4 && j < cols) {  // 判断向右斜着胜利
                            if (temp[j] != " "
                                && temp[j] == board[i-1][j+2]
                                && temp[j] == board[i-2][j+4]
                                && temp[j] == board[i-3][j+6]) {
                                printWinner()
                                break@game
                            }
                        }  // 判断向右斜着胜利

                        if (i >= 4 && j >= 6) {  // 判断向左斜着胜利
                            if (temp[j] != " "
                                && temp[j] == board[i-1][j-2]
                                && temp[j] == board[i-2][j-4]
                                && temp[j] == board[i-3][j-6]) {
                                printWinner()
                                break@game
                            }
                        }  // 判断向左斜着胜利
                        line += temp[j]
                    }

                    if ("oooo" in line) {  // 判断横着胜利
                        println("Player $playerOne won")
                        scoreOne += 2
                        break@game
                    } else if ("****" in line) {
                        println("Player $playerTwo won")
                        scoreTwo += 2
                        break@game
                    }
                    line = ""
                }  // 判断横着胜利

                // 结束判断
                pieces[n] -= 1
                println( if (turn++ % 2 == 0) "$playerOne's turn:" else "$playerTwo's turn:")
                step = readln()
            }
        }  //Game Process End
        if (b > 1) {
            println("Score")
            println("$playerOne: $scoreOne $playerTwo: $scoreTwo")
        }
    }
    println("Game Over!")
}
