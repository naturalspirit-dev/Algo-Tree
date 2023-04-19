package cp3.ass01.suffixtrie;

import java.util.ArrayList;

public class SuffixTrieData {

    private ArrayList<SuffixIndex> startIndexes = new ArrayList<>();

    public void addStartIndex(SuffixIndex startIndex) {
        startIndexes.add(startIndex);
    }
    public ArrayList<SuffixIndex> getStartIndex() {
        return startIndexes;
    }

    public String toString() {
        return startIndexes.toString();
    }
}
