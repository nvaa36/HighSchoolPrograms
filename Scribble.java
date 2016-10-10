/**
 * This program allows you to play a game of scribble. You get dealt 8 tiles
 * from a set of tiles that goes down as the game goes on. The user will 
 * make a word then the computer will make the best word possible, and the 
 * game ends when the user enters an invalid word, or when someone can no 
 * longer play a word.
 * 
 * @author Nicole Anderson-Au
 * @version  September 25, 2014
 */
 
 import java.util.Scanner;

public class Scribble
{
	private String tiles;
	private String hand, comphand;
	private String word, compword;
	private int score, compscore;
	private int [] scoretable;
	private boolean badlength, badword, badtiles, compDone, userDone;
	
	public Scribble ( )
	{
		scoretable = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
					 1, 1, 1, 1, 4, 4, 8, 4, 10};
		tiles = new String("AAAAAAAAAABBCCDDDDEEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ");
		hand = new String("");
		comphand = new String("");
		word = new String("");
		compword = new String("");
		score = compscore = 0;
		badlength = badword = badtiles = compDone = userDone = false;
	}
	
	public static void main (String [] args)
	{
		Scribble play = new Scribble();
		play.oneGame();
	}
	
	/**
	 * The command line for playing on game of scribble. Shows the intro
	 * then chooses tiles for the computer and the user, then plays until
	 * the user is done or the computer is done then shows the exit message.
	 */
	public void oneGame ( )
	{
		introduction();
		chooseRandomTiles(true,true);
		do
		{
			userDone = playUserRound();
			if(!userDone)
			{
				compDone = playCompRound();
			}
		}
		while(!userDone && !compDone);
		exitMessage();
	}
	
	/**
	 *  Prints the introduction with the instructions and welcome message.
	 */
	public void introduction ( )
	{
		System.out.println("\n\n\n");
		System.out.println("WELCOME TO THE GAME OF");
		System.out.println(" ____            _ _     _     _");
		System.out.println("/ ___|  ___ _ __(_) |__ | |__ | | ___ ");
		System.out.println("\\___ \\ / __| '__| | '_ \\| '_ \\| |/ _ \\");
		System.out.println(" ___) | (__| |  | | |_) | |_) | |  __/");
		System.out.println("|____/ \\___|_|  |_|_.__/|_.__/|_|\\___|");
		System.out.println("\n\nThis game is a \"scaled down\" version of Scrabble.  The game starts with a pool of letter tiles, with");
		System.out.println("the following group of 100 tiles:\n");
		System.out.println("A A A A A A A A A A B B C C D D D D E E E E E E E E E E E E E F F G G G H H I I I I I I I I I J K L");
		System.out.println("L L L M M N N N N N N O O O O O O O O P P Q R R R R R R S S S S T T T T T T U U U U V V W W X Y Y Z");
		System.out.println("\nThe game starts with 8 tiles being chosen at random to fill the player's hand, and 8 tiles chosen at");
		System.out.println("random to fill the computer's hand.  The player must then create a valid word, with a length from 3 to 8");
		System.out.println("letters, from the tiles in his/her hand.  The \"word\" entered by the player is then checked.  It is first");
		System.out.println("checked for length, then checked to make sure it is made up of letters from the letters in the current hand,");
		System.out.println("and then it is checked against the wordlist text file.  If any of these tests fail, the game terminates.  If");
		System.out.println("the word is valid, points are added to the player's score according to the following table (These scores are");
		System.out.println("taken from the game of Scrabble):");
		System.out.println("\nA  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z");
		System.out.println("1  3  3  2  1  4  2  4  1  8  5  1  3  1  1  3  10 1  1  1  1  4  4  8  4  10");
		System.out.println("\nOnce the player's score has been updated, more tiles are chosen at random from the remaining pool of");
		System.out.println("letters, to fill the player's hand to 8 letters.  Then, it is the computer's turn.  The computer will choose");
		System.out.println("the \"best\" word (highest score).  Once the computer completes its turn, the player again creates a word,");
		System.out.println("and the process continues.  The game ends when the player enters an invalid word, or the computer cannot");
		System.out.println("create a word, or the letters in the pool and players' hands run out.  Ready?  Let's play!\n\n\n");
		Prompt.getString("Hit ENTER on the keyboard to continue: ");
	}
	
	/**
	 *  Plays one user round of the game. First shows the status of the game,
	 *  then get the word from the user, then checks whether it is the right
	 *  length, fits the tiles, and is a valid word. Then throws away used
	 *  tiles and chooses random new ones.
	 *
	 *  @return  If it's a good word return false to say that the user is 
	 *  not done, and if it is a bad word return true to say that the user
	 *  is done.
	 */
	public boolean playUserRound ( )
	{
		showStatus();
		getWordFromUser();
		boolean goodword = verifyTiles() && verifyWord();
		if(goodword)
		{
			score += addToScore(word);
			hand = throwTiles(hand, word);
			chooseRandomTiles(true,false);
		}
		return !goodword;
	}
	
	/**
	 *  Plays one computer round of the game. First shows the status of the game,
	 *  then gets the best word possible using the WordUtilities class,
	 *  then adds the score to the current computer score.
	 *
	 *  @return  If the user can't make a word returns true to say that
	 *  the computer is done, if the computer can make a word, returns
	 *  false to say that the computer is not done.
	 */
	public boolean playCompRound ( )
	{
		showStatus();
		Prompt.getString("\n\nIt's the computer's turn. Hit ENTER on the keyboard to continue: ");
		String [] words = new String[2000];
		words = WordUtilities.findAllWords(comphand.toLowerCase());
		if(words.length == 0)
			return true;
		String compword = WordUtilities.bestWord(words,scoretable);
		compscore += addToScore(compword);
		System.out.println("\nthese  are the things   " + compword + "these  are the things   " + comphand);
		comphand = throwTiles(comphand, compword);
		chooseRandomTiles(false, true);
		System.out.println("\nThe computer chose: " + compword.toUpperCase());
		return false;
	}
	
	/**
	 *  Gets input as to which hands need to be refilled and refills them
	 *  and gets rid of the corresponding letters from the tiles.
	 *
	 *  @param    user   whether the user's tiles have to be refilled.
	 *  @param    comp   whether the computer's tiles have to be refilled.
	 */
	public void chooseRandomTiles(boolean user, boolean comp)
	{
		int random = 0;
		if(tiles.length() == 0)
			return;
		if(user)
		{
			if(tiles.length() < 8 - hand.length())
			{
				hand += tiles;
				tiles = "";
				return;
			}
			while(hand.length() < 8)
			{
				random = (int)(Math.random() * tiles.length());
				hand += tiles.charAt(random);
				if(random == tiles.length() - 1)
					tiles = tiles.substring(0, random - 1);
				else
					tiles = tiles.substring(0, random) + tiles.substring(random + 1);
			}
		}
		
		if(comp)
		{
			if(tiles.length() < 8 - comphand.length())
			{
				comphand += tiles;
				tiles = "";
				return;
			}
			while(comphand.length() < 8)
			{
				random = (int)(Math.random() * tiles.length());
				comphand += tiles.charAt(random);
				if(random == tiles.length() - 1)
					tiles = tiles.substring(0, random - 1);
				else
					tiles = tiles.substring(0, random) + tiles.substring(random + 1);
			}
		}
	}
	
	/**
	 *  Shows the status of the game. The scores, the letters remaining,
	 *  and the tiles in both of the hands.
	 */
	public void showStatus()
	{
		System.out.println("\nHere are the tiles remaining in the pool of letters:");
		for(int i = 0;i < tiles.length();i++)
		{
			if(i%20 == 0)
				System.out.println();
			System.out.print(tiles.charAt(i) + " ");
			
		}
		System.out.printf("\n\nPlayer Score:%7d", score);
		System.out.printf("\nComputer Score:%5d", compscore);
		System.out.print("\n\nTHE TILES IN YOUR HAND ARE:                 ");
		for(int i = 0;i < hand.length();i++)
			System.out.print(hand.charAt(i) + "   ");
		System.out.print("\n\nTHE TILES IN THE COMPUTER HAND ARE:         ");
		for(int i = 0;i < comphand.length();i++)
			System.out.print(comphand.charAt(i) + "   ");
	}
	
	/**
	 *  Gets a word from a user.
	 */
	public void getWordFromUser()
	{
		word = Prompt.getString("\n\nPlease enter a word created from your current set of tiles -> ");
	}
	
	/**
	 *  Verifies that the word is the right length and makes sure that it
	 *  can be created from the tiles in the given hand.
	 */
	public boolean verifyTiles()
	{
		if(word.length() < 3 || word.length() > 8)
		{
			 badlength = true;
			 return !badlength;
		 }
		String copy = hand;
		boolean match = false;
		for(int i = 0;i < word.length();i++){
			match = false;
			for(int j = 0;j < copy.length();j++){
				if(word.toUpperCase().charAt(i) == copy.charAt(j)){
					match = true;
					copy = copy.substring(0, j) + copy.substring(j + 1);
					j = copy.length();
				}
			}
			if(!match)
			{
				badtiles = true;
				return false;
			}
		}
		return !badlength;
	}
	
	/**
	 *  Verifies that the word that the user entered is a real world.
	 */
	public boolean verifyWord()
	{
		Scanner fromfile = OpenFile.openToRead("words.txt");
		int counter = 0;
		while(fromfile.hasNext()){
			if(fromfile.nextLine().equals(word.toLowerCase())){
				return true;
			}
		}
		fromfile.close();
		badword = true;
		return false;
	}
	
	/**
	 *  Gets the word from the user, gets the score, and returns it to
	 *  add to the current score.
	 * 
	 *  @param    word2   the word to get the score of.
	 *
	 *  @return  returns the score of the word to be added to the current score.
	 */
	public int addToScore(String word2)
	{
		int add = 0;
		for(int i = 0;i < word2.length();i++)
		{
			add += scoretable[word2.charAt(i) - 97];
		}
		
		return add;
	}
	
	/**
	 *  Gets rid of the tiles that were used for the word.
	 * 
	 *  @param    word2   the word that was made.
	 *  @param    hand2   the hand that was used.
	 *
	 *  @return  returns the new hand after the word was subtracted from it.
	 */
	public String throwTiles(String hand2, String word2)
	{
		for(int i = 0;i < word2.length();i++){
			for(int j = 0;j < hand2.length();j++){
				if(word2.toUpperCase().charAt(i) == hand2.charAt(j)){
					hand2 = hand2.substring(0, j) + hand2.substring(j + 1);
					j = hand2.length();
				}
			}
		}
		return hand2;
	}
	
	/**
	 *  Prints the exit message with what ended the game, what the scores
	 *  were, and a thank you message.
	 */
	public void exitMessage()
	{
		if(compDone)
			System.out.println("\n\nNo more words can be created.");
		if(badlength)
			System.out.println("\n\nError: Your word is not a valid length.");
		if(badtiles)
			System.out.println("\n\nError: Your word cannot be created from your tiles.");
		if(badword)
			System.out.println("\n\nError: Your word is not a valid word.");
		
		System.out.printf("\n\nPlayer Score:%7d", score);
		System.out.printf("\nComputer Score:%5d", compscore);
		System.out.println("\n\nThank You for playing Scribble!");
		System.out.println("\n\n");
	}
}









