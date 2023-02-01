package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The class for the Bye command which extends Command class.
 */
public class ByeCommand extends Command {
    private String input;

    /**
     * ByeCommand constructor.
     *
     * @param input The user's input.
     */
    public ByeCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTaskList(tasks);
        return "    " + "Bye. Hope to see you again soon!";
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
