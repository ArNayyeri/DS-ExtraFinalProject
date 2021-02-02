import java.net.URL;
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

    @FXML // fx:id="description"
    private Label description; // Value injected by FXMLLoader

    @FXML
    void check(ActionEvent event) {
        SearchClass searchClass = new SearchClass();
        String search = searchClass.search(text.getText(), tree);
        if (search.equals(""))
            description.setText("No word found!!!");
        result.setText(search);
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
        if (tree.contain(word.getText())) {
            description.setText("Word is exist in the tree!");
        } else if (getNonLetter(word.getText(), 0) != -1 || word.getText().equals("")) {
            description.setText("Word is invalid!");
        } else {
            tree.insertWord(word.getText(), tree.numberWords);
            description.setText("Insert Successfully");
            result.setText("");
        }
    }

    @FXML
    void remove_button(ActionEvent event) {
        if (tree.contain(word.getText())) {
            tree.removeWord(word.getText());
            description.setText("Remove Successfully");
            result.setText("");
        } else {
            description.setText("Word isn't exist in the tree!");
        }
    }

    @FXML
    void reset(ActionEvent event) {
        tree = new TrieTree(new TrieNode());
        description.setText("Reset Successfully");
        result.setText("");
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'UI.fxml'.";
        assert word != null : "fx:id=\"insert_text\" was not injected: check your FXML file 'UI.fxml'.";
        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'UI.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'UI.fxml'.";
        tree = new TrieTree(new TrieNode());
    }
}
