package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;



public class MarkCommandTest {
    @Test
    public void executeTest() throws DukeException {
        TaskList tl = new TaskList();
        Storage storage = new Storage("taskListTest.txt");
        Deadline d = new Deadline("homework",
                "2024-10-20 2359");
        tl.add(d);

        MarkCommand markCommand1 = new MarkCommand("mark 1");
        MarkCommand markCommand2 = new MarkCommand("mark ");
        MarkCommand markCommand3 = new MarkCommand("mark -1");

        assertEquals(markCommand1.execute(tl, storage),
                "Nice! I've marked this task as done:\n"
                        + "  [D] [ X ]  homework\n"
                        + " (by: Oct 20 2024 11:59 PM)\n");
        try {
            markCommand2.execute(tl, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "Command must be followed by an integer.");
        }
        try {
            markCommand3.execute(tl, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "There are insufficient tasks.");
        }

    }
}
