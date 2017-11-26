package ua.my.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.my.model.Conference;
import ua.my.model.Group;
import ua.my.services.ConferenceService;
import ua.my.services.MapConferenceImpl;

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

    @RequestMapping("/contact_add_page")
    public String contactAddPage(Model model) {
        model.addAttribute("groups", conferenceService.listGroups());
        return "contact_add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", conferenceService.listGroups());
        model.addAttribute("conferences", conferenceService.searchConferences(pattern));
        return "index";
    }

    @RequestMapping(value = "/conference/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            conferenceService.deleteConference(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/conference/add", method = RequestMethod.POST)
    public String contactAdd(@RequestParam(value = "group") long groupId,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam int price,
                             @RequestParam String date,
                             @RequestParam String image,
                             @RequestParam String email) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? conferenceService.findGroup(groupId) : null;

        Conference conference = new Conference(group, name, price, date, image, email, description);
        conferenceService.addConference(conference);

        return "redirect:/";
    }
}
