package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The class for the Mark command which extends Command class.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * MarkCommand constructor.
     *
     * @param input The user's input.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) {
        try {
            if (input.length() <= 5) {
                throw new DukeException("OOPS!!! You are missing the number of the task to be marked.");
            }

            int index = Integer.parseInt(input.substring(5));
            if (index > tasks.size() || index <= 0) {
                throw new DukeException(Ui.insufficientTasksMessage());
            }
            Task task = tasks.get(index - 1);
            task.mark();
            storage.saveTaskList(tasks);
            return "Nice! I've marked this task as done:\n  " + task;
        } catch (DukeException de) {
            return de.getMessage();
        } catch (NumberFormatException nfe) {
            return "OOPS!!! Mark has to be followed by an int.";
        }
    }
}
