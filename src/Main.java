import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {
    ArrayList<Task> tasklist = new ArrayList<>();
    private ArrayList<String> todoList = new ArrayList<>();
    private JComboBox<String> taskBoxes;
    private JComboBox<String> removeOptions;
    private DefaultComboBoxModel<String> taskModel;

    public static void main(String[] args ) {
        Main display = new Main();
        display.setVisible(true);
    }

    public Main() {
        setTitle("To Do list");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        taskModel = new DefaultComboBoxModel<>();
        taskBoxes = new JComboBox<>(taskModel);

        JButton addTask = new JButton("Add task");
        JButton removeTask = new JButton("Remove task");
        JButton customizeList = new JButton("Customize List");
        JButton saveList = new JButton("Save list");

        JPanel west = new JPanel();
        west.setLayout(new GridLayout(4, 1));

        west.add(addTask);
        west.add(removeTask);
        west.add(customizeList);
        west.add(saveList);

        add(west, BorderLayout.WEST);

        addTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // enter task and due date
                String input = JOptionPane.showInputDialog("Enter a task: ");
                String date = JOptionPane.showInputDialog("Enter due date or QUIT to quit: ");
                // create task object and add to list
                Task task = new Task(input, date);
                todoList.add(input);
                tasklist.add(task);

                int count = 0;

                // loop doing the above until user quits
                while(!input.equalsIgnoreCase("QUIT")) {
                    input = JOptionPane.showInputDialog("Enter a task or QUIT to quit: ");
                    if(input.equalsIgnoreCase("QUIT")) {
                        count++;
                        break;
                    }
                    date = JOptionPane.showInputDialog("Enter due date or QUIT to quit: ");
                    if(date.equalsIgnoreCase("QUIT")) {
                        count++;
                        break;
                    }
                    // add user input to list
                    tasklist.add(new Task(input, date));
                    todoList.add(input);
                    updateTaskList();

                    //increment counter
                    count++;
                }

                // create jpanel for todo list
                JPanel east = new JPanel();
                east.setLayout(new GridLayout(count+1, 1));

                for(int i = 0; i < count; i++) {
                    // loop and make labels for each task
                    east.add(new JCheckBox(tasklist.get(i).getName() + ": " + tasklist.get(i).getDate()));
                    revalidate();
                }

                add(east, BorderLayout.CENTER);
                revalidate();

            }

        });

        removeTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskBoxes.getSelectedIndex();
                if (selectedIndex != -1) {
                    tasklist.remove(selectedIndex);
                    updateTaskList();
                }
            }
        });

        customizeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //customize?tbd
            }
        });

        saveList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTodoList();
            }
        });

        loadTodoList();
        updateTaskList();
    }

    private void updateTaskList() {
        taskModel.removeAllElements();
        for (String task : todoList) {
            taskModel.addElement(String.valueOf(task));
        }
    }

    private void loadTodoList() {
        try (BufferedReader reader = new BufferedReader(new FileReader("todo_list.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                todoList.add(line);
            }
        } catch (IOException e) {
        }
    }

    private void saveTodoList() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("todo_list.txt"))) {
            for (String task : todoList) {
                writer.write(task);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(Main.this, "To-do list saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(Main.this, "Failed to save to-do list.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
