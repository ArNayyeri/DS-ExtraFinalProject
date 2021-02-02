import java.util.ArrayList;

public class SearchClass {

    public String search(String text, TrieTree tree) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        String words[] = new String[tree.numberWords];
        for (int i = 0; i < tree.numberWords; i++) {
            words[i] = null;
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < text.length(); i++) {
            if (getNonLetter(text, i) == -1) {
                String a = text.substring(i);
                if (tree.contain(a)) {
                    words[tree.getIndex(a)] = a.toLowerCase();
                    list.get(tree.getIndex(a)).add(i);
                }
                break;
            } else {
                String a = text.substring(i, getNonLetter(text, i));
                if (tree.contain(a)) {
                    words[tree.getIndex(a)] = a.toLowerCase();
                    list.get(tree.getIndex(a)).add(i);
                }
                i = getNonLetter(text, i);
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
        return output;
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
}
