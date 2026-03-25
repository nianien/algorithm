//207.course-schedule
/**
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
 * bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to
 * first take course 1.
 * <p>
 * <p>
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you
 * should also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * <p>
 * <p>
 * Related Topics Depth-First Search Breadth-First Search Graph Theory
 * Topological Sort 👍 17894 👎 869
 */

package leetcode.editor.en;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        Solution solution = new CourseSchedule().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, Set<Integer>> depends = new HashMap<>(); //inEdges
            Map<Integer, Set<Integer>> depended = new HashMap<>(); //outEdges
            for (int[] prerequisite : prerequisites) {
                depends.computeIfAbsent(prerequisite[0], _ -> new HashSet<>()).add(prerequisite[1]);
                depended.computeIfAbsent(prerequisite[1], _ -> new HashSet<>()).add(prerequisite[0]);
            }
            Deque<Integer> independents = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (!depends.containsKey(i)) {
                    independents.add(i);
                }
            }
            while (independents.size() > 0) {
                int i = independents.poll();
                Set<Integer> deps = depended.getOrDefault(i, Set.of());
                for (Integer dep : deps) {
                    Set<Integer> value = depends.get(dep);
                    value.remove(i);
                    if (value.isEmpty()) {
                        depends.remove(dep);
                        independents.add(dep);
                    }
                }
            }
            return depends.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}