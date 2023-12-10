import java.io.File

fun main(args: Array<String>) {
    var file = File("src/main/kotlin/day2/input.txt")

    var possibleGames = mutableSetOf<Int>()
    var gameIsValid = true

    file.forEachLine {
        gameIsValid = true
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
            if (cubesMap["blue"] != null) if (cubesMap["blue"]!! > 14) gameIsValid = false

            // green
            if (cubesMap["green"] != null) if (cubesMap["green"]!! > 13) gameIsValid = false

            // red
            if (cubesMap["red"] != null) if (cubesMap["red"]!! > 12) gameIsValid = false

            if (!gameIsValid) break
        }

        if (gameIsValid) possibleGames.add(gameNumber.toInt())

    }

    println(possibleGames.sum())

}


