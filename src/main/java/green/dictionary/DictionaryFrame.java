package green.dictionary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.List;

public class DictionaryFrame extends JFrame {

    private EnglishDictionary dictionary = new EnglishDictionary();

    public DictionaryFrame() {
        setSize(800, 600);
        setTitle("English Dictionary");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        setContentPane(main);

        JTextField word = new JTextField();
        main.add(word, BorderLayout.PAGE_START);

        JTextArea definition = new JTextArea();
        definition.setLineWrap(true);
        definition.setWrapStyleWord(true);
        main.add(definition);

        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                List<String> def = dictionary.getDefinition(word.getText());

                if (def == null) {
                    definition.setText("No definition found");
                } else {
                    definition.setText(defToString(def));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        };

        word.getDocument().addDocumentListener(docListener);
    }

    private String defToString(List<String> defs) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < defs.size(); i++) {
            str.append(defs.get(i)).append("\n");
        }
        return str.toString();
    }
}
