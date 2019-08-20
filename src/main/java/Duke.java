import java.util.ArrayList;

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
        ArrayList<String> list = new ArrayList<>();
        Ui ui = new Ui();
        ui.showWelcome();
        while (true) {
            String command = ui.readCommand();
            if (!command.equals("bye") && !command.equals("list")) {
                list.add(command);
                System.out.println("added: " + command);
            } else if (command.equals("bye")) {
                break;
            } else {
                for (int i = 0; i < list.size(); ++i) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            }
        }
        ui.showBye();
    }
}
