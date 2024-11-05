package Solution3;
import java.util.*;

/**
 * @description:
 *
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 *
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 *
 */

public class Solution3 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 异常检测部分

        // 1. 检测输入是否为空或长度是否为 0
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array 'nums' should not be null or empty.");
        }

        // 2. 检测是否包含非正整数
        for (int num : nums) {
            if (num <= 0) {
                throw new IllegalArgumentException("All elements in 'nums' should be positive integers.");
            }
        }

        // 3. 检测是否有重复元素
        Set<Integer> uniqueCheck = new HashSet<>();
        for (int num : nums) {
            if (!uniqueCheck.add(num)) {
                throw new IllegalArgumentException("Input array 'nums' should not contain duplicate elements.");
            }
        }


        int len = nums.length;//修改：将原本的length-1修改为length
        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = nums[0];//修改：将原本的dp[0]修改为nums[0],初始化为第一个元素的值
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {//修改：j从0开始，以覆盖所有元素
                // 题目中说「没有重复元素」很重要
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];//修改：maxVal更新为元素值nums[i]
            }
        }

        //第二步：倒推获得最大子集
        // 修改：使用 LinkedList 保证倒序插入从而获得升序结果
        LinkedList<Integer> res = new LinkedList<>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.addFirst(nums[i]); // 在列表头部插入，以确保升序
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }
}
