package ua.my.dao;

import ua.my.model.Conference;
import ua.my.model.Group;

import java.util.List;

public interface ConferenceDAO {
    void add(Conference conference);
    void delete(long ids);
    List<Conference> list(Group group, int start, int count);
    List<Conference> list(String pattern);
    long count();

    Conference findConferenceForId(long id);
}
