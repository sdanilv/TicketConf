package ua.my.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.my.dao.ConferenceDAO;
import ua.my.dao.GroupDAO;
import ua.my.model.Conference;
import ua.my.model.Group;

import java.util.List;


@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Autowired
    private ConferenceDAO conferenceDAO;
    @Autowired
    private GroupDAO groupDAO;

    @Transactional
    public Conference findConferenceForId(long id) {
        return conferenceDAO.findConferenceForId(id);
    }

    @Transactional
    public void addConference(Conference conference) {
        conferenceDAO.add(conference);
    }

    @Transactional
    public void addGroup(Group group) {
        groupDAO.add(group);
    }

    @Transactional
    public void deleteConference(long[] ids) {
        conferenceDAO.delete(ids);
    }

    @Transactional(readOnly = true)
    public List<Group> listGroups() {
        return groupDAO.list();
    }

    @Transactional(readOnly = true)
    public List<Conference> listConferences(Group group, int start, int count) {
        return conferenceDAO.list(group, start, count);
    }

    @Transactional(readOnly = true)
    public List<Conference> listConferences(Group group) {
        return conferenceDAO.list(group, 0, 0);
    }

    @Transactional(readOnly = true)
    public long count() {
        return conferenceDAO.count();
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Conference> searchConferences(String pattern) {
        return conferenceDAO.list(pattern);
    }
}
