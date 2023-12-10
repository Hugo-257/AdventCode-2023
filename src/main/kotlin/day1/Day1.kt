import java.io.File

fun main(args: Array<String>) {
    var res = 0

    val input = File("src/main/kotlin/day1/input.txt")

    val numbersInStrings = listOf<String>("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    //Retrieve words
    input.forEachLine { line ->

        var numbersAndIndex = arrayListOf<Pair<Int, Int>>()
        for (el in numbersInStrings) {
            var start = 0
            var index = -1
            index = line.indexOf(el, start)
            while (index >= 0) {
                if (index >= 0) numbersAndIndex.add(Pair(index, numbersInStrings.indexOf(el) + 1))
                start += el.length+1
                index = line.indexOf(el, start)
            }
        }

        line.forEachIndexed(fun(index, el) {
            if (el.isDigit()) numbersAndIndex.add(Pair(index, el.digitToInt()))
        })

        numbersAndIndex.sortBy { it.first }


        var number = "${numbersAndIndex.first().second}${numbersAndIndex.last().second}".toInt()
        res += number.toInt()

    }

    println(res)
}