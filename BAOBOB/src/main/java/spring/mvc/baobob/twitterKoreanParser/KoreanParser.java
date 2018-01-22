package spring.mvc.baobob.twitterKoreanParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer.KoreanToken;

import scala.collection.Seq;
import spring.mvc.baobob.vo.WordVO;

/**
 * @���� ���¼Һм��� �ؼ� word counting ����� �����ϴ� Ŭ����
 * @author ����ǰ
 * @�ۼ����� 2017.02.05
 */
public class KoreanParser {

	public static void main(String[] args) {
		getWordsMap("�����Ŀ�� ���ı�� #�ı� #���ı�� #�ķ����");
		
	}
	
	// �ؽ�Ʈ�� ���¼� �м��ؼ� word map���� �������ִ� �޼���
	public synchronized static List<WordVO> getWordsMap(String text) {
		
		// WordModel�� ������ �ִ� ��ü
		List<WordVO> wordList = new ArrayList<>();
		// �ܾ�� ī��Ʈ�� ������ �ִ� ��
		Map<String, Integer> wordMap = new HashMap<>();
		// �ܾ�� ǰ�縦 ������ �ִ� ��
		Map<String, String> wordTypeMap = new HashMap<>();
		
		// String text to Seq<KoreanTokenizer.KoreanToken>
		Seq<KoreanTokenizer.KoreanToken> tokens = KoreanTextParser.getTokens(text);
		// Seq<KoreanTokenizer.KoreanToken> to Seq<KoreanToken> koreanTokens
		Seq<KoreanToken> koreanTokens = KoreanTextParser.getStemmed(tokens);
		// Seq<KoreanToken> koreanTokens to List<KoreanTokenJava> koreanTokenJava
		List<com.twitter.penguin.korean.KoreanTokenJava> koreanTokenJava = KoreanTextParser.getTokensToJavaKoreanTokenList(koreanTokens);
		
		for(com.twitter.penguin.korean.KoreanTokenJava tokenJava : koreanTokenJava) {
			
			String pos = tokenJava.getPos().toString();
			System.out.println(tokenJava.getText()+" / " + tokenJava.getPos());
			if(pos.equals("Noun") || pos.equals("ProperNoun") || pos.equals("Hashtag") || pos.equals("Verb")) {
				System.out.println("�м� �� �ܾ� : " + tokenJava.toString());
				String word = tokenJava.getText();
				if(wordMap.get(word)==null) {
					wordMap.put(word, 1);
					wordTypeMap.put(word, pos);
				} else {
					wordMap.put(word, wordMap.get(word)+1);
				}
			}
		}
		
		for(Map.Entry<String, Integer> map : wordMap.entrySet()) {
			WordVO dto = new WordVO();
			String word = map.getKey();
			int count = map.getValue();
			String part = wordTypeMap.get(word);
			dto.setWord(word);
			dto.setCount(count);
			dto.setType_of_speech(part);
			wordList.add(dto);
		}
		
		return wordList;
	}
	
}
