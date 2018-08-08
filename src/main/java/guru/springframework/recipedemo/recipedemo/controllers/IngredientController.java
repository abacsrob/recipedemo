package guru.springframework.recipedemo.recipedemo.controllers;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.service.IngredientService;
import guru.springframework.recipedemo.recipedemo.service.RecipeService;
import guru.springframework.recipedemo.recipedemo.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable Long recipeId, Model model) {
        log.debug("Executing IngredientController.listIngredients: recipeId = " + recipeId);
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        log.debug("Executing IngredientController.showIngredient: recipeId = " + recipeId + "; id = " + id);
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        log.debug("Executing IngredientController.updateRecipeIngredient: recipeId = " + recipeId + "; id = " + id);
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        model.addAttribute("uomList", unitOfMeasureService.getAllUoms());
        return "recipe/ingredient/form";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        log.debug("Saved recipe id = " + savedCommand.getRecipeId());
        log.debug("Saved ingredient id = " + savedCommand.getId());
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
