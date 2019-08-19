import java.util.Scanner;

/**
 * Class that handles user input and messages shown to user of this application.
 */
public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?\n";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message to the user, which happens at startup.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Prints a bye message to the user, which happens upon exit.
     */
    public void showBye() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Scans the next line from standard input, returning a String containing the user input
     * with leading and trailing whitespaces removed.
     *
     * @return The String corresponding to the user input.
     */
    public String readCommand() {
        String line = scanner.nextLine().strip();
        System.out.println("\n" + line);
        return line;
    }
}
