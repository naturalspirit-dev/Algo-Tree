package cp3.ass01.suffixtrie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuffixTrieNode {

    private Map<Character, SuffixTrieNode> children;
    private List<SuffixIndex> data;

    public SuffixTrieNode() {
        children = new HashMap<>();
        data = new ArrayList<>();
    }

    public void addChild(char c, SuffixTrieNode child) {
        children.put(c, child);
    }

    public List<SuffixIndex> getData() {
        return data;
    }

    public Map<Character, SuffixTrieNode> getChildren() {
        return children;
    }

    public SuffixTrieNode get(char c) {
        return children.get(c);
    }
}
