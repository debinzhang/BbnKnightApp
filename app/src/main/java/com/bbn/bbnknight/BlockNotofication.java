package com.bbn.bbnknight;

public class BlockNotofication {

    private int[] blockNotification = new int[6];

    //1 for before block notification and no before block end notification
    //2 for before block end notification and no before block notification
    //3 for both before block and before block end notificaiton
    //4 for both no notification
    public BlockNotofication() {
        for(int i = 0; i < 6; i++) {
            blockNotification[i]=0;
        }
    }
    //0 for X Block
    //1 for Lunch
    //2 for activities
    //3 for advisory
    //4 for class meeting
    //5 for Assembly
    public void setBlockNotification(int activity, int changeType) {
        blockNotification[activity] = changeType;
    }

    public int getBlockNotification(int activity) {
        return blockNotification[activity];
    }

    public int[] getBlockNotificationArray() {
        return blockNotification;
    }
}
