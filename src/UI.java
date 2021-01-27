/**
 * Sample Skeleton for 'UI.fxml' Controller Class
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UI {

    private TrieTree tree;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="text"
    private TextArea text; // Value injected by FXMLLoader

    @FXML // fx:id="insert_text"
    private TextField word; // Value injected by FXMLLoader

    @FXML // fx:id="result"
    private Label result; // Value injected by FXMLLoader

    @FXML
    void check(ActionEvent event) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        String words[] = new String[tree.numberWords];
        for (int i = 0; i < tree.numberWords; i++) {
            words[i] = null;
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < text.getText().length(); i++) {
            if (getNonLetter(text.getText(), i) == -1) {
                String a = text.getText().substring(i);
                if (tree.contain(a)) {
                    words[tree.getIndex(a)] = a.toLowerCase();
                    list.get(tree.getIndex(a)).add(i);
                }
                break;
            } else {
                String a = text.getText().substring(i, getNonLetter(text.getText(), i));
                if (tree.contain(a)) {
                    words[tree.getIndex(a)] = a.toLowerCase();
                    list.get(tree.getIndex(a)).add(i);
                }
                i = getNonLetter(text.getText(), i);
            }
        }
        String output = "";
        for (int i = 0; i < tree.numberWords; i++) {
            if (words[i] == null)
                continue;
            output += words[i] + " : ";
            boolean first = true;
            for (int j : list.get(i)) {
                if (first) {
                    output += String.valueOf(j);
                    first = false;
                } else
                    output += ", " + String.valueOf(j);
            }
            output += "\n";
        }
        result.setText(output);
    }

    private int getNonLetter(String text, int pos) {
        int min = text.length();
        for (char i = 0; i < 256; i++) {
            if (i >= 'A' && i <= 'Z')
                continue;
            if (i >= 'a' && i < 'z')
                continue;
            int a = text.indexOf(i, pos);
            if (a != -1 && a < min) {
                min = a;
            }
        }
        if (min == text.length())
            return -1;
        return min;
    }

    @FXML
    void insert_button(ActionEvent event) {
        tree.insertWord(word.getText(), tree.numberWords);
    }

    @FXML
    void remove_button(ActionEvent event) {
        tree.removeWord(word.getText());
    }

    @FXML
    void reset(ActionEvent event) {
        tree = new TrieTree(new TrieNode());
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'UI.fxml'.";
        assert word != null : "fx:id=\"insert_text\" was not injected: check your FXML file 'UI.fxml'.";
        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'UI.fxml'.";
        tree = new TrieTree(new TrieNode());
    }
}
