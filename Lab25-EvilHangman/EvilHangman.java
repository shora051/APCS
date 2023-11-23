import java.io.File;
import java.io.IOException;
import java.util.*;


public class EvilHangman {
	private boolean debug;
	private Scanner in;
	private List<String> wordList;
	private int wordLength;
	private int remainingGuesses;
	private String solution;
	private String guessedLetters;


	/**
	 * Construct an EvilHangman object.
	 *
	 * @param fileName the name of the file that contains the word list.
	 * @param debug    indicates if the size of the remaining word list
	 *                 should be included in the toString result.
	 */
	public EvilHangman(String fileName, boolean debug) {
		this.debug = debug;
		in = new Scanner(System.in);
		inputWords(fileName);
		System.out.println("Number of guesses? ");
		remainingGuesses = in.nextInt();
		solution = "";
		for(int i = 0; i<wordLength; i++)
			solution = solution + '-';
		guessedLetters = "";



	}

	/**
	 * Plays one the game.  The user guesses letters until either
	 * they guess the word, or they run out of guesses.
	 * Game status is printed each turn and winning / losing
	 * information is printed at the conclusion of the game.
	 */
	public void playGame() {

		while (includesDash(solution) && remainingGuesses>0){
			System.out.println(this);
			String letter = inputLetter();
			if(guessedLetters.length() == 0){
				guessedLetters = guessedLetters + letter;
			} else{
				guessedLetters = guessedLetters + ", " + letter;
			}
			List<Partition> partition = getPartitionList(letter);
			removeSmallerPartitions(partition);
			wordList = getWordsFromOptimalPartition(partition);
			String tempsol = solution;
			substitute(wordList.get(0), letter);
			if (tempsol.equals(solution)){
				remainingGuesses--;
			}
		}
		if(remainingGuesses!=0){
			System.out.println("You win, Congrats");
		} else{
			System.out.println("You lose lmao, Sorry");
		}
		int index = (int)(Math.random() * wordList.size());
		System.out.println("The word was \"" + wordList.get(index) + "\"");

	}

	/**
	 * Creates and returns a status string that indicates the
	 * state of the game.
	 *
	 * @return the game status string4.
	 */
	@Override
	public String toString() {
		if(debug == true){
			return "\nRemaining Guesses: " + remainingGuesses + "\nGuessed Letters: " + guessedLetters + "\nSolution: " + solution + "\nRemaining words: " + wordList.size();
		} else{
			return "\nRemaining Guesses: " + remainingGuesses + "\nGuessed Letters: " + guessedLetters + "\nSolution: " + solution;

		}



	}


	////////// PRIVATE HELPER METHODS //////////

	/**
	 * Helper method for the constructor:
	 * Inputs the word length from the user, reads in the words from
	 * the fileName file, and initializes the wordList instance variable
	 * with the words of the correct length.  This method loops until
	 * a valid word length is entered.
	 *
	 * @param fileName the name of the file that contains the word list.
	 */
	private void inputWords(String fileName) {
		wordList = new ArrayList<>();
		Scanner input = null;

		try{
			input = new Scanner(new File(fileName));
		}
		catch(IOException e){
			System.out.println("Can't find file!");
		}

		while(wordList.size() == 0){
			System.out.println("Word length? ");
			wordLength = in.nextInt();

			while(input.hasNext()){
				String a = input.next();
				if(a.length() == wordLength){
					wordList.add(a);

				}
			}
			if(wordList.size() == 0)
				System.out.println("There are no words with " + wordLength + " letters");


		}


	}

	/**
	 * Helper method for playGame:
	 * Inputs a one-letter string from the user.
	 * Loops until the string is a one-character character in the range
	 * a-z or A-Z.
	 *
	 * @return the single-letter string converted to upper-case.
	 */
	private String inputLetter() {
		String a = "";
		while(a.length() == 0){
			System.out.println("Next Letter? ");
			String temp = in.next().toUpperCase();
			if(temp.length() == 1) {
				if (temp.matches("[a-zA-Z]+")) {
					a = a + temp;
				} else {
					System.out.println("Invalid Input!");
				}
			} else{
				System.out.println("One Letter Please");
			}
		}
		return a;

	}

	/**
	 * Helper method for getPartitionList:
	 * Uses word and letter to create a pattern.  The pattern string
	 * has the same number of letter as word.  If a character in
	 * word is the same as letter, the pattern is letter; Otherwise
	 * it's "-".
	 *
	 * @param word   the word used to create the pattern
	 * @param letter the letter used to create the pattern
	 * @return the pattern
	 */
	private String getPattern(String word, String letter) {
		StringBuilder a = new StringBuilder(word);

		for (int i = 0; i < a.length(); i++) {
			if(word.charAt(i) == letter.charAt(0)){
				a.setCharAt(i, letter.charAt(0));
			} else{
				a.setCharAt(i, '-');
			}
		}
		return a.toString();


	}

	/**
	 * Helper method for playGame:
	 * Partitions each string in wordList based on their patterns.
	 *
	 * @param letter the letter used to find the pattern for
	 *               each word in wordList.
	 * @return the list of partitions.
	 */
	private List<Partition> getPartitionList(String letter) {
		List<Partition> partitionList = new ArrayList<>();
		for (int i = 0; i < wordList.size(); i++) {
			String word = wordList.get(i);
			String pattern = getPattern(word, letter);

			if(partitionList.size() == 0){
				partitionList.add(new Partition(pattern, word));
			}
			else{
				boolean a = true;
				for (int j = 0; j < partitionList.size(); j++) {
					if(partitionList.get(j).addIfMatches(pattern, word) == false){
						a&=true; 
					} else a&=false;

				}
				if (a)
					partitionList.add(new Partition(pattern, word));

			}

		}
//        for (int i = 0; i < partitionList.size(); i++) {
//            for (int j = i+1; j < partitionList.size(); j++) {
//                if(partitionList.get(i).addIfMatches(partitionList.get(j).getPattern(), partitionList.get(j).getWords().get(0))){
//                    partitionList.remove(j);
//                }
//            }
//        }
		return partitionList;
	}



	/**
	 * Helper method for playGame:
	 * Removes all but the largest (most words) partitions from partitions.
	 *
	 * @param partitions the list of partitions.
	 *                   Precondition: partitions.size() > 0
	 *                   Postcondition: partitions
	 *                   1) contains all of the partitions with the most words.
	 *                   2) does not contain any of the partitions with fewer than the most words.
	 */
	private void removeSmallerPartitions(List<Partition> partitions) {
		int max = partitions.get(0).getWords().size();

		for (int i = 0; i < partitions.size(); i++) {
			if(partitions.get(i).getWords().size() > max){
				max = partitions.get(i).getWords().size();
			}
		}

		for (int i = 0; i < partitions.size(); i++) {
			if(partitions.get(i).getWords().size() != max) {
				partitions.remove(i);
				i--;
			}
			//if u set i to 0 every time and there are 2 lists with same size, it will never leave loop ********* FIX THIS!!!!!!!!!
		}


	}



	/**
	 * Helper method for playGame:
	 * Returns the words from one of the optimal partitions,
	 * that is a partition with the most dashes in its pattern.
	 *
	 * @param partitions the list of partitions.
	 * @return the optimal partition.
	 */
	private List<String> getWordsFromOptimalPartition(List<Partition> partitions) {
		int max = partitions.get(0).getPatternDashCount();
		for (int i = 0; i < partitions.size(); i++) {
			if(partitions.get(i).getPatternDashCount() > max){
				max = partitions.get(i).getPatternDashCount();
			}
			for (int j = 0; j < partitions.size(); j++) {

				if(partitions.get(i).getPatternDashCount() == max)
					return partitions.get(i).getWords();

			}

		}
		return null;
	}

	/**
	 * Helper method for playGame:
	 * Creates a new string for solution.  If the ith letter of
	 * found equals letter, then the ith letter of solution is
	 * changed to letter; Otherwise it is unchanged.
	 *
	 * @param found  the string to check for occurances of letter.
	 * @param letter the letter to check for in found.
	 */
	private void substitute(String found, String letter) {

		StringBuilder a = new StringBuilder(solution);

		for (int i = 0; i < found.length(); i++) {
			if(found.charAt(i) == letter.charAt(0))
				a.setCharAt(i, letter.charAt(0));
			else
				a.setCharAt(i, solution.charAt(i));
		}
		solution = a.toString();

	}
	private boolean includesDash(String s){
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-')
				return true;
		}
		return false;
	}
	
}
