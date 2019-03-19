
public class BlackJack{
    static def deck = [2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K", "A"]
    static def playerCards = []
    static def dealerCards = []

    static main(args) {
        shuffleCards()
        startDrawingCards()
        printCards()
        getPlayerInput()
    }

    static def shuffleCards() {
        Collections.shuffle(deck)
    }

    static def startDrawingCards() {
        giveDealerCard()
        givePlayerCard()
        givePlayerCard()
    }

    static def getPlayerInput() {
        def choice = System.console().readLine 'Do you want to (H)it, (S)tay, or (Q)uit? '
        switch(choice) {
            case "H":
                givePlayerCard()
                printCards()
                calculateScoreFor(playerCards)
            break
        }
        
    }

    static def calculateScoreFor(player) {
        def totalScore = 0
        player.each{ i ->
            if (i instanceof Integer) {
                totalScore += i
            } else {
                totalScore += faceCardToValue(i)
            }
        }
        println totalScore
    }

    static def int faceCardToValue(card) {
        switch(card) {
            case "J":
                return 10
                break
            case "Q":
                return 11
                break
            case "K":
                return 12
                break
            case "A":
                return 1
                break
        }
    }

    static def givePlayerCard() {
        def tempCard = deck[0]
        deck.remove(0)
        playerCards.add(tempCard)
    }

    static def giveDealerCard() {
        def tempCard = deck[0]
        deck.remove(0)
        dealerCards.add(tempCard)
    }

    static def printCards() {
        println "Player cards:"
        playerCards.each { i ->
            println "${i}"
        }
        println "Dealer cards:"
        dealerCards.each { i ->
            println "${i}"
        }
    }
}



