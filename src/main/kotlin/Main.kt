fun chessPrint() {
    println(
        "  +---+---+---+---+---+---+---+---+\n" +
                "8 | ${game.board[7][0]} | ${game.board[7][1]} | ${game.board[7][2]} | ${game.board[7][3]} | ${game.board[7][4]} | ${game.board[7][5]} | ${game.board[7][6]} | ${game.board[7][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "7 | ${game.board[6][0]} | ${game.board[6][1]} | ${game.board[6][2]} | ${game.board[6][3]} | ${game.board[6][4]} | ${game.board[6][5]} | ${game.board[6][6]} | ${game.board[6][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "6 | ${game.board[5][0]} | ${game.board[5][1]} | ${game.board[5][2]} | ${game.board[5][3]} | ${game.board[5][4]} | ${game.board[5][5]} | ${game.board[5][6]} | ${game.board[5][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "5 | ${game.board[4][0]} | ${game.board[4][1]} | ${game.board[4][2]} | ${game.board[4][3]} | ${game.board[4][4]} | ${game.board[4][5]} | ${game.board[4][6]} | ${game.board[4][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "4 | ${game.board[3][0]} | ${game.board[3][1]} | ${game.board[3][2]} | ${game.board[3][3]} | ${game.board[3][4]} | ${game.board[3][5]} | ${game.board[3][6]} | ${game.board[3][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "3 | ${game.board[2][0]} | ${game.board[2][1]} | ${game.board[2][2]} | ${game.board[2][3]} | ${game.board[2][4]} | ${game.board[2][5]} | ${game.board[2][6]} | ${game.board[2][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "2 | ${game.board[1][0]} | ${game.board[1][1]} | ${game.board[1][2]} | ${game.board[1][3]} | ${game.board[1][4]} | ${game.board[1][5]} | ${game.board[1][6]} | ${game.board[1][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "1 | ${game.board[0][0]} | ${game.board[0][1]} | ${game.board[0][2]} | ${game.board[0][3]} | ${game.board[0][4]} | ${game.board[0][5]} | ${game.board[0][6]} | ${game.board[0][7]} |\n" +
                "  +---+---+---+---+---+---+---+---+\n" +
                "    a   b   c   d   e   f   g   h"
    )
}

object game {
    var board = MutableList<MutableList<Char>>(8) { MutableList<Char>(8) { ' ' } } // mozda postoji laksi nacin
    var brBl = 8
    var brWh = 8
    fun podesi() {
        for (i in 0..7) board[1][i] = 'W'
        for (i in 0..7) board[6][i] = 'B'
    }

}


fun pokretljiv(i: Int, j: Int, bojaBela: Boolean): Boolean {   //TODO: uradiiii
    if (bojaBela == true) {
        return !(game.board[i - 1][j] != ' ' && if (j + 1 <= 7) game.board[i + 1][j + 1] != 'B' else if (j - 1 >= 0) game.board[i + 1][j - 1] != 'B' else true)
    } else {
        return !(game.board[i - 1][j] != ' ' && if (j + 1 <= 7) game.board[i - 1][j + 1] != 'W' else if (j - 1 >= 0) game.board[i - 1][j - 1] != 'W' else true)
    }
}

fun whiteWon(): Boolean {
    var pobeda = false
    for (i in 0..7) {
        if (game.board[7][i] == 'W') pobeda = true
    }
    if (game.brBl <= 0) pobeda = true
    if (pobeda) println("White Wins!")
    return pobeda

}

fun blackWon(): Boolean {
    var pobeda = false
    for (i in 0..7) {
        if (game.board[0][i] == 'B') pobeda = true
    }
    if (game.brWh <= 0) pobeda = true
    if (pobeda) println("Black Wins!")
    return pobeda
}

fun victory(potez: Boolean): Boolean {
    return if (potez) whiteWon() else blackWon()
}

fun stalemate(potez: Boolean): Boolean {
    var c = if (potez) 'W' else 'B'
    var sm = true

    for (i in game.board.indices) {
        for (j in game.board[i].indices) {
            if (game.board[i][j] == c) {
                if (pokretljiv(i, j, potez)) sm = false
            }
        }
    }
    if (sm) println("Stalemate!")

    return sm
}

fun main() {


//    for (i in 0..7) game.board[1][i] = 'W'
//    for (i in 0..7) game.board[6][i] = 'B'
    game.podesi()
    println("Pawns-Only Chess")
    println("First Player's name:")
    val p1 = readLine()!!
    println("Second Player's name:")
    val p2 = readLine()!!
    chessPrint()
//postavka ^^^

    var valid = false
    var x: Int
    var y: Int   // (x,y) koordinate pocetka a (a,b) cilja
    var a: Int
    var b: Int
    var daLi = true
    var input: String
    val control = Regex("[a-h][1-8][a-h][1-8]")
    var eW1: Int = 0
    var eW2: Int = 0
    var pasanB = false
    var pasanW = false
    var eB1 = 0
    var eB2 = 0


    do {
        valid = true
        if (daLi) println("$p1's turn:") else println("$p2's turn:")
        input = readLine()!!
        if (input == "exit") break
        if (input.matches(control)) valid = true
        else {
            println("Invalid Input")
            continue
        }
        y = (input[0].code - 'a'.code)
        b = (input[2].code - 'a'.code)
        x = (input[1].code) - '1'.code
        a = (input[3].code) - '1'.code


        if (daLi) {
            if (game.board[x][y] == 'W') {
                if ((y == b && (a == x + 1 || (a == x + 2 && x == 1)) && game.board[a][b] == ' ') || (a == x + 1 && (b == y + 1 || b == y - 1) && ((game.board[a][b] == 'B') || (pasanB && a == eB1 && b == eB2)))) {
                    if (pasanB) game.board[a - 1][b] = ' '
                    if (game.board[a][b] == 'B') game.brBl--
                    game.board[a][b] = 'W'
                    game.board[x][y] = ' '
                    pasanW = false
                    if (a == x + 2) {
                        eW1 = x + 1
                        eW2 = y
                        pasanW = true
                    }
                } else {
                    println("Invalid input")
                    valid = false
                }

            } else {
                println("No white pawn at ${input[0]}${input[1]}")
                valid = false
            }
        } else if (!daLi) {
            if (game.board[x][y] == 'B') {
                if ((y == b && (a == x - 1 || (a == x - 2 && x == 6)) && game.board[a][b] == ' ') || (a == x - 1 && (b == y + 1 || b == y - 1) && ((game.board[a][b] == 'W') || (pasanW && a == eW1 && b == eW2)))) {
                    if (pasanW) game.board[a + 1][b] = ' '
                    if (game.board[a][b] == 'W') game.brWh--
                    game.board[a][b] = 'B'
                    game.board[x][y] = ' '
                    pasanB = false
                    if (a == x - 2) {
                        eB1 = x - 1
                        eB2 = y
                        pasanB = true
                    }

                } else {
                    println("Invalid input")
                    valid = false
                }

            } else {
                println("No black pawn at ${input[0]}${input[1]}")
                valid = false
            }
        }
        if (valid) {
            chessPrint()
            if (victory(daLi)) break
            if (stalemate(!daLi)) break
            daLi = !daLi
        }


    } while (input != "exit")
    println("Bye!")

}




