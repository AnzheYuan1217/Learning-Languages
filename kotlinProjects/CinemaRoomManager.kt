package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val lines = readln().toInt()

    val seats = rows * lines

    val totalIncome = if (seats < 60) (seats * 10) else (rows/2 * lines * 10 + (rows - rows/2) * lines * 8)


    val topLine = mutableListOf(" ")
    for (i in 1..lines) topLine.add(i.toString())

    val room = mutableListOf(topLine)

    for (i in 1..rows) {
        val perLine = mutableListOf(i.toString())
        for (j in 1..lines) perLine.add("S")
        room.add(perLine)
    }
    var sold = 0
    var income = 0
    fun printSeats() {
        println("Cinema:")
        for (i in room) {
            println(i.joinToString(" "))
        }
    }

    fun buyOneTicket() {
        var a: Int
        var b: Int
        while (true) {
            while (true) {
                println("Enter a row number:")
                a = readln().toInt()
                println("Enter a seat number in that row:")
                b = readln().toInt()
                if (a > rows || b > lines) {
                    println("Wrong input!")
                } else {
                    break
                }
            }
            if (room[a][b] == "B") {
                println("That ticket has already been purchased!")
            } else {
                room[a][b] = "B"
                break
            }
        }
        val price = if (seats < 60 || a <= rows/2) 10 else 8
        income += price
        sold += 1
        println("Ticket price: $$price")
        printSeats()
    }

    fun cinemaStatistics() {
        val ticketsPercentage = (sold * 100).toDouble() / seats.toDouble()
        val formatPercentage = "%.2f".format(ticketsPercentage)
        println(
            """
                Number of purchased tickets: $sold
                Percentage: ${formatPercentage}%
                Current income: $$income
                Total income: $$totalIncome
            """.trimIndent()
        )
    }
    while (true) {
        println(
            """
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
            """.trimIndent())

        when (readln()) {
            "1" -> printSeats()
            "2" -> buyOneTicket()
            "3" -> cinemaStatistics()
            "0" -> break
        }
    }


}
