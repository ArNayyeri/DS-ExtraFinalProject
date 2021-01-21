import java.util.ArrayList;

public class TrieNode {
    public ArrayList<TrieNode> children = new ArrayList<>();
    public char c;
    public boolean isWord = false;

    public TrieNode() {
    }

    public TrieNode(char c, boolean isWord) {
        this.c = c;
        this.isWord = isWord;
    }

    public TrieNode getChild(char c) {
        for (TrieNode i : children)
            if (i.c == c)
                return i;
        return null;
    }
}
