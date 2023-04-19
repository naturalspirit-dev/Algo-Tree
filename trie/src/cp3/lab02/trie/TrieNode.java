package cp3.lab02.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private TrieData data = null;
    private boolean terminal = false;
    private Map<Character, TrieNode> children = new HashMap<>();


    /**
     * Lookup a child node of the current node that is associated with a
     * particular character label.
     *
     * @param label The label to search for
     * @return The child node associated with the provided label
     */
    public TrieNode getChild(char label) {
        return children.get(label);
    }

    /**
     * Add a child node to the current node, and associate it with the provided
     * label.
     *
     * @param label The character label to associate the new child node with
     * @param node The new child node that is to be attached to the current node
     */
    public void addChild(char label, TrieNode node) {
        children.put(label, node);
    }

    /**
     * Add a new cp3.lab02.trie.data object to the node.
     *
     * @param dataObject The cp3.lab02.trie.data object to be added to the node.
     */
    public void addData(TrieData dataObject) {
        data = dataObject;
    }

    /**
     * Methods added
     *
     */

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }


    public boolean isTerminal() {
        return data != null;
    }


    public boolean containsKey(char ch) {
        return children.containsKey(ch);
    }

    public TrieNode get(char ch) {
        return children.get(ch);
    }

    public void put(char ch, TrieNode node) {
        children.put(ch, node);
    }

    public TrieData getData() {
        return data;
    }

    public void setData(TrieData data) {
        this.data = data;
    }


    /**
     * The toString() method for the TrieNode that outputs in the format
     *   TrieNode; isTerminal=[true|false], cp3.lab02.trie.data={toString of cp3.lab02.trie.data}, #children={number of children}
     * for example,
     *   TrieNode; isTerminal=true, cp3.lab02.trie.data=3, #children=1
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrieNode; isTerminal=").append(terminal);
        if (data != null) {
            sb.append(", cp3.lab02.trie.data=").append(data.toString());
        }else{
            sb.append(", cp3.lab02.trie.data=").append("null");
        }
        sb.append(", #children=").append(children.size());
        return sb.toString();
    }
}
