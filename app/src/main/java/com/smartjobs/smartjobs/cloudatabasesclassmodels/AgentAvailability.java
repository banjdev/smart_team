package com.smartjobs.smartjobs.cloudatabasesclassmodels;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AgentAvailability {
    private String agentAvailability;

    public AgentAvailability() {
    }

    public AgentAvailability(String agentAvailability) {
        this.agentAvailability = agentAvailability;
    }

    public String getAgentAvailability() {
        return agentAvailability;
    }

    public void setAgentAvailability(String agentAvailability) {
        this.agentAvailability = agentAvailability;
    }
}
