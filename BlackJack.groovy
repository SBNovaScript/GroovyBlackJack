
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
        def choice = "H"
        while (choice == "H") {
            choice = System.console().readLine 'Do you want to (H)it, (S)tay, or (Q)uit? '
            switch(choice) {
                case "H":
                    Hit()
                    break
                case "S":
                    Stay()
                    break
                case "Q":
                    quit()
                break
            }
        }
    }

    static def Hit() {
        givePlayerCard()
        printCards()
        checkForBust()  
    }

    static def Stay() {
        while (calculateScoreFor(dealerCards) < 17) {
            giveDealerCard()
        }
        printCards()
        checkForBust()
        checkForWinner()
    }

    static def checkForBust() {
        if (calculateScoreFor(playerCards) > 21) {
            println "Oops! You Bust!"
            quit()
        }
        if (calculateScoreFor(dealerCards) > 21) {
            println "Dealer busts! You won!"
            quit()
        }
    }

    static def checkForWinner() {
        if (playerWon()) {
            println "Yay! You won!"
            quit()
        } else {
            println "Oops! You lost!"
            quit()
        }
    }

    static def boolean playerWon() {
        return calculateScoreFor(playerCards) > calculateScoreFor(dealerCards)
    }

    static def int calculateScoreFor(player) {
        def totalScore = 0
        def aceExists = 0;
        player.each{ i ->
            if (i instanceof Integer) {
                totalScore += i
            } else {
                totalScore += faceCardToValue(i)
            }
        }

        if (aceExists > 0) {
            if ((totalScore - 1) + 11 <= 21) {
                totalScore = (totalScore - 1) + 11
            }
        }

        return totalScore
    }

    static def int faceCardToValue(card) {
        switch(card) {
            case "A":
                return 1
                break
            default:
                return 10
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

    static def quit() {
        System.exit(0)
    }
}



