package guru.springframework.recipedemo.recipedemo.controllers;

import guru.springframework.recipedemo.recipedemo.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {
        log.debug("Index Controller: get index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        model.addAttribute("sortedRecipes", recipeService.getSortedRecipes(sort));
        return "index";
    }
}
