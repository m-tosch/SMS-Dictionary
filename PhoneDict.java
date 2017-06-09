import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PhoneDict{
	/* Dictionary!
	 * key: sequence code   values: words associated with it 
	 * because it is possible, that multiple values are associated
	 * with the same key, an ArrayList of Strings was chosen for the value*/
	private HashMap<String, ArrayList<String>> map;
	
	// read all words from input file when creating new instance
	public PhoneDict(File file){
		readWords(file);
	}
	
	/** decode a given sequence into a string (sentence)
	 * - if multiple words are possible for a sub-sequence,
	 *   two or more words are displayed as follows: (word1|word2)
	 * - if no word could be matched against a sub-sequence,
	 *   this word will be displayed as **** with as many *
	 *   as the length of the corresponding sub-sequence
	 * @param s the complete unstriped sequence
	 * @return the sentence */
	public String decodeSequence(String seq){
		// remove all unwanted whitespaces (zeros)
		seq = stripSequence(seq);
		// retrieve all sub sequences
		String[] sequences = seq.split("0");
		// return value. assembled sentence
		String sent = "";
		// iterate over all subsequences and match against words
		for(int i = 0; i < sequences.length; i++){
			// get the words to the sequence
			ArrayList<String> words = map.get(sequences[i]);
			String tmp = "";
			if(words == null){
				// add as many * as the sequence is long
				for(int j = 0; j < sequences[i].length(); j++){
					sent = sent.concat("*");
				}
			}else if(words.size() == 1){
				// just add the plain word
				tmp = tmp.concat(words.get(0));
			}else{
				// special formatting: (word1|word2)
				tmp = "(";
				for(String word : words){
					tmp = tmp.concat(word+"|");
				}
				tmp = tmp.substring(0, tmp.length()-1).concat(")");
			}
			// concatenate matched or unmatched word(s) to sentence
			sent = sent.concat(tmp);
			// add whitespace if necessary
			if(i < sequences.length - 1){
				sent = sent.concat(" ");
			}
		}
		return sent;
	}
	
	/** uses regular expressions to remove multiple zeros
	 * in sequence and leading and trailing zeros
	 * @param seq the sequence 
	 * @return the stripped sequence*/
	private String stripSequence(String seq){
		// replace one or more occurrences of 0 with single 0
		seq = seq.replaceAll("0+", "0");
		// remove leading and trailing 0
		seq = seq.replaceAll("^0|0$", "");
		return seq;
	}
	
	/** reads words from a file and saves them in words ArrayList
	 * Assumes that every word is on a separate line
	 * @param file the input file */
	private void readWords(File file){
		BufferedReader br = null;
		map = new HashMap<String, ArrayList<String>>();
		try{
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = br.readLine()) != null){
				// get the sequence code for the current word
				String seqCode = getSequenceCode(line);
				/* put the sequence code as the key in the HashMap
				 * associate word (line) with it */
				ArrayList<String> current;
				current = map.containsKey(seqCode) ? map.get(seqCode) : new ArrayList<String>();
				current.add(line);
				// put key and value in hash map
				map.put(seqCode, current);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/** Returns the corresponding code for a string
	 * @param s input sequence string
	 * @return the sequence code */
	private String getSequenceCode(String s){
		String code = "";
		for(int i = 0; i < s.length(); i++){
			code = code.concat(""+getCode(""+s.charAt(i)));
		}
		return code;
	}
	
	/** takes an input string as a single character!
	 * and returns the corresponding code as a short
	 * either 0 or 2-9 (both inclusive)
	 * @param c input string
	 * @return the code*/
	private short getCode(String c){
		if("abc".contains(c.toLowerCase())) return 2;
		if("def".contains(c.toLowerCase())) return 3;
		if("ghi".contains(c.toLowerCase())) return 4;
		if("jkl".contains(c.toLowerCase())) return 5;
		if("mno".contains(c.toLowerCase())) return 6;
		if("pqrs".contains(c.toLowerCase())) return 7;
		if("tuv".contains(c.toLowerCase())) return 8;
		if("wxyz".contains(c.toLowerCase())) return 9;
		return 0;
	}
	
}
