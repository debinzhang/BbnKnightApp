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
        int blockImage;

        void setBlockImage() {
            if(this.type == Block_Type.LAB_CONF) {
                this.blockImage = R.drawable.lab;
            } else {
                switch (this.name) {
                    case A_BLOCK:
                        this.blockImage = R.drawable.letter_a_lg_icon;
                        break;
                    case B_BLOCK:
                        this.blockImage = R.drawable.letter_b_pink_icon;
                        break;
                    case C_BLOCK:
                        this.blockImage = R.drawable.letter_c_orange_icon;
                        break;
                    case D_BLOCK:
                        this.blockImage = R.drawable.letter_d_blue_icon;
                        break;
                    case E_BLOCK:
                        this.blockImage = R.drawable.letter_e_blue_icon;
                        break;
                    case F_BLOCK:
                        this.blockImage = R.drawable.letter_f_red_icon;
                        break;
                    case G_BLOCK:
                        this.blockImage = R.drawable.letter_g_violet_icon;
                        break;
                    case X_BLOCK:
                        this.blockImage = R.drawable.letter_x_dg_icon;
                        break;
                    case LUNCH_BLOCK:
                        this.blockImage = R.drawable.breakfast_icon;
                        break;
                    case ASSEMBLY_BLOCK:
                        this.blockImage = R.drawable.assembly;
                        break;
                    case ADVISORY_BLOCK:
                        this.blockImage = R.drawable.advisory;
                        break;
                    case ACTIVITIES_BLOCK:
                        this.blockImage = R.drawable.activity;
                        break;
                }
            }
        }

        public BlockItem(BlockItem block) {
            this.name = block.name;
            this.type = block.type;
            this.start_time = block.start_time;
            this.end_time = block.end_time;
            this.alt_start_time = block.alt_start_time;
            this.alt_end_time = block.alt_end_time;
            this.blockImage = block.blockImage;
        }

        public BlockItem(String name, Block_Type type, String start_time, String end_time) {
            this.name = name;
            this.type = type;
            this.start_time = start_time;
            this.end_time = end_time;
            setBlockImage();
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
        blockItem = new BlockItem(ASSEMBLY_BLOCK, Block_Type.ASSEMBLY, "8:00AM", "8:15AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(D_BLOCK, Block_Type.REGULAR, "8:20AM", "9:05AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.WITH_LAB_CONF, "9:10AM", "10:00AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.LAB_CONF, "10:00AM", "10:20AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(F_BLOCK, Block_Type.REGULAR, "10:25AM", "11:10AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(A_BLOCK, Block_Type.WITH_LUNCH, "11:15AM", "12:05PM",
                "11:15AM", "11:40AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(A_BLOCK, Block_Type.LUNCH, "12:10PM", "12:35PM",
                "11:45AM", "12:35AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(X_BLOCK, Block_Type.REGULAR, "12:40PM", "1:25PM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(C_BLOCK, Block_Type.REGULAR, "1:30PM", "2:20PM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(G_BLOCK, Block_Type.REGULAR, "2:25PM", "3:10PM");
        dayBlock.add(blockItem);
        weekBlock.add(dayBlock);

        // Tuesday
        dayBlock = new ArrayList<BlockItem>();
        blockItem = new BlockItem(ADVISORY_BLOCK, Block_Type.ADVISORY, "8:00AM", "8:15AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(E_BLOCK, Block_Type.REGULAR, "8:20AM", "9:05AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(C_BLOCK, Block_Type.WITH_LAB_CONF, "9:10AM", "10:00AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(C_BLOCK, Block_Type.LAB_CONF, "10:00AM", "10:20AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(D_BLOCK, Block_Type.REGULAR, "10:25AM", "11:10AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.WITH_LUNCH, "11:15AM", "12:05PM",
                "11:15AM", "11:40AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(B_BLOCK, Block_Type.LUNCH, "12:10AM", "12:35AM",
                "11:45AM", "12:35AM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(X_BLOCK, Block_Type.REGULAR, "12:40AM", "1:20PM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(F_BLOCK, Block_Type.WITH_LAB_CONF, "1:25PM", "2:15PM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(F_BLOCK, Block_Type.LAB_CONF, "2:15PM", "2:25PM");
        dayBlock.add(blockItem);
        blockItem = new BlockItem(A_BLOCK, Block_Type.REGULAR, "2:40PM", "3:25PM");
        dayBlock.add(blockItem);
        weekBlock.add(dayBlock);


        // Wednesday

        // Thursday


        // Fridday

    }
}
