package Dal;

import java.util.ArrayList;
import java.util.List;

public class Tokenize {

	public List<String> tokenizePoem(String poem) {
		List<String> tokenizedVerses = new ArrayList<>();

		String[] verses = poem.split("\n");

		for (String verse : verses) {
			String[] tokens = verse.split(" ");
			for (String token : tokens) {
				tokenizedVerses.add(token);
			}		}

		return tokenizedVerses;
	}

//	public static void main(String[] args) {
//		String poemText = "تسلب العقل الحزين";
//		List<String> result = tokenizePoem(poemText);
//
//		for (String verseTokens : result) {
//			System.out.println(verseTokens);
//		}
//	}
}
