public class BlackJack{
    def deck = new Deck(2, 3, 4, 5, 6, 7, 8, 9, 10, "J", "Q", "K", "A")
    def playerCards = new Deck()
    def dealerCards = new Deck()

    def shuffleCards() {
        deck.shuffleCards()
    }

    def startDrawingCards() {
        giveDealerCard()
        givePlayerCard()
        givePlayerCard()
    }

    def getPlayerInput() {
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

    def Hit() {
        givePlayerCard()
        printCards()
        checkForBust()  
    }

    def Stay() {
        while (dealerCards.calculateScore() < 17) {
            giveDealerCard()
        }
        printCards()
        checkForBust()
        checkForWinner()
    }

    def checkForBust() {
        if (playerCards.calculateScore() > 21) {
            println "Oops! You Bust!"
            quit()
        }
        if (dealerCards.calculateScore() > 21) {
            println "Dealer busts! You won!"
            quit()
        }
    }

    def checkForWinner() {
        if (playerWon()) {
            println "Yay! You won!"
            quit()
        } else {
            println "Oops! You lost!"
            quit()
        }
    }

    def boolean playerWon() {
        return playerCards.calculateScore() > dealerCards.calculateScore()
    }

    def givePlayerCard() {
        playerCards.drawCards(deck.returnAndRemoveCard())
    }

    def giveDealerCard() {
        dealerCards.drawCards(deck.returnAndRemoveCard())
    }

    def printCards() {
        println "Player cards:"
        playerCards.printCards()

        println "Dealer cards:"
        dealerCards.printCards()
    }

    def quit() {
        System.exit(0)
    }
}