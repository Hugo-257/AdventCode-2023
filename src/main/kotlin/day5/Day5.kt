package day5

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day5/input.txt")

    val seedsString = ArrayList<String>()
    val seedToSoilMapString = ArrayList<String>()
    val soilToFertilizerMapString = ArrayList<String>()
    val fertilizerToWaterMapString = ArrayList<String>()
    val waterToLightMapString = ArrayList<String>()
    val lightToTemperatureMapString = ArrayList<String>()
    val temperatureToHumidityMapString = ArrayList<String>()
    val humidityToLocationMapString = ArrayList<String>()

    var section = 1

    input.forEachLine {
        if (it == "")
            section += 1
        else
            when (section) {
                1 -> seedsString.add(it)
                2 -> seedToSoilMapString.add(it)
                3 -> soilToFertilizerMapString.add(it)
                4 -> fertilizerToWaterMapString.add(it)
                5 -> waterToLightMapString.add(it)
                6 -> lightToTemperatureMapString.add(it)
                7 -> temperatureToHumidityMapString.add(it)
                8 -> humidityToLocationMapString.add(it)
            }

    }
    val it = 5

    val seeds = seedsString[0].split(":")[1].trim().split(" ")

    val seedToSoilMap = ArrayList<List<Long>>()
    val soilToFertilizerMap = ArrayList<List<Long>>()
    val fertilizerToWaterMap = ArrayList<List<Long>>()
    val waterToLightMap = ArrayList<List<Long>>()
    val lightToTemperatureMap = ArrayList<List<Long>>()
    val temperatureToHumidityMap = ArrayList<List<Long>>()
    val humidityToLocationMap = ArrayList<List<Long>>()


    for (i in 1 until seedToSoilMapString.size) {
        val values = seedToSoilMapString[i].split(" ").map { it.toLong() }
        seedToSoilMap.add(values)
    }

    for (i in 1 until soilToFertilizerMapString.size) {
        val values = soilToFertilizerMapString[i].split(" ").map { it.toLong() }
        soilToFertilizerMap.add(values)
    }

    for (i in 1 until fertilizerToWaterMapString.size) {
        val values = fertilizerToWaterMapString[i].split(" ").map { it.toLong() }
        fertilizerToWaterMap.add(values)
    }

    for (i in 1 until waterToLightMapString.size) {
        val values = waterToLightMapString[i].split(" ").map { it.toLong() }
        waterToLightMap.add(values)
    }

    for (i in 1 until lightToTemperatureMapString.size) {
        val values = lightToTemperatureMapString[i].split(" ").map { it.toLong() }
        lightToTemperatureMap.add(values)
    }

    for (i in 1 until temperatureToHumidityMapString.size) {
        val values = temperatureToHumidityMapString[i].split(" ").map { it.toLong() }
        temperatureToHumidityMap.add(values)
    }

    for (i in 1 until humidityToLocationMapString.size) {
        val values = humidityToLocationMapString[i].split(" ").map { it.toLong() }
        humidityToLocationMap.add(values)
    }

    val locations = seeds.map {
        var soil = inInterval(seedToSoilMap,it.toLong()).second
        var fertilizer = inInterval(soilToFertilizerMap,soil).second
        var water = inInterval(fertilizerToWaterMap,fertilizer).second
        var light = inInterval(waterToLightMap,water).second
        var temperature = inInterval(lightToTemperatureMap,light).second
        var humidity = inInterval(temperatureToHumidityMap,temperature).second
        var location = inInterval(humidityToLocationMap,humidity).second

        location
    }

    locations.minOf { it }

    println("Min location is : ${locations.minOf { it }}")
}


fun inInterval(map:List<List<Long>>, value:Long):Pair<Boolean,Long>{

    for(i in map)
        if(value in i[1] until i[1]+i[2] )
            return Pair(true, i[0]+value-i[1])
    return Pair(false,value)
}