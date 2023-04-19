package cp3.lab02.trie;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trie {

    private TrieNode root = new TrieNode();


    /**
     * Inserts a string into the trie and returns the last node that was
     * inserted.
     *
     * @param str The string to insert into the trie
     * @param data	The cp3.lab02.trie.data associated with the string
     * @return The last node that was inserted into the trie
     */
    public TrieNode insert(String str, TrieData data) {
        TrieNode node = root;
        char[] charArr = str.toCharArray();
        for (char ch : charArr) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setData(data);
        node.setTerminal(true); // set the terminal flag to true for the last node
        return node;
    }

    /**
     * Search for a particular prefix in the trie, and return the final node in
     * the path from root to the end of the string, i.e. the node corresponding
     * to the final character. getNode() differs from get() in that getNode()
     * searches for any prefix starting from the root, and returns the node
     * corresponding to the final character of the prefix, whereas get() will
     * search for a whole word only and will return null if it finds the pattern
     * in the trie, but not as a whole word.  A "whole word" is a path in the
     * trie that has an ending node that is a terminal node.
     *
     * @param str The string to search for
     * @return the final node in the path from root to the end of the prefix, or
     * null if prefix is not found
     */
    public TrieNode getNode(String str) {
        TrieNode node = root;
        char[] charArr = str.toCharArray();
        for (char ch : charArr) {
            if (!node.containsKey(ch)) {
                return null;
            }
            node = node.get(ch);
        }
        return node;
    }

    /**
     * Searches for a word in the trie, and returns the final node in the search
     * sequence from the root, i.e. the node corresponding to the final
     * character in the word.
     *
     * getNode() differs from get() in that getNode() searches for any prefix
     * starting from the root, and returns the node corresponding to the final
     * character of the prefix, whereas get() will search for a whole word only
     * and will return null if it finds the pattern in the trie, but not as a
     * whole word. A "whole word" is a path in the
     * trie that has an ending node that is a terminal node.
     *
     * @param str The word to search for
     * @return The node corresponding to the final character in the word, or
     * null if word is not found
     */
    public TrieNode get(String str) {
        TrieNode node = getNode(str);
        if (node != null && !node.isTerminal()) {
            return null;
        }
        return node;
    }

    /**
     * Retrieve from the trie an alphabetically sorted list of all words
     * beginning with a particular prefix.
     *
     * @param prefix The prefix with which all words start.
     * @return The list of words beginning with the prefix, or an empty list if
     * the prefix was not found.
     */
    public List<TrieData> getAlphabeticalListWithPrefix(String prefix) {
        TrieNode node = getNode(prefix);
        List<TrieData> result = new ArrayList<>();
        if (node != null) {
            collectWords(node, new StringBuilder(prefix), result);
        }
        return result;
    }

    private void collectWords(TrieNode node, StringBuilder prefix, List<TrieData> result) {
        if (node.isTerminal()) {
            TrieData data = new TrieData(prefix.toString(), node.getData().getFrequency());
            result.add(data);
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            TrieNode child = node.get(ch);
            if (child != null) {
                prefix.append(ch);
                collectWords(child, prefix, result);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }



    /**
             * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Finds the most frequently
             * occurring word represented in the trie (according to the dictionary file)
             * that begins with the provided prefix.
             *
             * @param prefix The prefix to search for
             * @return The most frequent word that starts with prefix
             */
    public String getMostFrequentWordWithPrefix(String prefix) {
        List<TrieData> result = getAlphabeticalListWithPrefix(prefix);
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getFrequency() > max) {
                max = result.get(i).getFrequency();
                maxIndex = i;
            }
        }
        return result.get(maxIndex).getWord();
    }

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Reads in a dictionary from file
     * and places all words into the trie.
     *
     * @param fileName the file to read from
     * @return the trie containing all the words
     */
    public static Trie readInDictionary(String fileName) {
        fileName = System.getProperty("user.dir") + "/" + fileName;
        Trie trie = new Trie();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    String word = parts[1].toLowerCase();
                    int frequency = Integer.parseInt(parts[2]);
                    trie.insert(word, new TrieData(word, frequency));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trie;
    }
}
