class BlackJack{
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
            println()
            switch(choice) {
                case "H":
                    hit()
                    break
                case "S":
                    stay()
                    break
                case "Q":
                    quit()
                break
            }
        }
    }

    def hit() {
        givePlayerCard()
        printCards()
        checkForBust()  
    }

    def stay() {
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
            println "Dealer busts! You win!"
            quit()
        }
    }

    def checkForWinner() {
        if (playerWon()) {
            println "Yay! You win!"
            quit()
        } else if (dealerWon()){
            println "Oops! You lose!"
            quit()
        } else {
            println "It's a tie!"
            quit()
        }
    }

    boolean playerWon() {
        playerCards.calculateScore() > dealerCards.calculateScore()
    }

    boolean dealerWon() {
        dealerCards.calculateScore() > playerCards.calculateScore()
    }

    def givePlayerCard() {
        playerCards.drawCards(deck.returnAndRemoveCard())
    }

    def giveDealerCard() {
        dealerCards.drawCards(deck.returnAndRemoveCard())
    }

    def printCards() {
        println "Player has score ${playerCards.calculateScore()} with cards:"
        playerCards.printCards()

        println "Dealer has score ${dealerCards.calculateScore()} with cards:"
        dealerCards.printCards()
    }

    def quit() {
        System.exit 0
    }
}