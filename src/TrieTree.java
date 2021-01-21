public class TrieTree {
    public TrieNode root;

    public TrieTree() {
    }

    public TrieTree(TrieNode root) {
        this.root = root;
    }

    public void insertWord(String word) {
        word = word.toUpperCase();
        TrieNode t = root;
        for (int i = 0; i < word.length(); i++)
            if (t.getChild(word.charAt(i)) != null)
                t = t.getChild(word.charAt(i));
            else
                for (; i < word.length(); i++) {
                    TrieNode tt = new TrieNode(word.charAt(i), false);
                    t.children.add(tt);
                    t = tt;
                }
        t.isWord = true;
    }

    public void removeWord(String word) {
        word = word.toUpperCase();
        TrieNode t = root, tt = root;
        int max = 0;
        for (int i = 0; i < word.length(); i++) {
            if (t.children.size() > 1 || t.isWord) {
                max = i;
                tt = t;
            }
            t = t.getChild(word.charAt(i));
        }
        if (!t.children.isEmpty()) {
            t.isWord = false;
            return;
        }
        tt.children.remove(tt.getChild(word.charAt(max)));
    }

    public boolean contain(String word) {
        word = word.toUpperCase();
        TrieNode t = root;
        for (int i = 0; i < word.length(); i++) {
            t = t.getChild(word.charAt(i));
            if (t == null)
                return false;
        }
        return t.isWord;
    }
}
