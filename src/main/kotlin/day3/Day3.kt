import java.io.File

fun main(args: Array<String>) {
    var input = ArrayList<String>()
    File("src/main/kotlin/day3/input.txt").forEachLine { input.add(it) }

    val maxX = input.size - 1
    val maxY = input[0].length - 1


    val numbers = ArrayList<Number>()
    val gears = HashMap<String, ArrayList<Number>>()

    var numLine = 0
    for (line in input) {
        numbers.addAll(retrieveNumbers(line, numLine))
        numLine += 1
    }


    var apartNumbers = numbers.filter { it.isApart(input, maxX, maxY) }

    fun extracted(x: Int, y: Int, number: Number) {
        val id = "G-$x-$y"
        var value = gears[id]
        if (value == null) gears[id] = arrayListOf(number)
        else gears[id]!!.add(number)
    }

    for (number in apartNumbers) {
        //Horizontally
        if (number.start.y > 0) if (input[number.start.x][number.start.y - 1] == '*') {
            extracted(number.start.x, number.start.y - 1, number)
        }

        if (number.end.y < maxY) if (input[number.start.x][number.end.y + 1] == '*') {
            extracted(number.start.x, number.end.y + 1, number)
        }


        //Vertically
        //Up
        if (number.start.x > 0) for (i in number.start.y - 1..number.end.y + 1)
            if (i in 0..maxY)
                if (input[number.start.x - 1][i] == '*') {
                    extracted(number.start.x - 1, i, number)
                }

        //Down
        if (number.start.x < maxX)
            for (i in number.start.y - 1..number.end.y + 1)
                if (i in 0..maxY) if (input[number.start.x + 1][i] == '*')
                    extracted(number.start.x + 1, i, number)
    }


    println("Apart numbers sum : ${apartNumbers.sumOf { it.value }}")

    var ratioGears = 0
    for (i in gears) {
        if (i.value.size == 2)
            ratioGears += i.value.first().value * i.value.last().value
    }

    println("Gear Ratio Sum : $ratioGears")
}


fun retrieveNumbers(line: String, numLine: Int): ArrayList<Number> {
    val numbers = ArrayList<Number>()

    val regexPattern = "\\b\\d+\\b".toRegex()

    val matches = regexPattern.findAll(line)

    for (match in matches) {
        val value = match.value
        val startIndex = match.range.first
        val endIndex = match.range.last
        numbers.add(Number(value.toInt(), Position(numLine, startIndex), Position(numLine, endIndex)))
    }

    return numbers
}


open class Element(val start: Position, val end: Position)

class Number(val value: Int, start: Position, end: Position) : Element(start, end) {


    fun isApart(matrice: List<String>, maxX: Int, maxY: Int): Boolean {
        //is any symbol except (.)

        //Horizontally
        if (start.y > 0) if (matrice[start.x][start.y - 1] != '.') return true

        if (end.y < maxY) if (matrice[start.x][end.y + 1] != '.') return true

        //Vertically
        //Up
        if (start.x > 0) for (i in start.y - 1..end.y + 1) if (i in 0..maxY) if (matrice[start.x - 1][i] != '.') return true

        //Down
        if (start.x < maxX) for (i in start.y - 1..end.y + 1) if (i in 0..maxY) if (matrice[start.x + 1][i] != '.') return true

        return false

    }

}

class Position(var x: Int, var y: Int)