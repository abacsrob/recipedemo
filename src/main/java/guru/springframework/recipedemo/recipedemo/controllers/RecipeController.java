package guru.springframework.recipedemo.recipedemo.controllers;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.service.CategoryService;
import guru.springframework.recipedemo.recipedemo.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    RecipeService recipeService;

    CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        log.debug("Executing RecipeController.newRecipe");
        model.addAttribute("categories", categoryService.getCategoryCommands());
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String modifyRecipe(@PathVariable Long id, Model model) {
        log.debug("Executing RecipeController.modifyRecipe: id = " + id);
        model.addAttribute("categories", categoryService.getCategoryCommands());
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        log.debug("Executing RecipeController.showById: id = " + id);
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        return "recipe/show";
    }

//    @RequestMapping(name = "recipe", method = RequestMethod.POST)
    @PostMapping
    @RequestMapping("recipe")
    public String saveUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        log.debug("Executing RecipeController.saveUpdateRecipe");
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        log.debug("Executing RecipeController.deleteRecipe: id =" + id);
        recipeService.deleteRecipe(id);
        return "redirect:/";
    }
}
