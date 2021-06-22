package dataStructure.jike.day01;

/**
 * 第三题：盛最多水的容器 <br />
 * https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/
 * @Author junode
 * @Date 2021/3/13
 */
public class Question03 {

    public static void main(String[] args) {
//        int[] contains = {1,8,6,2,5,4,8,3,7};
        int[] contains = {2,3,4,5,18,17,6};
        System.out.println(maxArea(contains));;
    }

    // method_01 双指针法
    public static int maxArea(int[] height){
        int left = 0,right = height.length - 1;
        int maxArea = 0;
        while(left < right) {
            int currArea = height[left] > height[right] ? height[right] * (right - left) : height[left] * (right - left);
            maxArea = maxArea > currArea ? maxArea : currArea;
            if( height[left] < height[right] ){
                left++ ;
            }else {
                right --;
            }
        }
        return maxArea;
    }
}
