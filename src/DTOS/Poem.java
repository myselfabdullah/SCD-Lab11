package DTOS;

import java.util.List;

public class Poem {
    private String verse;
    private List<String> roots;

    public Poem(String verse, List<String> roots) {
        this.verse = verse;
        this.roots = roots;
    }

    public String getVerse() {
        return verse;
    }

    public List<String> getRoots() {
        return roots;
    }
}
