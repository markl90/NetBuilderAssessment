package stringReplacer;

public class StringReplacer {

    private String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String replaceLetterWithPosition(String input){
        String upperCase = input.toUpperCase();
        char [] inputCharacters = upperCase.toCharArray();
        char [] alphabetCharacters = Alphabet.toCharArray();
        String result = "";

         for (int i = 0; i<inputCharacters.length; i++){
             for (int j = 0; j<alphabetCharacters.length; j++){
                 if(inputCharacters[i]==alphabetCharacters[j]){
                     result += (j+1)+" ";
                 }
             }

         }
        return result.trim();
    }

}
