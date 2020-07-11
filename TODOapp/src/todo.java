import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class todo {
    public static void main(String[] args) {
        if (args.length == 0) {
            instructions();
        } else {
            switch (args[0]) {
                case "-l":
                    listTasks();
                    break;
                case "-a":
                    if (args.length == 1) {
                        System.out.println("Unable to check: no task provided.");
                        break;
                    } else {
                        System.out.println("*" + args[1] + "* just added.");
                        String newLine = System.lineSeparator() + args[1];
                        addNewTask(newLine);
                        break;
                    }
                case "-c":
                    if (args.length == 1) {
                        System.out.println("Unable to check: no index provided.");
                        break;
                    }
                    try {
                        Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Unable to check: index is not a number.");
                        break;
                    }
                    if (Integer.parseInt(args[1]) > countTasks()) {
                        System.out.println("Unable to check: index is out of bound.");
                        break;
                    }
                    int numArg2c = Integer.parseInt(args[1]);
                    checkTask(numArg2c - 1);
                    break;
                case "-r":
                    if (args.length == 1) {
                        System.out.println("Unable to remove: no index provided.");
                        break;
                    }
                    try {
                        Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Unable to remove: index is not a number.");
                        break;
                    }
                    if (Integer.parseInt(args[1]) > countTasks()) {
                        System.out.println("Unable to remove: index is out of bound.");
                        break;
                    } else {
                        int numArg2r = Integer.parseInt(args[1]);
                        removeTask(numArg2r - 1);
                    }
                default:
                    System.out.println("Sorry, unsupported argument. Try to be more intelligent.");
                    instructions();
            }
        }
    }


    public static void instructions() {
        System.out.println("Command Line Todo application");
        System.out.println("=============================");
        System.out.println("Command line arguments:");
        System.out.println("    -l   Lists all the tasks");
        System.out.println("    -a   Adds a new task");
        System.out.println("    -r   Removes a task");
        System.out.println("    -c   Completes a task");

    }

    public static void listTasks() {
        try {
            Path path = Paths
                .get("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\tasks.txt");
            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                System.out.println("Hooray! No toodoos for tooday.");
            } else {
                for (int i = 0; i < lines.size(); i++) {
                    int count = i + 1;
                    if (lines.get(i).charAt(0) == '+') {
                        System.out.println(count + " - [X] " + lines.get(i).substring(1));
                    } else {
                        System.out.println(count + " - [ ] " + lines.get(i));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Houston, we have a problem.");
        }
    }

    public static void addNewTask(String newLine) {

        try {
            Path path = Paths
                .get("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\tasks.txt");
            Files.write(path, newLine.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.out.println("Houston, we have a problem.");
        }
    }

    public static void checkTask(int argCount) {
        File oldFile =
            new File("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\tasks.txt");
        File newFile =
            new File("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\temp.txt");
        try {
            Path path = Paths.get("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\tasks.txt");
            newFile.createNewFile();
            List<String> lines = Files.readAllLines(path);
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                if (i != argCount) {
                    temp.add(lines.get(i));
                } else {
                    temp.add('+'+lines.get(i));
                }
            }
            Files.write(Paths
                    .get("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\temp.txt"),
                temp, StandardOpenOption.APPEND);
            oldFile.delete();
            newFile.renameTo(oldFile);

        } catch (IOException e) {
            System.out.println("Houston, we have a problem.");
        }
    }

    public static void removeTask(int argCount) {
        File oldFile =
            new File("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\tasks.txt");
        File newFile =
            new File("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\temp.txt");
        try {
            Path path = Paths
                .get("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\tasks.txt");
            newFile.createNewFile();
            List<String> lines = Files.readAllLines(path);
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                if (i != argCount) {
                    temp.add(lines.get(i));
                }
            }
            Files.write(Paths
                    .get("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\temp.txt"),
                temp, StandardOpenOption.APPEND);
            oldFile.delete();
            newFile.renameTo(oldFile);

        } catch (IOException e) {
            System.out.println("Houston, we have a problem.");
        }
    }

    public static int countTasks() {
        int count = 0;
        try {
            Path path = Paths
                .get("C:\\Users\\Admin\\greenfox\\marekstepan-todo-app\\TODOapp\\src\\tasks.txt");
            List<String> lines = Files.readAllLines(path);
            count = (lines.size() + 1);
        } catch (IOException e) {
            System.out.println("Houston, we have a problem.");
        }
        return(count);
    }
}

