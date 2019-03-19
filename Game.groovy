class Game {
    static main(args) {

        BlackJack mainGame = new BlackJack()

        mainGame.shuffleCards()
        mainGame.startDrawingCards()
        mainGame.printCards()
        mainGame.getPlayerInput()
        
    }
}