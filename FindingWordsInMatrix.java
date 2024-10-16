import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class FindingKeys {

    public static void main(String []args) {
        char[][] grid = {
            {'A', 'B', 'R'},
            {'C', 'Y', 'P'},
            {'H', 'U', 'L'}
        };
        
        List<String> words = List.of("ABU", "BRP", "ABR", "RPL", "HUY", "CYL");
        System.out.println(findingKeyWords(grid, words));
    }
    
    private static List<String> findingKeyWords(char[][] grid, List<String> words) {
        Map<Character, List<Integer>> letters = new HashMap<Character, List<Integer>>();
        
        for (int i=0; i < grid.length; i++) {
            for (int j=0; j < grid.length; j++) {
                letters.put(grid[i][j], Arrays.asList(i,j));
            }
        }

        List<String> wordMatched = new ArrayList<>();
        words.forEach(word -> {
            //consider the letter does not exist on the grid
            List<Integer> startLetterPosition = letters.get(word.charAt(0));

            for (int i=1; i < word.length(); i ++) {
                List<Integer> nextLetterPosition = letters.get(word.charAt(i));
                
                int row = Math.abs(startLetterPosition.get(0) - nextLetterPosition.get(0));
                int col = Math.abs(startLetterPosition.get(1) - nextLetterPosition.get(1));
                
                if ((row == 1 && col == 0) || (row == 0 && col == 1)) {
                    startLetterPosition = nextLetterPosition;
                    if (i == word.length()-1) {
                        wordMatched.add(word);
                    }
                    continue;
                }
                break;
            }
        });
        return wordMatched;
    }
}
