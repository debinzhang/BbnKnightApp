package com.bbn.bbnknight;

import java.util.ArrayList;

public class BlocksInWeek {
    public static ArrayList<DayBlock> weekBlock = new ArrayList<>();

    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    public static final String A_BLOCK  = "A Block";
    public static final String B_BLOCK  = "B Block";
    public static final String C_BLOCK  = "C Block";
    public static final String D_BLOCK  = "D Block";
    public static final String E_BLOCK  = "E Block";
    public static final String F_BLOCK  = "F Block";
    public static final String G_BLOCK  = "G Block";
    public static final String H_BLOCK  = "H Block";
    public static final String LUNCH_BLOCK  = "Lunch";
    public static final String ADVISORY_BLOCK  = "Advisory";
    public static final String ACTIVITIES = "Activities";

    public static class BlockItem {
        String name;
        String start_time;
        String end_time;
        Boolean notification;
        public BlockItem(String name, String start_time, String end_time, Boolean notification) {
            this.name = name;
            this.start_time = start_time;
            this.end_time = end_time;
            this.notification = notification;
        }
    };

    public static class DayBlock {
        ArrayList<BlockItem> dayBlock;
        DayBlock() {
            dayBlock = new ArrayList<BlockItem>();
        }
        public void addBlock(BlockItem block) {
            dayBlock.add(block);
        }
        public BlockItem getBlock(int i) {
            return dayBlock.get(i);
        }
    }

    public static void initBlocks() {
        DayBlock dayBlock;
        BlockItem blockItem;
        // Monday
        dayBlock = new DayBlock();
        blockItem = new BlockItem(ADVISORY_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(A_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(B_BLOCK, "9:00m", "9:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(C_BLOCK, "10:00am", "10:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(D_BLOCK, "11:00am", "11:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(LUNCH_BLOCK, "12:00am", "12:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(F_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(G_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        weekBlock.add(dayBlock);

        // Tuesday
        dayBlock = new DayBlock();
        blockItem = new BlockItem(ADVISORY_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(B_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(A_BLOCK, "9:00m", "9:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(C_BLOCK, "10:00am", "10:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(D_BLOCK, "11:00am", "11:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(LUNCH_BLOCK, "12:00am", "12:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(F_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(G_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        weekBlock.add(dayBlock);

        // Wednesday
        dayBlock = new DayBlock();
        blockItem = new BlockItem(ADVISORY_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(C_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(A_BLOCK, "9:00m", "9:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(B_BLOCK, "10:00am", "10:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(D_BLOCK, "11:00am", "11:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(LUNCH_BLOCK, "12:00am", "12:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(F_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(G_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        weekBlock.add(dayBlock);

        // Thursday
        dayBlock = new DayBlock();
        blockItem = new BlockItem(ADVISORY_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(D_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(A_BLOCK, "9:00m", "9:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(B_BLOCK, "10:00am", "10:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(C_BLOCK, "11:00am", "11:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(LUNCH_BLOCK, "12:00am", "12:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(F_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(G_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        weekBlock.add(dayBlock);

        // Fridday
        dayBlock = new DayBlock();
        blockItem = new BlockItem(ADVISORY_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(E_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(A_BLOCK, "9:00m", "9:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(B_BLOCK, "10:00am", "10:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(C_BLOCK, "11:00am", "11:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(LUNCH_BLOCK, "12:00am", "12:45am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(A_BLOCK, "7:45am", "8:00am", true);
        dayBlock.addBlock(blockItem);
        blockItem = new BlockItem(G_BLOCK, "8:00am", "8:45am", true);
        dayBlock.addBlock(blockItem);
        weekBlock.add(dayBlock);
    }
}
