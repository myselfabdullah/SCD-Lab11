package Dal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DTOS.Poem;

public class RootDal {

    public List<Poem> processPoems(File file) throws IOException {
        AddPoem_DAL addPoemDal = new AddPoem_DAL();
        Tokenize tokenizer = new Tokenize();

        List<String> verses = addPoemDal.add_poem(file);
        List<Poem> poems = new ArrayList<>();

        for (String verse : verses) {
            List<String> roots = tokenizer.tokenizePoem(verse);
            Poem poem = new Poem(verse, roots);
            poems.add(poem);
        }

        return poems;
    }

    public static void main(String[] args) throws IOException {
        File poemFile = new File("Poem.txt");
        RootDal poemProcessor = new RootDal();
        List<Poem> poems = poemProcessor.processPoems(poemFile);

        for (Poem poem : poems) {
            System.out.println("Verse: " + poem.getVerse());
            System.out.println("Roots: " + poem.getRoots());
            System.out.println();
        }
    }
}
