import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/day4/input.txt")

    var sumPoints = 0

    val cards = ArrayList<Card>()

    var maxNumberOfCards=0


    var numCard = 0
    input.forEachLine {

        numCard++

        val listsNumbers = it.split(":")[1]

        val regexPattern = "\\b\\d+\\b".toRegex()

        val matches = regexPattern.findAll(listsNumbers)

        val numbers = matches.map { match -> match.value.toInt() }.toList()

        val occurences = numbers.map { number -> Pair<Int, Int>(number, Collections.frequency(numbers, number)) }

        val ownedWinningNumbers = occurences.filter { el -> el.second == 2 }.toSet()

        var points = 0

        for (el in ownedWinningNumbers) {
            if (points == 0)
                points = 1
            else
                points *= 2
        }

        val card = Card(numCard)
        card.numberOfWinningNumbersOwned = ownedWinningNumbers.size
        card.instances = 1

        cards.add(card)

        sumPoints += points

        maxNumberOfCards++
    }

    //Count copiedCards

    for (card in cards) {
        var cardsCopied = 1
        while (cardsCopied <= card.numberOfWinningNumbersOwned &&  (card.id + cardsCopied)<=maxNumberOfCards) {
            cards[card.id + cardsCopied-1].instances += 1*card.instances
            cardsCopied++
        }
    }


    val sumScratchCards= cards.sumOf { card->card.instances }

    println("Total point : $sumPoints")
    println("Total scratchcards : $sumScratchCards")

}

class Card(val id: Int) {
    var numberOfWinningNumbersOwned: Int = 0
    var instances = 0
}