/**
 * The Duke program is a simple Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 *
 * @author  Jefferson111
 */
public class Duke {
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        while (!ui.readCommand().equals("bye")) {
        }
        ui.showBye();
    }
}
