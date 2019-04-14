package com.smartjobs.smartjobs.model;


//This class is not going to be used to get info from the cloud database. This is why is is not annotated with IgnoreExtraProperties
import com.google.firebase.database.IgnoreExtraProperties;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.AgentAvailability;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.MoreInfoOnAgent;
import com.smartjobs.smartjobs.cloudatabasesclassmodels.Users;


@IgnoreExtraProperties
public class Agent  {

    Users users;
    MoreInfoOnAgent moreInfoOnAgent;
    AgentAvailability agentAvailablity;

    public Agent() {
    }

    public Agent(Users users, MoreInfoOnAgent moreInfoOnAgent, AgentAvailability agentAvailablity) {
        this.users = users;
        this.moreInfoOnAgent = moreInfoOnAgent;
        this.agentAvailablity = agentAvailablity;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public MoreInfoOnAgent getMoreInfoOnAgent() {
        return moreInfoOnAgent;
    }

    public void setMoreInfoOnAgent(MoreInfoOnAgent moreInfoOnAgent) {
        this.moreInfoOnAgent = moreInfoOnAgent;
    }

    public AgentAvailability getAgentAvailablity() {
        return agentAvailablity;
    }

    public void setAgentAvailablity(AgentAvailability agentAvailablity) {
        this.agentAvailablity = agentAvailablity;
    }
}
