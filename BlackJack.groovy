public class BlackJack{
    static def deck = new Deck(2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K", "A")
    static def playerCards = new Deck()
    static def dealerCards = new Deck()

    static main(args) {
        shuffleCards()
        startDrawingCards()
        printCards()
        getPlayerInput()
    }

    static def shuffleCards() {
        deck.shuffleCards()
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
        while (dealerCards.calculateScore() < 17) {
            giveDealerCard()
        }
        printCards()
        checkForBust()
        checkForWinner()
    }

    static def checkForBust() {
        if (playerCards.calculateScore() > 21) {
            println "Oops! You Bust!"
            quit()
        }
        if (dealerCards.calculateScore() > 21) {
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
        return playerCards.calculateScore() > dealerCards.calculateScore()
    }

    static def givePlayerCard() {
        playerCards.drawCards(deck.returnAndRemoveCard())
    }

    static def giveDealerCard() {
        dealerCards.drawCards(deck.returnAndRemoveCard())
    }

    static def printCards() {
        println "Player cards:"
        playerCards.printCards()

        println "Dealer cards:"
        dealerCards.printCards()
    }

    static def quit() {
        System.exit(0)
    }
}