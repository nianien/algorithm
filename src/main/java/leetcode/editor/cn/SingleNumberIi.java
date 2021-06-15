//137.给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
//
// 说明： 
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
//
// 示例 1: 
//
// 输入: [2,2,3,2]
//输出: 3
// 
//
// 示例 2: 
//
// 输入: [0,1,0,1,0,1,99]
//输出: 99 
// Related Topics 位运算 
// 👍 541 👎 0


package leetcode.editor.cn;

public class SingleNumberIi {
    public static void main(String[] args) {
        Solution solution = new SingleNumberIi().new Solution();
        System.out.println( solution.singleNumber(new int[]{2,2,3,2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int singleNumber(int[] nums) {
            //对所有数的某一位进行统计模3以后,每位的状态有三种可能00,01,10, 由于每位需要两个bit表示,这里分别用ones和twos记录每bit的值
            //也就是原来的每一位经过计算后需要两个bit表示(可以理解三进制)
            //对于ones的计算规则是(0)0->(0)1->(1)0->(0)0, 即0->1->0->0
            //对于twos的计算规则是0(0)->0(1)->1(0)->0(0), 即0->0->1->0
            //可以发现,ones和twos的值计算过程互相依赖
            int ones = 0, twos = 0;
            for (int num : nums) {
                ones = ones ^ num & ~twos;
                twos = twos ^ num & ones;
            }
            //ones位为1表示模3余1
            return ones;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}