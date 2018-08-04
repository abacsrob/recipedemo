package guru.springframework.recipedemo.recipedemo.controllers;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.service.CategoryService;
import guru.springframework.recipedemo.recipedemo.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    RecipeService recipeService;

    CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("categories", categoryService.getCategoryCommands());
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String modifyRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryService.getCategoryCommands());
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        return "recipe/show";
    }

//    @RequestMapping(name = "recipe", method = RequestMethod.POST)
    @PostMapping
    @RequestMapping("recipe")
    public String saveUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "index";
    }
}
