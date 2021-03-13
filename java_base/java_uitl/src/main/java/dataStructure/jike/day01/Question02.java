package dataStructure.jike.day01;

/**
 * @description: 统计一个数字在排序数组中出现的次数
 * @author: hitton
 * @create: 2021-03-11 00:19
 **/
public class Question02 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(binarySearch(nums, target));
    }

    /**
     * 目的：统计一个数字在排序数组中出现的次数
     * 转换为：找到最靠近target的左边下标索引，通过二分查找不断缩减窗口大小进行定位
     */
    private static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 窗口大小为[leftIndex = 0,rightIndex = nums.length - 1]
        int leftIndex=0,rightIndex = nums.length - 1;
        while(leftIndex <= rightIndex) {// 当rightIndex < leftIdex时，表明已经遍历完成整个窗口，此时leftIndex为最靠近target的下标索引
            // while循环体里：通过二分缩减窗口大小[leftIndex,rightIndex],找到target的LeftIndex
            // 1 计算中点，med
            int med = leftIndex + ( rightIndex - leftIndex ) / 2;
            if(nums[med] <= target ) { // 若nums[med] 小于 target 表示在右侧，下次遍历范围位[mde+1,rightIndex]。todo 对于等于的边界判断是走哪边？
                leftIndex = med + 1;
            }else { // 否则，在左侧，下次遍历范围为[leftIndex,med - 1]
                rightIndex = med - 1;
            }
        }
        // todo 这里返回的时leftIndex还是rightIndex
        return 0;
    }


}
