//17.letter-combinations-of-a-phone-number
/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 * <p>
 * <p>
 * Related Topics Hash Table String Backtracking 👍 20910 👎 1131
 */

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private static final Map<Character, char[]> MAP = Map.ofEntries(
                Map.entry('2', new char[]{'a', 'b', 'c'}),
                Map.entry('3', new char[]{'d', 'e', 'f'}),
                Map.entry('4', new char[]{'g', 'h', 'i'}),
                Map.entry('5', new char[]{'j', 'k', 'l'}),
                Map.entry('6', new char[]{'m', 'n', 'o'}),
                Map.entry('7', new char[]{'p', 'q', 'r', 's'}),
                Map.entry('8', new char[]{'t', 'u', 'v'}),
                Map.entry('9', new char[]{'w', 'x', 'y', 'z'})
        );

        public List<String> letterCombinations(String digits) {
            List<String> list = new ArrayList<>();
            if(digits!=null&&digits.length()>0){
                dfs(digits, 0, new char[digits.length()], list);
            }
            return list;
        }

        private void dfs(String digits, int index, char[] path, List<String> res) {
            if (index == digits.length()) {
                res.add(new String(path));
                return;
            }
            char ch = digits.charAt(index);
            for (char c : MAP.getOrDefault(ch, new char[0])) {
                path[index] = c;
                dfs(digits, index + 1, path, res);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}