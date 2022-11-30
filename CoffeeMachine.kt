package machine

var water:Int = 400
var milk:Int = 540
var beans:Int = 120
var cups = 9
var money = 550

var waterNeed = 0
var milkNeed = 0
var beansNeed = 0
var moneyEarned = 0

/*
enum class Menus(val names: String, val code: Int, val water: Int, val milk: Int, val beans: Int, val money: Int){
    ESPRESSO("espresso", 1, 250, 0, 16, 4),
    LATTE("latte", 2, 350, 75, 20, 7),
    CAPPUCCINO("cappuccino", 3, 200, 100, 12, 6)
}
 */

class Actions(val action: String){
    fun printRemaining() {
        println(
            """
            The coffee machine has:
            $water ml of water
            $milk ml of milk
            $beans g of coffee beans
            $cups disposable cups
            $$money of money
            """.trimIndent())
    }

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        when(readln()) {
                "1" -> {
                    waterNeed = 250
                    milkNeed = 0
                    beansNeed = 16
                    moneyEarned = 4
                }
                "2" -> {
                    waterNeed = 350
                    milkNeed = 75
                    beansNeed = 20
                    moneyEarned = 7
                }
                "3" -> {
                    waterNeed = 200
                    milkNeed = 100
                    beansNeed = 12
                    moneyEarned = 6
                }
                "back" -> return
            }
        println( when {
            water < waterNeed -> "Sorry, not enough water!"
            milk < milkNeed -> "Sorry, not enough milk!"
            beans < beansNeed -> "Sorry, not enough beans!"
            cups < 1 -> "Sorry, not enough cups!"
            else -> {
                water -= waterNeed
                milk -= milkNeed
                beans -= beansNeed
                money += moneyEarned
                cups -= 1
                "I have enough resources, making you a coffee!"
            }
        })
    }

    fun fill() {
        println("Write how many ml of water you want to add:")
        water += readln().toInt()
        println("Write how many ml of milk you want to add:")
        milk += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        beans += readln().toInt()
        println("Write how many disposable cups you want to add:")
        cups += readln().toInt()
    }

    fun take() {
        println("I gave you $$money")
        money = 0
    }
}

fun main() {

    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        val action = Actions(readln())

        when (action.action) {
            "buy" -> action.buy()
            "fill" -> action.fill()
            "take" -> action.take()
            "remaining" -> action.printRemaining()
            "exit" -> break
        }
    }

}
