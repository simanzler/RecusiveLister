import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecursiveLister extends JFrame {
    JPanel mainPnl;
    JButton startBtn;
    JButton quitBtn;
    JScrollPane scrollPane;
    JTextArea textArea;
    public RecursiveLister() {
        mainPnl = new JPanel(new BorderLayout());
        startBtn = new JButton("Start");
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        quitBtn = new JButton("Quit");

        startBtn.addActionListener(e -> getDirectory());
        quitBtn.addActionListener(e -> System.exit(0));

        mainPnl.add(startBtn, BorderLayout.NORTH);
        mainPnl.add(scrollPane, BorderLayout.CENTER);
        mainPnl.add(quitBtn, BorderLayout.SOUTH);

        add(mainPnl);

        setTitle("Recursive File Lister");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void getDirectory() {
        textArea.setText("");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
            getFiles(chooser.getSelectedFile());
        }
    }

    private void getFiles(File file) {
        File[] list = file.listFiles();
        for (File f: list) {
            if (f.isFile()) {
                textArea.append("File: " + f.getAbsolutePath() + "\n");
            } else if (f.isDirectory()) {
                textArea.append("Directory: " + f.getAbsolutePath() + "\n");
                getFiles(f);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new RecursiveLister();
    }
}