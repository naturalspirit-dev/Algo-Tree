package cp3.lab02.trie;

public class TrieData {

    private int frequency = -1;
    private String word = null;

    /**
     * Construct a new TrieData with the given frequency
     * @param frequency the frequency of the cp3.lab02.trie.data associated with the TrieNode
     */
    public TrieData(String word, int frequency) {
        this.frequency = frequency;
        this.word = word;
    }

    /**
     * Gets the frequency of this TrieData.
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency of this TrieData
     * @param frequency the frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    public String getWord() {
        return this.word;
    }




    @Override
    public String toString() {
        return String.valueOf(frequency);
    }
}
