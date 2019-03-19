class Deck {
    def cards = []

    Deck(... args) {
        drawCards(args)
    }

    def drawCards(... args) {
        args.each{ i ->
            cards.add(i)
        }
    }

    def shuffleCards() {
        Collections.shuffle(cards)
    }

    def returnAndRemoveCard() {
        def tempCard = cards[0]
        cards.remove(0)
        return tempCard
    }

    def printCards() {
        cards.each { i ->
            println "${i}"
        }
    }

    def int calculateScore() {
        def totalScore = 0
        def aceExists = 0;
        cards.each{ i ->
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

}