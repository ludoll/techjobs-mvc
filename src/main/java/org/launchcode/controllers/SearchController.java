package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "/results")
    public String searchByType(Model model, @RequestParam String searchType,
                               @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);

        switch (searchType) {
            case "all":
                if (searchTerm.isEmpty()) {
                    model.addAttribute("searchData", JobData.findAll());
                } else {
                    model.addAttribute("searchData", JobData.findByValue(searchTerm));
                }

                break;
            case "position type":
            case "core competency":
            case "employer":
            case "location":
                model.addAttribute("searchData", JobData.findByColumnAndValue(searchType, searchTerm));
                break;


        }
        return "search";
        // TODO #1 - Create handler to process search request and display results

    }
}
