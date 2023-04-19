package cp3.lab02.trie;

import java.util.Scanner;

/**
 *
 * @author lewi0146
 */
public class TrieDriver {

    private static Trie dictionaryTrie = null;

    public static void main(String[] args) {
        System.out.println("Please input:");

        Scanner in = new Scanner(System.in);
        String inputData = in.nextLine();

        int testLevel = Integer.parseInt(inputData.trim().split(" ")[0]);
        System.out.print(testLevel);


        switch (testLevel) {
            case 1:
                test1(in);
                break;
            case 2:
                test2(in);
                break;
            case 3:
                test3(in);
                break;
            case 4:
                test4(inputData);
                break;
            case 5:
                test5(inputData);
                break;
        }

    }

    /**
     * Test out the basic TrieNode implementation. Expect a list on standard in
     * of three children nodes and their cp3.lab02.trie.data (ints), and another character that
     * is not one of the child nodes e.g. a 12 b 12 c 45 z
     */
    public static void test1(Scanner in) {

        TrieNode p = new TrieNode();
        TrieNode c1 = new TrieNode();
        TrieNode c2 = new TrieNode();
        TrieNode c3 = new TrieNode();

        // get cp3.lab02.trie.data from standard in
        char c1label = in.next().charAt(0);
        int c1data = in.nextInt();
        char c2label = in.next().charAt(0);
        int c2data = in.nextInt();
        char c3label = in.next().charAt(0);
        int c3data = in.nextInt();

        char randomlabel = in.next().charAt(0);
        String tmp="I love you";

        c1.addData(new TrieData(tmp, c1data));
        c2.addData(new TrieData(tmp, c2data));
        c3.addData(new TrieData(tmp, c3data));
        p.addChild(c1label, c1);
        p.addChild(c2label, c2);
        p.addChild(c3label, c3);

        TrieNode w = p.getChild(c1label);
        TrieNode x = p.getChild(c2label);
        TrieNode y = p.getChild(randomlabel);
        TrieNode z = c1.getChild(randomlabel);

        System.out.println("p is " + p);
        System.out.println("Child " + c1label + " of p is " + w);
        System.out.println("Child " + c2label + " of p is " + x);
        System.out.println("Child " + randomlabel + " of p is " + y);
        System.out.println("Child " + randomlabel + " of " + c1label + " is " + z);

    }

    /**
     * Test out the basic Trie implementation. Expect a list on standard in of
     * the words and the cp3.lab02.trie.data to be inserted and then a second line that
     * searches for words in the Trie. The first search uses get (so should only
     * return for whole words) and the second search use getNode (so should
     * return nodes even if they are not whole words).
     *
     * For example
     *
     * abacus 12 abasement 23 abase 43 abroad 32 aloud 23 all 56 allowed 44
     * aba abase abasement abroad aloud broad
     *
     * using get
     * aba: null abase: TrieNode; isTerminal=true, cp3.lab02.trie.data=43, #children=1
     * abasement: TrieNode; isTerminal=true, cp3.lab02.trie.data=23, #children=0
     * abroad: TrieNode; isTerminal=true, cp3.lab02.trie.data=32, #children=0
     * aloud: TrieNode; isTerminal=true, cp3.lab02.trie.data=23, #children=0
     * broad: null
     *
     * using getNode
     * aba: TrieNode; isTerminal=false, cp3.lab02.trie.data=null, #children=2
     * abase: TrieNode; isTerminal=true, cp3.lab02.trie.data=43, #children=1
     * abasement: TrieNode; isTerminal=true, cp3.lab02.trie.data=23, #children=0
     * abroad: TrieNode; isTerminal=true, cp3.lab02.trie.data=32, #children=0
     * aloud: TrieNode; isTerminal=true, cp3.lab02.trie.data=23, #children=0
     * broad: null
     */
    public static void test2(Scanner in) {


        // parse the first line
        String[] ss = in.nextLine().trim().split(" ");
        Trie t = new Trie();
        for (int si = 0; si < ss.length; si += 2) {
            t.insert(ss[si], new TrieData("I love you", Integer.parseInt(ss[si + 1])));
        }

        // parse the second line
        String[] ss2 = in.nextLine().trim().split(" ");
        System.out.println("using get");
        for (String s : ss2) {
            System.out.println(s + ": " + t.get(s));
        }
        System.out.println();
        System.out.println("using getNode");
        for (String s : ss2) {
            System.out.println(s + ": " + t.getNode(s));
        }
    }

    /**
     *
     * Test out the Trie implementation for prefix search. Expect a list on standard in of
     * the words and the cp3.lab02.trie.data to be inserted and then a second line that
     * searches for prefixes in the Trie.
     *
     * For example
     * abacus 12 abasement 34 abase 56 abroad 67 aloud 3 all 34 allowed 43 allow 32 allowance 11 abroad 12
     * aba all br
     *
     * aba: [abacus, abase, abasement]
     * all: [all, allow, allowance, allowed]
     *
     */
    public static void test3(Scanner in) {

        // parse the first line
        String[] ss = in.nextLine().trim().split(" ");
        Trie t = new Trie();

        for (int si = 0; si < ss.length; si += 2) {
            t.insert(ss[si], new TrieData("I love you", Integer.parseInt(ss[si + 1])));
        }

        // parse the second line
        String[] ss2 = in.nextLine().trim().split(" ");

        for (String s : ss2) {
            System.out.println(s + ": " + t.getAlphabeticalListWithPrefix(s));
        }
        System.out.println();

    }

    public static void test4(String inputData) {
        String[] datas = inputData.trim().split(" ");
        String filename = datas[1];
        dictionaryTrie = Trie.readInDictionary(filename);
        System.out.println("Reading in trie...done\n");

        System.out.println("testing getNode");
        TrieNode node = null;
        for(int i=0; i<datas.length-2;i++) {
            node = dictionaryTrie.getNode(datas[i+2]);
            if (node!=null)
                System.out.println(datas[i+2] + ": " + node.toString());
            else
                System.out.println(datas[i+2] + ": " + "null");
        }

        System.out.println("\ntesting get");

        for(int i=0; i<datas.length-2;i++) {
            node = dictionaryTrie.get(datas[i+2]);
            if (node!=null)
                System.out.println(datas[i+2] + ": " + node.toString());
            else
                System.out.println(datas[i+2] + ": " + "null");
        }
    }

    public static void test5(String inputData) {
        String[] datas = inputData.trim().split(" ");
        String filename = datas[1];
        dictionaryTrie = Trie.readInDictionary(filename);
        System.out.println("Reading in trie...done\n");

        TrieNode node = null;
        for(int i=0; i<datas.length-2;i++) {
            node = dictionaryTrie.getNode(datas[i+2]);
            if (node!=null)
                System.out.println("PREFIX = " + datas[i+2] + "\n" + "Most Frequent Word is = " + dictionaryTrie.getMostFrequentWordWithPrefix(datas[i+2]));
            else
                System.out.println("PREFIX = " + datas[i+2] + "\n" + "Most Frequent Word is " + datas[i+2]);
        }
    }
}
