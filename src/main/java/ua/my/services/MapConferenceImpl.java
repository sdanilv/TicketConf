package ua.my.services;

import org.springframework.stereotype.Component;
import ua.my.model.Conference;
import ua.my.model.Group;

import java.util.*;

@Component
public class MapConferenceImpl implements ConferenceService {
    private final Map<Long , Conference> conferences =  new HashMap<>();
    private final ArrayList <Group>  groups = new ArrayList<>();
    long id;

    @Override
    public void addConference(Conference conference) {
        conference.setId(++id);
        conferences.put(conference.getId(), conference);
    }

    @Override
    public void addGroup(Group group) {
groups.add(group);
    }

    @Override
    public void deleteConference(long[] ids) {
        for (long id : ids) {
            conferences.remove(id);
        }
    }

    @Override
    public List<Group> listGroups() {
        return groups;
    }

    @Override
    public List<Conference> listConferences(Group group, int start, int count) {

        return new ArrayList<Conference>(conferences.values());
    }

    @Override
    public List<Conference> listConferences(Group group) {
        return group.getConferences();
    }

    @Override
    public long count() {
        return conferences.size();
    }

    @Override
    public Group findGroup(long id) {
        for (Group group : groups) {
            if(group.getId()==id)
                return group;
        }
        return null;
    }

    @Override
    public List<Conference> searchConferences(String pattern) {
        return null;
    }

    @Override
    public Conference findConferenceForId(long id) {
        return conferences.get(id);
    }
}
