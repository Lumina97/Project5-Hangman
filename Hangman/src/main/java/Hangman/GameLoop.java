package Hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class GameLoop {

    InputValidator inputValidator = new InputValidator();

    String[] HangManArt = {
            "+---+\n    |\n    |\n    |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n    |\n    |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n    |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n|   |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n|   |\n|   |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n|   |\n|   |\n^   |\n    |\n   ===\n"};


    String[] wordsToGuess = {"cat", "dog", "mouse", "horse", "house", "animal"};
    ArrayList<String> wrongLetters = new ArrayList<>();
    ArrayList<String> rightLetters = new ArrayList<>();

    String word = "";
    int currentTry = 0;

    //Grabs a new word and resets the game to starting settings
    public void startGame() {
        word = wordsToGuess[generateNewRandomNumberFromRange(0, wordsToGuess.length)];
        //reset variables
        currentTry = 0;
        wrongLetters.clear();
        rightLetters.clear();

        out.println("H A N G M A N");
        out.println(HangManArt[currentTry]);

        loop();
    }

    //Game loop
    private void loop() {
        boolean isPlaying = true;
        boolean replay = false;
        do {
            displayGuessedLetters();
            playRound();

            GameState state = checkHasFinishedGame();
            if (state != GameState.playing) {
                replay = endOfGame(state);
                isPlaying = false;
            }
        }
        while (isPlaying);

        if (replay) startGame();
    }

    // lets the player guess and evaluates said guess
    private void playRound() {
        out.println("Guess a Letter!");
        String guess = inputValidator.GetValidCharacterInput(new Scanner(System.in)).toString();

        if (wrongLetters.contains(guess)) {
            out.println("You already guessed that letter! Try again!");
            return;
        }

        if (word.contains(guess)) {

            out.println(guess + " was correct!");
            rightLetters.add(guess);
            if (checkHasFinishedGame() == GameState.playing) {
                printHangMan();
                out.println("Your word so far:");
                out.println(getCurrentWordWithGuesses());
            }

        } else {

            wrongLetters.add(guess);
            out.println("Your guess was wrong.");
            currentTry++;

            if (checkHasFinishedGame() == GameState.playing) {
                printHangMan();
                out.println(getCurrentWordWithGuesses());
            }
        }
    }

    private void printHangMan() {
        out.println(HangManArt[currentTry]);
    }

    private void displayGuessedLetters() {
        if (wrongLetters.size() > 0) {
            out.println("Guessed Letters:");
            for (String wrongLetter : wrongLetters) {
                out.print(wrongLetter + " , ");
            }
        }
        out.println("\n");
    }

    private String getCurrentWordWithGuesses() {
        //convert word to list
        List<String> wordList = Arrays.stream(word.split("")).toList();
        //stream over list and check if rightLetters contains current char
        //print it out if yes and print a space if no
        return wordList.stream()
                .reduce("", (acc, element) -> {
                    if (rightLetters.contains(element))
                        return acc + " " + element;

                    return acc + " _ ";
                });
    }

    private GameState checkHasFinishedGame() {
        String guessedWord = getCurrentWordWithGuesses();

        if (currentTry >= HangManArt.length) return GameState.lost;

        if (guessedWord.contains("_"))
            return GameState.playing;
        else
            return GameState.won;
    }

    private boolean endOfGame(GameState state) {

        if (state == GameState.won)
            out.println("You have guessed the word: " + word);
        else if(state == GameState.lost)
            out.println("You didn't manage to guess the word...");


        out.println("Would you like to play again?");
        out.println("Yes or No ");

        String input = inputValidator.GetValidStringInput(new Scanner(System.in), new String[]{"Yes", "No", "yes", "no"});

        return input.equals("yes") || input.equals("Yes");
    }

    private int generateNewRandomNumberFromRange(int minNumber, int maxNumber) {
        return (int) ((Math.random() * (maxNumber - minNumber)) + minNumber);
    }
}
