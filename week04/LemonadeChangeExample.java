package com.msk.home;

/**
 * 柠檬水找零
 */
public class LemonadeChangeExample {

    /**
     * 谈心算法 用一张10元的和一张5元的去找零20元的钞票，达到整体找零的最优解
     *
     * @param nums
     * @return
     */
    public boolean lemonadeChange(int[] nums) {
        int ten = 0;
        int five = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 5) five++;
            else if (nums[i] == 10) {
                //若没有5元的钞票，则无法找零
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    ten--;
                    five--;
                } else if (five > 2) five -= 3;
                //若没有一张10元的一张5元钞票或者没有3张5元的钞票，则无法找零
                else return false;
            }
        }
        return true;
    }

}
