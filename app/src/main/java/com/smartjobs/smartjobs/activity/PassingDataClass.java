package com.smartjobs.smartjobs.activity;

public class PassingDataClass {

    private static String gId;
    private static String gIdAgentAvailability;

    public PassingDataClass() {
    }

    public static String getgId() {
        return gId;
    }

    public static void setgId(String gId) {
        PassingDataClass.gId = gId;
    }

    public static String getgIdAgentAvailability() {
        return gIdAgentAvailability;
    }

    public static void setgIdAgentAvailability(String gIdAgentAvailability) {
        PassingDataClass.gIdAgentAvailability = gIdAgentAvailability;
    }
}
