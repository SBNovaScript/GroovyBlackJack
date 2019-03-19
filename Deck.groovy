class Deck {
    private def cards = []
    private def containsAce = false

    Deck(... args) {
        drawCards(args)
    }

    def drawCards(... args) {
        args.each{ i ->
            if (i == "A") {
                containsAce = true
            }
            cards.add(i)
        }
    }

    def shuffleCards() {
        Collections.shuffle(cards)
    }

    def returnAndRemoveCard() {
        def tempCard = cards[0]
        cards.remove 0 
        return tempCard
    }

    def printCards() {
        cards.each { i ->
            println "${i}"
        }
    }

    def calculateScore() {
        int totalScore = calculateInitialScore()

        if (containsAce) {
            totalScore = calculateAcesWithScore(totalScore)
        }

        return totalScore
    }

    private calculateInitialScore() {
        int totalScore = 0
        cards.each{ i ->
            if (i instanceof Integer) {
                totalScore += i
            } else {
                totalScore += faceCardToValue i
            }
        }
        return totalScore
    }

    private calculateAcesWithScore(totalScore) {
        if (aceCanBeLargeIn(totalScore)) {
            totalScore = largerAce(totalScore)
        }
        return totalScore
    }

    private boolean aceCanBeLargeIn(totalScore) {
        largerAce(totalScore) <= 21
    }

    private int largerAce(totalScore) {
        (totalScore - 1) + 11
    }

    private int faceCardToValue(card) {
        if(card == "A") {
            return 1
        } else {
            return 10
        }
    }
}