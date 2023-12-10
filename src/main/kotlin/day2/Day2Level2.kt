import java.io.File

fun main(args: Array<String>) {
    var file = File("src/main/kotlin/day2/input.txt")

    var sumPower = 0
    file.forEachLine {
        var minRed = 1
        var minBlue = 1
        var minGreen = 1
        val split1 = it.split(":")
        val gameNumber = split1[0].split(" ")[1]
        val sets = split1[1].split(";")
        for (set in sets) {
            var cubes = set.split(",")
            var cubesTrimmed = cubes.map { it.trim() }

            var cubesMap = LinkedHashMap<String, Int>()
            for (el in cubesTrimmed) {
                var arrayOfCubeAndCount = el.split(" ")
                cubesMap[arrayOfCubeAndCount[1]] = arrayOfCubeAndCount[0].toInt()
            }

            // blue
            if (cubesMap["blue"] != null)
                if (minBlue < cubesMap["blue"]!!) minBlue = cubesMap["blue"]!!

            // green
            if (cubesMap["green"] != null)
                if (minGreen < cubesMap["green"]!!) minGreen = cubesMap["green"]!!

            // red
            if (cubesMap["red"] != null)
                if (minRed < cubesMap["red"]!!) minRed = cubesMap["red"]!!
        }
        sumPower += (minRed  * minBlue * minGreen)

    }
    println(sumPower)
}


