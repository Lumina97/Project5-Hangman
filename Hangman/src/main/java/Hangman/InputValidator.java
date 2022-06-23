package Hangman;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.out;

public class InputValidator {

    //Returns valid number user input in a given range of values
    public int GetValidNumberInput(Scanner input, int validMin, int validMax) {
        do {
            try {
                int result = input.nextInt();

                if (result >= validMin && result <= validMax)
                    return result;
                else {
                    out.println("Your input was invalid. Please enter either " + validMin + " or " + validMax + ".");
                    continue;
                }
            }
            //Handle wrong input exception (i.e. entering string when expecting number
            catch (java.util.InputMismatchException e) {
                out.println("Your input was invalid. Please enter either " + validMin + " or " + validMax + ".");
                input.nextLine();
            }
            //Handle scanner closing
            catch (IllegalStateException e) {
                out.println("The scanner was closed before this was executed!");
                out.println("Defaulting response to validMin: " + validMin + ".");
                return validMin;
            }
            //handle any other unexpected exceptions
            catch (Exception e) {
                out.println("There was an unhandled exception: " + e.getMessage());
                out.println("Exiting program.");
                exit(-1);
            }
        }
        while (true);
    }

    public String GetValidStringInput(Scanner input, String[] validInput) {
        do {
            try {
                String result = input.nextLine();

                if (validInput.length == 0) {
                    out.println("Given string array was empty...");
                    throw new Exception("Given string array was empty... Inputvalidator -> GetValidStringInput()");
                }

                for (int i = 0; i < validInput.length; i++) {
                    if (result.equals(validInput[i])) {
                        return result;
                    }
                }
                out.println("Your input was invalid. Please enter one of the following" + Arrays.toString(validInput) + ".");
                continue;

            }
            //Handle wrong input exception (i.e. entering number when expecting string
            catch (java.util.InputMismatchException e) {
                out.println("Your input was invalid. Please enter one of the following" + Arrays.toString(validInput) + ".");
                input.nextLine();

            }
            //Handle scanner closing
            catch (IllegalStateException e) {
                out.println("The scanner was closed before this was executed!");
                out.println("Defaulting response to: " + validInput[0] + ".");
                return validInput[0];
            }
            //handle any other unexpected exceptions
            catch (Exception e) {
                out.println("There was an unhandled exception: " + e.getMessage());
                out.println("Exiting program.");
                exit(-1);
            }
        }
        while (true);
    }


    public Character GetValidCharacterInput(Scanner input) {
        do {
            try {
                String result = input.nextLine();

                if(result.length() == 1)
                    return result.charAt(0);

                out.println("Your input was invalid. Please enter a single character");
                continue;

            }
            //Handle wrong input exception (i.e. entering number when expecting string
            catch (java.util.InputMismatchException e) {
                out.println("Your input was invalid. Please enter a single character");
                input.nextLine();

            }
            //Handle scanner closing
            catch (IllegalStateException e) {
                out.println("The scanner was closed before this was executed!");
                return GetValidCharacterInput(new Scanner(System.in)  );
            }
            //handle any other unexpected exceptions
            catch (Exception e) {
                out.println("There was an unhandled exception: " + e.getMessage());
                out.println("Exiting program.");
                exit(-1);
            }
        }
        while (true);
    }

}
