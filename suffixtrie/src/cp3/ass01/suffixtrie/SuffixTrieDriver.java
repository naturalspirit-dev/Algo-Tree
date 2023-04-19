package cp3.ass01.suffixtrie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * This is an example class to driver the Suffix Trie project. You can use this a starting point
 * to test your Suffix Trie implementation.
 *
 * It expects user input of the file to processes as the first line and then the subsequent lines are
 * the words/phrases/suffixes to search for with an empty line terminating the user input. For example:
 *
 * java cp3.ass01.suffixtrie.SuffixTrieDriver data/Frank02.txt and the , the onster ngeuhhh ing? This
 *
 * @author lewi0146
 */
public class SuffixTrieDriver {

    private static List<SuffixIndex> result = new ArrayList<>();

    private static void logs(SuffixTrieNode sn) {
        if(sn == null)
            return;
        if (sn.getData() != null && !sn.getData().isEmpty()) {
            result.addAll(0, sn.getData());
        }

        if (!sn.getChildren().isEmpty()) {
            for (Map.Entry<Character, SuffixTrieNode> child : sn.getChildren().entrySet()) {
                logs(child.getValue());
            }
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        fileName = System.getProperty("user.dir") + "/" + fileName;
        Queue<String> ss = new ArrayDeque<>();
        String suffix = in.nextLine();

        while (!suffix.equals("")) {
            ss.offer(suffix);
            suffix = in.nextLine();
        }

        SuffixTrie st = SuffixTrie.readInFromFile(fileName);

        while (!ss.isEmpty()) {
            String s = ss.poll();
            SuffixTrieNode sn = st.get(s);
            result = new ArrayList<>();
            logs(sn);
            Collections.sort(result, Comparator.comparing(ele -> ele.getSentence()));
            System.out.println("[" + s + "]: " + result.toString());
        }
    }
}
