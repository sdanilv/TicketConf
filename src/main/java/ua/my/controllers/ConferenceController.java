package ua.my.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.my.logic.Logic;
import ua.my.model.Conference;
import ua.my.model.Group;
import ua.my.services.ConferenceService;


import static ua.my.controllers.GroupController.DEFAULT_GROUP_ID;

@Controller
public class ConferenceController {
    private static final int ITEMS_PER_PAGE = 6;

    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        long totalCount = conferenceService.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("groups", conferenceService.listGroups());
        model.addAttribute("conferences", conferenceService.listConferences(null, start, ITEMS_PER_PAGE));
        model.addAttribute("pages", pageCount);

        return "index";
    }

    @RequestMapping("/conf")
    public String conf(Model model, @RequestParam(value = "id") long id) {
        Conference conference = conferenceService.findConferenceForId(id);
        model.addAttribute("conference", conference);
        return "Conference";
    }

    @RequestMapping("/add_conf")
    public String contactAddPage(Model model) {
        model.addAttribute("groups", conferenceService.listGroups());
        return "Conference_add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", conferenceService.listGroups());
        model.addAttribute("conferences", conferenceService.searchConferences(pattern));
        return "index";
    }

    @RequestMapping(value = "/conference/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
            Logic.deletePhoto(conferenceService.findConferenceForId(id).getImage());
            conferenceService.deleteConference(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/conference/add", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String contactAdd(
//            @RequestParam(value = "group") long groupId,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int price,
            @RequestParam String date,
            @RequestParam String email,
            @RequestParam MultipartFile photo) {
//        Group group = (groupId != DEFAULT_GROUP_ID) ? conferenceService.findGroup(groupId) : null;

        String photoName = Logic.savePhoto(photo);

        Conference conference = new Conference(null, name, price, date, photoName, email, description);
        conferenceService.addConference(conference);

        return "redirect:/";
    }
}
