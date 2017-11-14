package ua.my.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.my.model.Group;
import ua.my.services.ConferenceService;

@Controller
public class GroupController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping(value="/group/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name) {
        conferenceService.addGroup(new Group(name));
        return "redirect:/";
    }

    @RequestMapping("/group/{id}")
    public String listGroup(@PathVariable(value = "id") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? conferenceService.findGroup(groupId) : null;

        model.addAttribute("groups", conferenceService.listGroups());
        model.addAttribute("conferences", conferenceService.listConferences(group));

        return "index";
    }
}
