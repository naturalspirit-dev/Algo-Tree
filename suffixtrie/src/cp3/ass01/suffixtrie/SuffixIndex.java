package cp3.ass01.suffixtrie;

public class SuffixIndex {

    int sentence;
    int character;

    public SuffixIndex(int sentence, int character) {
        this.sentence = sentence;
        this.character = character;
    }

    public int getSentence() {
        return sentence;
    }

    public void setSentence(int sentence) {
        this.sentence = sentence;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    /**
     * The sentence and character position in the format sentence.character notation
     * @return the position.
     */
    @Override
    public String toString() {
        return sentence + "." + character;
    }
}
