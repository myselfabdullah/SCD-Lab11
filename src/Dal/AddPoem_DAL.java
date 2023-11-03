package Dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPoem_DAL {

	public List<String> add_poem(File file_path) throws IOException {

		String readl;
		List <String>verses = new ArrayList<String>();
		BufferedReader read = null;
		try {
			read = new BufferedReader(new FileReader("Poem.txt"));
			while ((readl = read.readLine()) != null) {
				verses.add(readl);
				System.out.println(readl);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {
				read.close();
			}
		}
		return verses;
	}

	public static void main(String[] args) throws IOException {
		AddPoem_DAL obj = new AddPoem_DAL();
		System.out.println(obj.add_poem(null));

	}

}
