package dataStructure.jike.day01;


/**
 * @Author junode
 * @Date 2021/3/12
 */
public class Question02 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7};
        int target = 4;
        System.out.println(searchIndex(nums, target, false) - searchIndex(nums, target, true) - 1);
        System.out.println(searchIndex2(nums,target,false) - searchIndex2(nums,target,true) + 1);
    }
    private static int searchIndex(int[] nums, int target, boolean isLeft) {// method_01 返回结果：right指向左边target的index-1位置，left指向右边target的index+1位置
        int left = 0, right = nums.length - 1;
        while (left <= right) {// 注意，若想[left,right]区间无元素，则应该right跨过left，继而需要在while循环中判断 left = right情况
            int med = left + (right - left) / 2;
            if (nums[med] < target) left = med + 1;
            else if (nums[med] > target) right = med - 1;
            else {
                if (isLeft) right = med - 1; // 若想找target的开始位置，则将右边界right赋值为med-1
                else left = med + 1; // 若想找target的结束位置，则将左边界left赋值为med+1
            }
        }
        return isLeft ? right : left; // 经过遍历后，right实际上指向的左边界，left指向的是右边界。
    }

    private static int searchIndex2(int[] nums, int target, boolean isLeft) { // method_02 返回结果：right指向target的最右侧index位置，left指向target的最左侧index位置
        int left = 0, right = nums.length - 1;
        while (left <= right) {// 注意，若想[left,right]区间无元素，则应该right跨过left，继而需要在while循环中判断 left = right情况
            int med = left + (right - left) / 2;
            if (nums[med] < target) left = med + 1;
            else if (nums[med] > target) right = med - 1;
            else {
                if (isLeft) right = med - 1; // 若想找target的开始位置，则将右边界right赋值为med-1
                else left = med + 1; // 若想找target的结束位置，则将左边界left赋值为med+1
            }
        }
        return isLeft ? left : right; // 此时left指向的是target左侧开始位置，right指向target右侧开始位置
    }

    public static int search(int[] nums, int target) {
        // 搜索右边界 right
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= target) i = m + 1;
            else j = m - 1;
        }
        int right = i;
        // 若数组中无 target ，则提前返回
        if (j >= 0 && nums[j] != target) return 0;
        // 搜索左边界 right
        i = 0;
        j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] < target) i = m + 1;
            else j = m - 1;
        }
        int left = j;
        return right - left - 1;
    }

}
