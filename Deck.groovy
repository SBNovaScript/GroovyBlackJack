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

    private def calculateInitialScore() {
        int totalScore = 0
        cards.each{ i ->
            if (i instanceof Integer) {
                totalScore += i
            } else {
                def newValue = faceCardToValue i
                totalScore += newValue
            }
        }
        return totalScore
    }

    private def calculateAcesWithScore(totalScore) {
        if ((totalScore - 1) + 11 <= 21) {
            totalScore = (totalScore - 1) + 11
        }
        return totalScore
    }

    private int faceCardToValue(card) {
        switch(card) {
            case "A":
                return 1
                break
            default:
                return 10
                break
        }
    }

}