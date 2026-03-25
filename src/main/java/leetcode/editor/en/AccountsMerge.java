//721.accounts-merge
/**
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the elements
 * are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to
 * the same person if there is some common email to both accounts. Note that even
 * if two accounts have the same name, they may belong to different people as people
 * could have the same name. A person can have any number of accounts initially,
 * but all of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format: the
 * first element of each account is the name, and the rest of the elements are
 * emails in sorted order. The accounts themselves can be returned in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],[
 * "John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John",
 * "johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"]
 * ,["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email
 * "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses
 * are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary',
 * 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']]
 * would still be accepted.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin",
 * "Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co",
 * "Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.
 * co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.
 * co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"]
 * ,["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1
 *
 * @m.co","Fern5@m.co"]] Constraints:
 * <p>
 * <p>
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 * <p>
 * <p>
 * Related Topics Array Hash Table String Depth-First Search Breadth-First Search
 * Union-Find Sorting 👍 7666 👎 1308
 */

package leetcode.editor.en;

import java.util.*;
import java.util.Map.Entry;

public class AccountsMerge {
    public static void main(String[] args) {
        Solution solution = new AccountsMerge().new Solution();
        List<List<String>> accounts = List.of(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        List<List<String>> lists = solution.accountsMerge(accounts);
        lists.forEach(System.out::println);
    }

    /**
     * 解题思路：并查集（Union-Find）
     * <p>
     * 核心思想：
     * 如果两个账户有相同的邮箱，说明属于同一个人，需要合并。
     * 这本质上是一个"连通分量"问题——把有公共邮箱的账户连在一起，最后收集每个连通分量的所有邮箱。
     * <p>
     * 并查集天然适合处理这种动态的"合并 + 查询是否同组"场景。
     * <p>
     * 这里的并查集直接以 email 字符串作为节点（而非数组下标），用 HashMap 实现。
     * <p>
     * 算法步骤：
     * 1. 遍历每个账户，将同一账户内的所有邮箱 union 到一起
     * - 以第一个邮箱 account.get(1) 作为"锚点"，将其余邮箱都与它合并
     * - 同时记录 email -> name 的映射
     * <p>
     * 2. 遍历并查集中所有邮箱，按 root 分组
     * - find(email) 得到该邮箱所属连通分量的根节点
     * - 相同 root 的邮箱归为一组
     * <p>
     * 3. 对每个分组，拼装结果：[name, email1, email2, ...]
     * <p>
     * 推演示例：
     * 输入: [["John","a@","b@"], ["John","a@","c@"], ["Mary","d@"]]
     * <p>
     * step1 - union:
     * 账户0: union(a@, a@), union(a@, b@)  -> a@ -- b@
     * 账户1: union(a@, a@), union(a@, c@)  -> a@ -- b@, a@ -- c@
     * 账户2: union(d@, d@)                  -> d@ 独立
     * <p>
     * step2 - 按 root 分组:
     * find(a@)=a@, find(b@)=a@, find(c@)=a@ -> 组1: {a@, b@, c@}
     * find(d@)=d@                            -> 组2: {d@}
     * <p>
     * step3 - 拼装结果:
     * ["John", "a@", "b@", "c@"], ["Mary", "d@"]
     * <p>
     * 时间复杂度：O(n * k * α(n*k))，n=账户数，k=平均邮箱数，α 为反阿克曼函数（路径压缩后近似 O(1)）
     * 空间复杂度：O(n * k)
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 并查集，以 String 为节点，HashMap 实现。
         * 支持路径压缩的 find 和基本的 union。
         */
        class Union {
            // parent 映射：email -> 其父节点 email
            Map<String, String> map = new HashMap<>();

            /**
             * 查找 name 所在集合的根节点，带路径压缩。
             * putIfAbsent 保证首次访问时自动初始化为指向自己。
             */
            private String find(String name) {
                map.putIfAbsent(name, name);
                if (!name.equals(map.get(name))) {
                    // 路径压缩：递归找到根节点后，直接将 name 指向根
                    map.put(name, find(map.get(name)));
                }
                return map.get(name);
            }

            /**
             * 合并 a 和 b 所在的集合。
             * 将 a 的根指向 b 的根。
             */
            private void union(String a, String b) {
                String ra = find(a);
                String rb = find(b);
                if (!ra.equals(rb)) {
                    map.put(ra, rb);
                }
            }
        }

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Union union = new Union();
            // email -> 账户名，用于最终结果拼装
            Map<String, String> email2Name = new HashMap<>();

            // step1: 遍历每个账户，将同一账户的所有邮箱合并到同一集合
            for (List<String> account : accounts) {
                for (int i = 1; i < account.size(); i++) {
                    // 将每个邮箱都与该账户的第一个邮箱 union
                    union.union(account.get(1), account.get(i));
                    email2Name.put(account.get(i), account.get(0));
                }
            }

            // step2: 按 root 对所有邮箱分组
            Map<String, List<String>> emailGroups = new HashMap<>();
            for (String s : union.map.keySet()) {
                String root = union.find(s);
                emailGroups.computeIfAbsent(root, _ -> new ArrayList<>()).add(s);
            }

            // step3: 拼装结果，每组前面加上账户名
            List<List<String>> ans = new ArrayList<>();
            for (List<String> emails : emailGroups.values()) {
                List<String> account = new ArrayList<>(emails.size() + 1);
                Collections.sort(emails);  // 加这一行
                account.add(email2Name.get(emails.get(0)));
                account.addAll(emails);
                ans.add(account);
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}