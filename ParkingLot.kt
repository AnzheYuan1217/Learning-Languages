package parking

class Car {
    var reg: String = ""
    var color: String = ""
}

fun main() {
    var parked = 0
    var spots = 0
    var parkFree = MutableList(spots) { Car() }
    var notCreated = true

    while (true) {
        val a = readln()
        val s: MutableList<String> = a.split(" ") as MutableList<String>

        fun setAParking() {
            parked = 0
            spots = s[1].toInt()
            notCreated = false
            parkFree = MutableList(spots) { Car() }
            println("Created a parking lot with $spots spots.")
        }

        fun parkingCar() {
            if (notCreated) {
                println("Sorry, a parking lot has not been created.")
                return
            }
            for (i in parkFree.indices) {
                if (parkFree[i].reg == "") {
                    parkFree[i].reg = s[1]
                    parkFree[i].color = s[2].lowercase()
                    parked += 1
                    println("${s[2]} car parked in spot ${i + 1}.")
                    return
                }
            }
            println("Sorry, the parking lot is full.")
        }

        fun printStatus() {
            when {
                notCreated -> println("Sorry, a parking lot has not been created.")
                parked == 0 -> println("Parking lot is empty.")
                else -> {
                    for (i in parkFree.indices) {
                        if (parkFree[i].reg != "") {
                            println("${i + 1} ${parkFree[i].reg} ${parkFree[i].color}")
                        }
                    }
                }
            }
        }

        fun leaveCars() {
            if (notCreated) {
                println("Sorry, a parking lot has not been created.")
            } else {
                parkFree[s[1].toInt()-1].reg = ""
                parkFree[s[1].toInt()-1].color = ""
                println("Spot ${s[1]} is free.")
            }
        }

        fun regByColor() {
            if (notCreated) {
                println("Sorry, a parking lot has not been created.")
                return
            }
            val colorSpots: MutableList<String> = mutableListOf()
            for (i in parkFree) {
                if(i.color == s[1].lowercase()) {
                    colorSpots.add(i.reg)
                }
            }
            if (colorSpots.size == 0) {
                println("No cars with color ${s[1]} were found.")
            } else {
                println(colorSpots.joinToString(", "))
            }
        }

        fun spotByColor() {
            if (notCreated) {
                println("Sorry, a parking lot has not been created.")
                return
            }
            val colorSpots: MutableList<Int> = mutableListOf()
            for (i in parkFree.indices) {
                if(parkFree[i].color == s[1].lowercase()) {
                    colorSpots.add(i+1)
                }
            }
            if (colorSpots.size == 0) {
                println("No cars with color ${s[1]} were found.")
            } else {
                println(colorSpots.joinToString(", "))
            }
        }

        fun spotByReg() {
            if (notCreated) {
                println("Sorry, a parking lot has not been created.")
                return
            }
            val colorSpots: MutableList<Int> = mutableListOf()
            for (i in parkFree.indices) {
                if(parkFree[i].reg == s[1]) {
                    colorSpots.add(i+1)
                }
            }
            if (colorSpots.size == 0) {
                println("No cars with registration number ${s[1]} were found.")
            } else {
                println(colorSpots.joinToString(", "))
            }
        }

        when (s[0]) {
            "create" -> setAParking()

            "leave" -> leaveCars()

            "park" -> parkingCar()

            "status" -> printStatus()

            "reg_by_color" -> regByColor()

            "spot_by_color" -> spotByColor()

            "spot_by_reg" -> spotByReg()

            "exit" -> break

            else -> break
        }
    }
}
