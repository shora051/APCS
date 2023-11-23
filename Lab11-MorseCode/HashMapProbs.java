import java.util.HashMap;
import java.util.*;
import java.io.*;
public class HashMapProbs
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(System.in);
        //number 2
        HashMap<String, String> animalSounds = new HashMap<>();
        animalSounds.put("Dog", "Woof");
        animalSounds.put("Cat", "Meow");
        animalSounds.put("Horse", "Nehh");
        animalSounds.put("Snake", "Hiss");
        //number 3
        System.out.println("Add a value for String str: ");
        String str = input.nextLine();
        if(animalSounds.get(str) != null)
        {
            System.out.println(animalSounds.get(str));
        }
        else
        {
            System.out.println("null");
        }
        //number 4
        System.out.println("pairs: " + animalSounds);
        //number 5
        System.out.println("Add a value for String newAnimal: ");
        String newAnimal = input.nextLine();
        System.out.println("Add a value for String newSound: ");
        String newSound = input.nextLine();
        animalSounds.put(newAnimal, newSound);
        System.out.println("pairs: " + animalSounds);
        //tester methods
        System.out.println(takeBefore("str", "bye"));
        System.out.println(multiple("hello"));
        System.out.println(charWord(new String[] {"tea", "salt", "soda", "taco"}));
         Scanner scann = new Scanner(new File("dream.txt"));
        HashMap<String, Integer> map = new HashMap<>();
        String q;
        while (scann.hasNext()) 
        {
            q = scann.next();
            if (map.containsKey(q)) {
                map.put(q, map.get(q) + 1);
            } 
            else 
            {
                map.put((q).toLowerCase(), 1);
            }
        }
        System.out.println(frequency());
    }
    //number 7
    public static HashMap<String, String> takeBefore(String a, String b)
    {
        HashMap<String, String> takeBefore = new HashMap<>();
        for(int i = 0; i < a.length(); i++)
        {
            if(a.charAt(i) < b.charAt(i))
            {
                takeBefore.put(a.substring(i, i+1), b.substring(i, i+1));
            }
            if(a.charAt(i) > b.charAt(i))
            {
                takeBefore.put(b.substring(i, i+1), a.substring(i, i+1));
            }
        }
        return takeBefore;
    }
   
    //number 8
    public static HashMap<String, Boolean> multiple(String s) 
    {
        HashMap<String, Boolean> multiple = new HashMap<>();
        String currentChar = "";
        int counter = 0;
        for (int x = 0; x < s.length(); x++) {
            currentChar = s.substring(x, x + 1);
            counter = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.substring(i, i + 1).equals(currentChar))
                {
                    counter++;
                }
            }
            if (counter > 1) 
            {
                multiple.put(currentChar, true);
            } else 
            {
                multiple.put(currentChar, false);
            }
        }
        return multiple;
    }
    //number 9
    public static HashMap<String, String> charWord(String[] words)
    {
        HashMap<String, String> charWord = new HashMap<>();
        for (String w : words) {
            if (charWord.containsKey(w.substring(0, 1))) {
                charWord.put(w.substring(0, 1), charWord.get(w.substring(0, 1)) + w);
            } else {
                charWord.put(w.substring(0, 1), w);
            }
        }
        return charWord;
    }
    //number 10
    public static String frequency() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("dream.txt"));
        HashMap<String, Integer> map = new HashMap<>();
        String q;
        while (scan.hasNext()) {
            q = scan.next();
            if (map.containsKey(q)) {
                map.put(q, map.get(q) + 1);
            } else {
                map.put((q).toLowerCase(), 1);
            }
        }

        int max = Collections.max(map.values()); //the largest value in the map
        //we will assume there is only one maximum
        for (String key : map.keySet()) { //iterate through all the keys in the map
            if (map.get(key) == max) //if this key's value matches max...
            {
                return ("Highest frequency word: " + key + ", " + max);
            }
        }
        return null;
    }
}

