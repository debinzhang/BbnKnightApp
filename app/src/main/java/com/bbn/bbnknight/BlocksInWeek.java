package com.bbn.bbnknight;

import android.util.Log;

import java.util.ArrayList;

public class BlocksInWeek {
    public static ArrayList<ArrayList<BlockItem>> weekBlock = new ArrayList<>();

    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    public enum Block_Type {
        REGULAR, WITH_LAB_CONF, LAB_CONF, WITH_LUNCH, LUNCH, ASSEMBLY, ADVISORY, ACTIVITIES,
        FACULTY_MEETING, CLASS_MEETING
    }

    public static final String A_BLOCK  = "A Block";
    public static final String B_BLOCK  = "B Block";
    public static final String C_BLOCK  = "C Block";
    public static final String D_BLOCK  = "D Block";
    public static final String E_BLOCK  = "E Block";
    public static final String F_BLOCK  = "F Block";
    public static final String G_BLOCK  = "G Block";
    public static final String X_BLOCK  = "X Block";

    public static final String ASSEMBLY_BLOCK  = "Assembly";
    public static final String LUNCH_BLOCK  = "Lunch";
    public static final String ADVISORY_BLOCK  = "Advisory";
    public static final String ACTIVITIES_BLOCK = "Activities";

    public static class BlockItem {
        String name;
        Block_Type type;
        String start_time;
        String end_time;
        String alt_start_time;
        String alt_end_time;

        public BlockItem(String name, Block_Type type, String start_time, String end_time) {
            this.name = name;
            this.type = type;
            this.start_time = start_time;
            this.end_time = end_time;
        }

        public BlockItem(String name, Block_Type type, String start_time, String end_time,
                         String alt_start_time, String alt_end_time) {
            this(name, type, start_time, end_time);
            this.alt_start_time = alt_start_time;
            this.alt_end_time = alt_end_time;
        }

    };

    public static void initBlocks() {
        ArrayList<BlockItem> dayBlock;
        BlockItem blockItem;

        // Monday
        dayBlock = new ArrayList<BlockItem>();
        blockItem = new BlockItem(ASSEMBLY_BLOCK, Block_Type.ASSEMBLY, "8:00am", "8:15am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(D_BLOCK, Block_Type.REGULAR, "8:20am", "9:05am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.WITH_LAB_CONF, "9:10m", "10:00am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.LAB_CONF, "10:00m", "10:20am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(F_BLOCK, Block_Type.REGULAR, "10:25am", "11:10am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(A_BLOCK, Block_Type.WITH_LUNCH, "11:15am", "12:05pm",
                "11:15am", "11:40am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(A_BLOCK, Block_Type.LUNCH, "12:10pm", "12:35pm",
                "11:45am", "12:35am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(X_BLOCK, Block_Type.REGULAR, "12:40pm", "1:25pm");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(C_BLOCK, Block_Type.REGULAR, "1:30pm", "2:20pm");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(G_BLOCK, Block_Type.REGULAR, "2:25pm", "3:10pm");
        dayBlock.add(blockItem);
        weekBlock.add(dayBlock);

        // Tuesday
        dayBlock = new ArrayList<BlockItem>();
        blockItem = new BlockItem(ADVISORY_BLOCK, Block_Type.ADVISORY, "8:00am", "8:15am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(E_BLOCK, Block_Type.REGULAR, "8:20am", "9:05am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(C_BLOCK, Block_Type.WITH_LAB_CONF, "9:10m", "10:00am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(C_BLOCK, Block_Type.LAB_CONF, "10:00m", "10:20am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(D_BLOCK, Block_Type.REGULAR, "10:25am", "11:10am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.WITH_LUNCH, "11:15am", "12:05am",
                "11:15am", "11:40am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.LUNCH, "12:10am", "12:35am",
                "11:45am", "12:35am");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(X_BLOCK, Block_Type.REGULAR, "12:40am", "1:20pm");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(F_BLOCK, Block_Type.WITH_LAB_CONF, "1:25pm", "2:15pm");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(F_BLOCK, Block_Type.LAB_CONF, "2:15pm", "2:25pm");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(A_BLOCK, Block_Type.REGULAR, "2:40m", "3:25pm");
        dayBlock.add(blockItem);

        // Wednesday

        // Thursday


        // Fridday

    }
}
