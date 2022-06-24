package Hangman;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class GameLoop {

    InputValidator inputValidator = new InputValidator();

    String[] HangMen = {"+---+\n    |\n    |\n    |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n    |\n    |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n    |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n|   |\n    |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n|   |\n|   |\n    |\n    |\n   ===\n",
            "+---+\nO   |\n|   |\n|   |\n|   |\n^   |\n    |\n   ===\n"};


    String[] wordsToGuess = {"cat", "dog", "mouse", "horse", "house", "animal"};
    ArrayList<Character> wrongLetters = new ArrayList<Character>();
    ArrayList<Character> rightLetters = new ArrayList<Character>();

    String word = "";
    int currentTry = 0;

    //Grabs a new word and resets the game to starting settings
    public void StartGame() {
        word = wordsToGuess[GenerateNewRandomNumberFromRange(0, wordsToGuess.length)];
        out.println("H A N G M A N");
        out.println(HangMen[currentTry]);
        //reset variables
        currentTry = 0;
        wrongLetters.clear();
        rightLetters.clear();

        Loop();
    }

    //Game loop
    private void Loop() {
        boolean isPlaying = true;
        boolean replay = false;
        do {
            DisplayGuessedLetters();
            PlayRound();
            if (CheckHasFinishedGame()){
                replay = EndOfGame(true);
                isPlaying =  false;
            }
        }
        while (isPlaying);

        if(replay) StartGame();
    }

    // lets the player guess and evaluates said guess
    private void PlayRound() {
        out.println("Guess a Letter!");
        Character guess = inputValidator.GetValidCharacterInput(new Scanner(System.in));

        if (wrongLetters.contains(guess)) {
            out.println("You already guessed that letter! Try again!");
            return;
        }

        if (word.contains(guess.toString())) {

            out.println(guess + " was correct!");
            rightLetters.add(guess);

            PrintHangMan();
            PrintCurrentWordWithGuesses();
        } else {

            wrongLetters.add(guess);
            out.println("Your guess was wrong.");
            currentTry++;
            PrintHangMan();

            PrintCurrentWordWithGuesses();
        }
    }

    private void PrintHangMan() {
        out.println(HangMen[currentTry]);
    }

    private void PrintCurrentWordWithGuesses() {
        out.println("Your word so far:");
        for (int i = 0; i < word.length(); i++) {
            if (rightLetters.contains(word.charAt(i))) {
                out.print(word.charAt((i)));
            }
        }
        out.println("\n");
    }

    private void DisplayGuessedLetters() {
        if (wrongLetters.size() > 0) {
            out.println("Guessed Letters:");
            for (Character wrongLetter : wrongLetters) {
                out.print(wrongLetter + " , ");
            }
        }
        out.println("\n");
    }

    private boolean CheckHasFinishedGame() {

        boolean guessedTheWord = true;

        for (int i = 0; i < word.length(); i++) {
            if (rightLetters.contains(word.charAt(i)) == false) {
                guessedTheWord = false;
                break;
            }
        }

        return guessedTheWord;
    }

    private boolean EndOfGame(boolean guessedCorrectly) {

        if (guessedCorrectly)
            out.println("You have guessed the word: " + word);
        else
            out.println("You didn't manage to guess the word...");
        
        
        out.println("Would you like to play again?");
        out.println("Yes or No ");

        String input = inputValidator.GetValidStringInput(new Scanner(System.in), new String[]{"Yes", "No", "yes", "no"});

        if (input.equals("yes") || input.equals("Yes")) {
           return true;
        }

        return false;
    }

    private int GenerateNewRandomNumberFromRange(int minNumber, int maxNumber) {
        return (int) ((Math.random() * (maxNumber - minNumber)) + minNumber);
    }
}
