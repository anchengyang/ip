package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;


public class TodoCommandTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        Storage storage = new Storage("taskListTest.txt");
        TodoCommand tdc = new TodoCommand("todo test");

        assertEquals(tdc.execute(tl, storage),
                "Got it. I've added this task:\n"
                        + "  [T] [ ] test\n"
                        + "Now you have 1 tasks in the list.");
    }
}
