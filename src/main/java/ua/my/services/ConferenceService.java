package ua.my.services;

import ua.my.model.Conference;
import ua.my.model.Group;

import java.util.List;

public interface ConferenceService {
    void addConference(Conference conference);
    void addGroup(Group group);
    void deleteConference(long[] ids);
    List<Group> listGroups();
    List<Conference> listConferences(Group group, int start, int count);
    List<Conference> listConferences(Group group);
    long count();
    Group findGroup(long id);
    List<Conference> searchConferences(String pattern);
    Conference findConferenceForId(long id);
}
