package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class PsiLingTurk {

	public static Map<String, String> words;

	public static Map<String, Double> freqs;

	public static Scanner scanner;

	public static PrintStream sysout; 
	private static Locale turkishLocale = new Locale("tr", "TR");
	
	public static Map<String, String> getWordList()
	{
		return words;
	}
	
	public static Map<String, Double> getFreqList()
	{
		return freqs;
	}

	public static void main(String[] args) throws IOException {

		readWords();
		readFreqs();
		writeWords();

//		String query = "";
//
//		while(true)
//		{
//			sysout.print("Please enter the word you want to query (exit to exit): ");
//			//Console c = System.console();
//			query = scanner.nextLine(); //c.readLine();
//			query = query.toLowerCase(turkishLocale);
//
//			if(query.equalsIgnoreCase("exit"))
//				break;
//
//			// return its type
//			sysout.println("Type: " + words.get(query));
//
//			// print out bigrams
//			sysout.println("Bigrams");
//			List<String> bigrams = getBigrams(query);
//
//			int bigramFreq[] = getBigramFreq(query, bigrams);
//
//			int totalBigramFreq[] = getTotalBigramFreq(query, bigrams);
//
//			for (int i = 0; i < bigrams.size(); i++) 
//			{
//				sysout.println(bigrams.get(i)+": " + bigramFreq[i] + "/" + totalBigramFreq[i]);
//			}
//
//			// print out bigrams
//			sysout.println("Trigrams");
//			List<String> trigrams = getTrigrams(query);
//			int trigramFreq[] = getTrigramFreq(query, trigrams);
//			int totalTrigramFreq[] = getTotalTrigramFreq(query, trigrams);
//			for (int i = 0; i < trigrams.size(); i++) 
//			{
//				sysout.println(trigrams.get(i)+": " + trigramFreq[i]+ "/" + totalTrigramFreq[i]);
//			}
//
//			// print out orthographic
//			sysout.println("Orthographic Neighbors");
//			List<String> orthoList = getOrthographic(query);
//			for (String word: orthoList) 
//			{
//				sysout.println(word);
//
//			}
//
//			// print out subsets
//			sysout.println("Subsets");
//			List<String> subsets = getSubsets(query);
//			for (String word: subsets) 
//			{
//				sysout.println(word);
//
//			}
//
//			// print out supersets
//			sysout.println("Supersets");
//			List<String> supersets = getSupersets(query);
//			for (String word: supersets) 
//			{
//				sysout.println(word);
//
//			}
//			
//			// calculate old20
//			sysout.println("Old20");
//			sysout.println(calculateOld20(query));
//		}

	}
	
	
	public static double calculateOld20(String query)
	{
		ArrayList<Integer> laven = new ArrayList<Integer>();
		for (String word: words.keySet()) 
		{
			if( !query.equalsIgnoreCase(word))
				laven.add(computeLevenshteinDistance(query, word));
		}
		java.util.Collections.sort(laven);
		double average = 0;
		for(int i = 0; i < 20; i++)
			average += laven.get(i);
		
		return average / 20;
	}

	public static String getType(String query) 
	{
		return words.get(query);
	}




	public static void readWords() throws IOException 
	{
		Collator collator = Collator.getInstance(Locale.forLanguageTag("tr-TR"));
		collator.setStrength(Collator.PRIMARY);

		words = new TreeMap<String, String>(collator);
		scanner = new Scanner(System.in, "UTF-8");
		sysout = new PrintStream(System.out, true, "UTF-8");

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						PsiLingTurk.class.getResourceAsStream("freqs_corrected.txt"), "UTF-8"));
		
		
		String line = null;
		String word = null;
		String type = null;
		
		while ((line=reader.readLine()) != null)
		{
			if(line.length()>0 )
			{
				String[] lineWords = line.split(" ");

				word = lineWords[0].split("\\[")[0].toLowerCase(turkishLocale);
				type = lineWords[0].substring(lineWords[0].indexOf("[") + 1, lineWords[0].indexOf("]"));
				if(words.get(word) == null )
					words.put(word, type);
			}
		} 

		reader.close();
	}
	
	public static void writeWords() throws IOException
	{
		BufferedWriter writer = Files.newBufferedWriter(
								Paths.get("freqs_corrected_all.txt"), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		for (Map.Entry<String, String> entry : words.entrySet()) {
			String word = entry.getKey();
			writer.write(word + " " + entry.getValue() + " " + freqs.get(word) + " " + getOrthographic(word).size() + " " + calculateOld20(word) + "\n");
		}
		writer.close();
		
		
	}

	public static void readFreqs() throws IOException 
	{
		Collator collator = Collator.getInstance(Locale.forLanguageTag("tr-TR"));
		collator.setStrength(Collator.PRIMARY);
		freqs = new TreeMap<String, Double>(collator);

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						PsiLingTurk.class.getResourceAsStream("freqs_corrected.txt"), "UTF-8"));

		String line = null;
		String word = null;
		Double freq = null;


		while ((line=reader.readLine()) != null)
		{
			if(line.length()>0 )
			{
				String[] words = line.split(" ");

				word = words[0].split("\\[")[0];
				freq = Double.parseDouble(words[1]);

				freqs.put(word, freq);
			}
		} 

		reader.close();
	}

	public static Double getFreq(String query) 

	{
		return freqs.get(query);
	}

	public static List<String> getBigrams(String query) 
	{
		ArrayList<String> bigrams = new ArrayList<String>();


		for(int i = 0; i < query.length() - 1; i++)
		{
			bigrams.add(query.substring(i,i+2));
		}

		return bigrams;
	}

	public static int[] getBigramFreq(String query, List<String> bigrams) {

		// initialize frequency array
		int freq[] = new int[bigrams.size()];
		for (int i = 0; i<freq.length; i++) 
		{

			freq[i] = 0;

		}

		for (String word: words.keySet()) 
		{
			// check word has same amount of characters 
			// and it is not the same word
			if( query.length()==word.length() && !query.equals(word) ) 
			{

				List<String> wordBigrams = getBigrams(word);

				for (int i = 0; i < bigrams.size(); i++) 
				{

					if(bigrams.get(i).equals(wordBigrams.get(i)))
						freq[i] = freq[i] +  1;
				}


			}
		}


		return freq;
	}

	public static int[] getTotalBigramFreq(String query, List<String> bigrams) {

		// initialize frequency array
		int freq[] = new int[bigrams.size()];
		for (int i = 0; i < freq.length; i++) 
		{
			freq[i] = 0;
		}

		for (String word: words.keySet()) 
		{
			// check word has same amount of characters 
			// and it is not the same word
			if( query.length() == word.length() && !query.equals(word) ) 
			{
				List<String> wordBigrams = getBigrams(word);
				for (int i = 0; i < bigrams.size(); i++) 
				{
					for (String bigram : wordBigrams) 

					{
						if(bigrams.get(i).equals(bigram)) 
						{
							freq[i] = freq[i]  + 1;

						}
					}
				}
			}
		}

		return freq;
	}

	public static List<String> getTrigrams(String query) 
	{
		ArrayList<String> trigrams = new ArrayList<String>();
		for(int i = 0; i < query.length() - 2; i++) 
		{
			trigrams.add(query.substring(i,i+3));

		}


		return trigrams;
	}

	public static int[] getTrigramFreq(String query, List<String> trigrams) 
	{

		// initialize frequency array
		int freq[] = new int[trigrams.size()];

		for (int i = 0; i < freq.length; i++)
		{

			freq[i] = 0;
		}

		for (String word: words.keySet()) 
		{
			// check word has same amount of characters 
			// and it is not the same word
			if( query.length()==word.length() && !query.equals(word) ) 
			{
				List<String> wordBigrams = getTrigrams(word);


				for (int i = 0; i < trigrams.size(); i++) 
				{
					if(trigrams.get(i).equals(wordBigrams.get(i)))
						freq[i] = freq[i] + 1;
				}
			}

		}


		return freq;
	}

	public static int[] getTotalTrigramFreq(String query, List<String> trigrams) 
	{

		// initialize frequency array
		int freq[] = new int[trigrams.size()];
		for (int i = 0; i < freq.length; i++) 
		{
			freq[i] = 0;
		}

		for (String word: words.keySet()) 
		{
			// check word has same amount of characters 
			// and it is not the same word

			if( query.length() == word.length() && !query.equals(word) ) 
			{
				List<String> wordTrigrams = getTrigrams(word);

				for (int i = 0; i < trigrams.size(); i++) 
				{
					for (String trigram : wordTrigrams) 
					{
						if(trigrams.get(i).equals(trigram))
							freq[i] = freq[i] +  1;

					}

				}

			}
		}


		return freq;
	}


	public static List<String> getOrthographic(String query) 
	{
		List<String> orthoList = new ArrayList<String>();

		// find all one character difference ortoghraphic words
		// eg. elma alma
		for (String word: words.keySet()) 
		{

			// check word has same amount of characters 
			// and it is not the same word

			int diff;

			if( query.length() == word.length() && !query.equals(word) ) 
			{
				diff = 0;
				for(int i = 0; i < query.length(); i++) 
				{

					if(query.charAt(i) != word.charAt(i))
					{
						diff++;
					}

					if(diff == 2)
					{
						break;
					}
				}

				if( diff == 1) 
				{
					orthoList.add(word);
				}
			}
		}
		return orthoList;
	}

	public static List<String> getTransposed(String query) 
	{

		List<String> transposedList = new ArrayList<String>();

		// find transposed letter orthographic
		// eg. sene esne

		String swappedQuery;
		char [] charArray;
		for( int i = 0; i < query.length() - 1; i++) 
		{

			// swap ith and (i+1)th character
			charArray = query.toCharArray();
			charArray[i] = query.charAt(i+1);
			charArray[i+1] = query.charAt(i);


			swappedQuery = new String(charArray);
			if(!swappedQuery.equals(query) && words.get(swappedQuery) != null )
			{
				transposedList.add(swappedQuery);

			}

		}

		return transposedList;

	}

	public static List<String> getSubsets(String query) 
	{
		List<String> subsets = new ArrayList<String>();

		if(!query.equals("")) 
		{
			for (String word: words.keySet()) 
			{
				if (!query.equals(word) && query.matches(".*" + word + ".*"))
				{
					subsets.add(word);
				}

			}


		}


		return subsets;
	}

	public static List<String> getSupersets(String query) 
	{
		List<String> supersets = new ArrayList<String>();
		if(!query.equals("")) 
		{
			for (String word: words.keySet()) 
			{
				if (!query.equals(word) && word.matches(".*" + query + ".*"))

				{
					supersets.add(word);

				}
			}
		}


		return supersets;
	}
	private static int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public static int computeLevenshteinDistance(String str1,
			String str2) {
		int[][] distance = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i <= str1.length(); i++)
			distance[i][0] = i;
		for (int j = 1; j <= str2.length(); j++)
			distance[0][j] = j;

		for (int i = 1; i <= str1.length(); i++)
			for (int j = 1; j <= str2.length(); j++)
				distance[i][j] = minimum(
						distance[i - 1][j] + 1,
						distance[i][j - 1] + 1,
						distance[i - 1][j - 1]
								+ ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
										: 1));

		return distance[str1.length()][str2.length()];
	}
}
