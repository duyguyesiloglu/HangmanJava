import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = { "duygu", "engineer", "software", "agile", "computer" };
    private static final int MAX_TRIES = 6;

    private String secretWord;
    private StringBuilder guessedWord;
    private int triesLeft;
    private StringBuilder lettersGuessed;

    public void play() {
        initializeGame();

        while (triesLeft > 0 && guessedWord.indexOf("_") != -1) {
            displayGameState();
            char guess = getValidGuess();
            updateGameState(guess);
        }

        displayGameResult();
    }

    private void initializeGame() {
        secretWord = getRandomWord();
        guessedWord = new StringBuilder("_".repeat(secretWord.length()));
        triesLeft = MAX_TRIES;
        lettersGuessed = new StringBuilder();
    }

    private String getRandomWord() {
        int index = (int) (Math.random() * WORDS.length);
        return WORDS[index];
    }

    private void displayGameState() {
        System.out.println("Guessed Word: " + guessedWord);
        System.out.println("Tries Left: " + triesLeft);
        System.out.println("Letters Guessed: " + lettersGuessed);
    }

    private char getValidGuess() {
        Scanner scanner = new Scanner(System.in);
        char guess;

        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextLine().toLowerCase().charAt(0);
        } while (!isValidGuess(guess));

        return guess;
    }

    private boolean isValidGuess(char guess) {
        if (!Character.isLetter(guess)) {
            System.out.println("Please enter a valid letter.");
            return false;
        }

        if (lettersGuessed.indexOf(String.valueOf(guess)) != -1) {
            System.out.println("You have already guessed this letter.");
            return false;
        }

        return true;
    }

    private void updateGameState(char guess) {
        lettersGuessed.append(guess);

        if (secretWord.indexOf(guess) != -1) {
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) {
                    guessedWord.setCharAt(i, guess);
                }
            }
        } else {
            triesLeft--;
        }
    }

    private void displayGameResult() {
        if (guessedWord.indexOf("_") == -1) {
            System.out.println("Congratulations! You guessed the word: " + guessedWord);
        } else {
            System.out.println("Oops! You ran out of tries. The word was: " + secretWord);
        }
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }
}
