import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;

/**
 * @author spenry
 * @version 01/30/23
 * Scrabble Soccer Program that takes a scrabble word and outputs a point value for the word
 */
public class ScrabbleScorer {
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int[] values = {1, 3, 3, 2, 1, 4,
            2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
            1, 1, 1, 1, 4, 4, 8, 4, 10};
    private ArrayList<ArrayList<String>> dictionary;

    /**
     * Constructor for the ScrabbleScorer class
     */
    public ScrabbleScorer()   {
        dictionary = new ArrayList<>();
        for(int i = 0; i < 26; i++)
            dictionary.add(new ArrayList<String>());
        buildDictionary();
    }

    /**
     * This method builds the dictionary for the program and sorts the dictionary by askii valye
     * returns void
     */
    public void buildDictionary()   {
        try {
            Scanner in = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while(in.hasNext())  {
                String word = in.nextLine();
                int index = alpha.indexOf(word.substring(0, 1));
                dictionary.get(index).add(word);
            }
            in.close();
            // now I need to sort all the buckets
            for(int i = 0; i < dictionary.size(); i++)
                Collections.sort(dictionary.get(i));
        }
        catch(Exception e)   {
            System.out.println("Error here: " + e);
        }
    }

    /**
     * Method to check if a word is valid
     * @param word takes a word that the user inputs
     * @return false if that word is not a valid word, returns true otherwise
     */
    public boolean isValidWord(String word)   {
        //  find the correct bucket for word by looking at the first letter
        // find the indexOf the first letter in alpha
        // get the corresponding bucket
        if (Collections.binarySearch(dictionary.get(alpha.indexOf(word.substring(0,1))), word) < 0)
            return false;
        return true;

    }

    /**
     * This method is what converts the word to a point value
     * @param word takes a word user inputs
     * @return an int that represents the scrabble point value of word
     */
    public int getWordScore(String word)   {
        int score = 0;
        for(int i = 0; i < word.length(); i++)   {
            int index = alpha.indexOf(word.substring(i, i+1));
            score += values[index];
        }
        return score;
    }

    /**
     * Main Method of Scrabble Scorer Class
     * @param args command line args if needed 
     */
    public static void main(String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("*Welcome to the Scrabble Word Scorer App*");
        Scanner in = new Scanner(System.in);
        while(true)   {
            System.out.println("*Enter a word to score or 0 to quit* ");
            String word = in.nextLine().toUpperCase();
            if(word.equals(0))   {
                break;
            }
            if(app.isValidWord(word))   {
                System.out.println(word + " = " + app.getWordScore(word) + " points");
            }
            else
                System.out.println(word + " is not a valid word in the dictionary");

        }
        System.out.println("*Exiting the game, thanks for playing*");
    }
}
