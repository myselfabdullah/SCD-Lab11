package Pl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import Dal.RootDal;
import DTOS.Poem;

public class AddPoem_PL {
    private JFrame frame;
    private JComboBox<String> verseComboBox;
    private JPanel rootsPanel;

    public AddPoem_PL() {
        frame = new JFrame("Poem Root Extractor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());

        // Create and configure components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel fileLabel = new JLabel("Choose a file:");
        JButton browseButton = new JButton("Browse");
        topPanel.add(fileLabel);
        topPanel.add(browseButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        verseComboBox = new JComboBox<>();
        centerPanel.add(verseComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addRootButton = new JButton("Add Root");
        buttonPanel.add(addRootButton);
        centerPanel.add(buttonPanel);

        rootsPanel = new JPanel();
        rootsPanel.setLayout(new BoxLayout(rootsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(rootsPanel);
        centerPanel.add(scrollPane);

        // Add components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Set button styles
        addRootButton.setBackground(new Color(0, 123, 255));
        addRootButton.setForeground(Color.BLACK);

        // Browse button action listener to choose a file
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        processFile(selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Add Root button action listener to extract and display roots
        addRootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedVerse = (String) verseComboBox.getSelectedItem();
                    if (selectedVerse != null) {
                        displayRoots(selectedVerse);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }

    private void processFile(File file) throws IOException {
        RootDal poemProcessor = new RootDal();
        List<Poem> poems = poemProcessor.processPoems(file);

        verseComboBox.removeAllItems();
        for (Poem poem : poems) {
            verseComboBox.addItem(poem.getVerse());
        }
    }

    private void displayRoots(String selectedVerse) throws IOException {
        RootDal poemProcessor = new RootDal();
        List<Poem> poems = poemProcessor.processPoems(new File("Poem.txt"));

        for (Poem poem : poems) {
            if (poem.getVerse().equals(selectedVerse)) {
                List<String> roots = poem.getRoots();
                rootsPanel.removeAll();
                for (String root : roots) {
                    JTextField rootTextField = new JTextField(root);
                    rootTextField.setEditable(false);
                    rootsPanel.add(rootTextField);
                }
                rootsPanel.revalidate();
                rootsPanel.repaint();
                return;
            }
        }
        rootsPanel.removeAll();
        rootsPanel.revalidate();
        rootsPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Use the system's look and feel for a more native appearance
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new AddPoem_PL();
            }
        });
    }
}
