package com.bbn.bbnknight;

public class LunchBlock {

    private boolean[] weekLunch = new boolean[5];

    //true for first lunch
    //false for second lunch
    public LunchBlock() {
        for(int i = 0; i < 5; i++) {
            weekLunch[i] = false;
        }
    }

    public  void setLunchTime(boolean firstLunch, int dayOfWeek) {
        //0 for monday
        //1 for tuesday
        //2 for wednesday
        //3 for thursday
        //4 for friday
        weekLunch[dayOfWeek] = firstLunch;
    }
    public boolean getLunchTime(int dayOfWeek) {
        //0 for monday
        //1 for tuesday
        //2 for wednesday
        //3 for thursday
        //4 for friday
        return weekLunch[dayOfWeek];
    }

}
