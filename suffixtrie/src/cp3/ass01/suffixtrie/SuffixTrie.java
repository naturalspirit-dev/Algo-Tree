package cp3.ass01.suffixtrie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class SuffixTrie {

    private SuffixTrieNode root = new SuffixTrieNode();

    /**
     * Insert a String into the suffix trie. For the assignment the string str is
     * a sentence from the given text file.
     *
     * @param str           the sentence to insert
     * @param startPosition the starting index/position of the sentence
     * @return the final node inserted
     */
    public SuffixTrieNode insert(String str, int startPosition) {
        SuffixTrieNode node = root;
        for (char c : str.toCharArray()) {
            SuffixTrieNode child = node.get(c);
            if (child == null) {
                child = new SuffixTrieNode();
                node.addChild(c, child);
            }
            node = child;
        }
        // Attach the start position of the original string to the node
        SuffixIndex index = new SuffixIndex(startPosition, 0);
        node.getData().add(index);

        // Attach the start positions of all the suffixes to their corresponding nodes
        String suffix = str;
        for (int i = 1; i < str.length(); i++) {
            suffix = suffix.substring(1);
            node = root;
            for (char c : suffix.toCharArray()) {
                SuffixTrieNode child = node.get(c);
                if (child == null) {
                    child = new SuffixTrieNode();
                    node.addChild(c, child);
                }
                node = child;
            }
            // Attach the start position of the suffix to the node
            index = new SuffixIndex(startPosition, i);
            node.getData().add(index);
        }
        return node;
    }

    /**
     * Get the suffix trie node associated with the given (sub)string.
     *
     * @param str the (sub)string to search for
     * @return the final node in the (sub)string
     */
    public SuffixTrieNode get(String str) {
        SuffixTrieNode node = root;
        for (char c : str.toCharArray()) {
            SuffixTrieNode child = node.get(c);
            if (child == null) {
                return null;
            }
            node = child;
        }
        return node;
    }


    public static SuffixTrie readInFromFile(String fileName) {
        SuffixTrie trie = new SuffixTrie();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int sentenceIndex = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] lines = line.split("[.!?]+\\s*");
                for (int i = 0; i < lines.length; i++) {
                    trie.insert(lines[i], sentenceIndex++);
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + fileName);
            e.printStackTrace();
        }

        return trie;
    }
}