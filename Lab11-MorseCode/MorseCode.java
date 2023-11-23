import java.util.*;

public class MorseCode {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 ";
    private final String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.","....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...","-","..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-",".....", "-....", "--...", "---..", "----.", "-----", "|"};
    private HashMap<String, String> toText = new HashMap<>();
    private HashMap<String, String> toCode = new HashMap<>();
    
    public MorseCode(){
        //to text
        for(int i = 0 ; i < morse.length ; i++){
            toText.put(morse[i], alphabet.substring(i, i+1));
        }
        
        //to code
        for(int i = 0 ; i < alphabet.length() ; i++){
            toCode.put(alphabet.substring(i, i+1), morse[i]);
        }
    }
    
    public String encode(String s){
        String yeah = "";
        for(int i = 0; i < s.length(); i++){
            
                yeah = yeah + toCode.get(s.substring(i, i+1)) + " ";
            
        }
        return yeah;
    }
    
    public String decode(String s){
        String yeah = "";
        String[] yuh = s.split(" ");
        for(int i = 0; i < yuh.length; i++){
            yeah = yeah + toText.get(yuh[i]);
        }
        return yeah;
    }
    
    public static void main(String[] args){
        MorseCode m = new MorseCode();
        System.out.println(m.encode("hello world"));
        System.out.println(m.decode("--- -- --. | .. - | .-- --- .-. -.- . -.."));
    }

}
