import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import Solution3.Solution3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test_2022111199_3 {

    /**
     * 测试策略：
     * 1. 输入为 null
     * 2. 输入为空数组
     * 3. 输入包含非正整数（如负数或零）
     * 4. 输入包含重复元素
     * 5. 输入为正常的数组
     *    5.1 数组中的元素为互质数（没有共同因子的数）
     *    5.2 数组中的元素为非互质数（存在整除关系的数）
     *    5.3 数组仅包含单个元素
     *    5.4 数组中存在多个有效解子集
     */

    // 测试1：输入为null，应抛出IllegalArgumentException异常
    @Test(expected = IllegalArgumentException.class)
    public void testInputIsNull() {
        Solution3 solution = new Solution3();
        solution.largestDivisibleSubset(null);
    }

    // 测试2：输入为空数组，应抛出IllegalArgumentException异常
    @Test(expected = IllegalArgumentException.class)
    public void testInputIsEmptyArray() {
        Solution3 solution = new Solution3();
        solution.largestDivisibleSubset(new int[]{});
    }

    // 测试3：输入包含非正整数（如负数），应抛出IllegalArgumentException异常
    @Test(expected = IllegalArgumentException.class)
    public void testInputContainsNonPositiveInteger() {
        Solution3 solution = new Solution3();
        solution.largestDivisibleSubset(new int[]{1, -2, 3});
    }

    // 测试4：输入包含重复元素，应抛出IllegalArgumentException异常
    @Test(expected = IllegalArgumentException.class)
    public void testInputContainsDuplicateElements() {
        Solution3 solution = new Solution3();
        solution.largestDivisibleSubset(new int[]{1, 2, 2, 3});
    }

    // 测试5：正常输入的数组
    // 5.1：互质数的输入数组，预期返回单个元素的集合，因为没有其他数满足整除条件
    @Test
    public void testInputIsCoprimeNumbers() {
        Solution3 solution = new Solution3();
        List<Integer> result = solution.largestDivisibleSubset(new int[]{5, 7, 11});
        assertEquals(Arrays.asList(5), result);
    }

    // 5.2：非互质数的输入数组，预期返回包含最大整除子集的集合
    @Test
    public void testInputIsNonCoprimeNumbers() {
        Solution3 solution = new Solution3();
        List<Integer> result = solution.largestDivisibleSubset(new int[]{1, 2, 4, 8});
        assertEquals(Arrays.asList(1, 2, 4, 8), result);
    }

    // 5.3：单个元素的输入数组，预期返回该元素的集合
    @Test
    public void testInputIsSingleElement() {
        Solution3 solution = new Solution3();
        List<Integer> result = solution.largestDivisibleSubset(new int[]{10});
        assertEquals(Arrays.asList(10), result);
    }

    // 5.4：多个有效解子集（如[1,2] 和 [1,3]）均符合要求，验证其中一个正确性
    @Test
    public void testInputIsNormalArray() {
        Solution3 solution = new Solution3();
        List<Integer> result = solution.largestDivisibleSubset(new int[]{1, 2, 3});
        assertTrue(result.equals(Arrays.asList(1, 2)) || result.equals(Arrays.asList(1, 3)));
    }
}
