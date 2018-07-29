package guru.springframework.recipedemo.recipedemo.bootstrap;

import guru.springframework.recipedemo.recipedemo.domain.*;
import guru.springframework.recipedemo.recipedemo.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class RecipesBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository rRepo;
    private CategoryRepository cRepo;
    private UnitOfMeasureRepository uRepo;

    public RecipesBootstrap(RecipeRepository rRepo, CategoryRepository cRepo, UnitOfMeasureRepository uRepo) {
        this.rRepo = rRepo;
        this.cRepo = cRepo;
        this.uRepo = uRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Optional<Category> catChicken = cRepo.findByName("Chicken");
        Optional<Category> catSalad = cRepo.findByName("Salad");
        Optional<Category> catPizza = cRepo.findByName("Pizza");
        Optional<Category> catVegan = cRepo.findByName("Vegan");
        Optional<UnitOfMeasure> uomPound = uRepo.findByName("Pound");
        Optional<UnitOfMeasure> uomOunce = uRepo.findByName("Ounce");
        Optional<UnitOfMeasure> uomPiece = uRepo.findByName("Piece");
        Optional<UnitOfMeasure> uomTeaspoon = uRepo.findByName("Teaspoon");
        Optional<UnitOfMeasure> uomTablespoon = uRepo.findByName("Tablespoon");

        Recipe r1 = new Recipe();
        r1.setDescription("Grilled Chicken Caesar Salad");
        r1.setPrepTime(10);
        r1.setCookTime(15);
        r1.setServings(4);
        r1.setUrl("https://www.simplyrecipes.com/recipes/grilled_chicken_caesar_salad/");
        r1.setDifficulty(Difficulty.EASY);
        r1.setDirections("1 Marinate the chicken: In a medium bowl, whisk together the olive oil, lemon zest, garlic, salt, and pepper. Add the chicken and toss to coat. Marinate in the refrigerator, covered, for at least 20 minutes and up to 24 hours. 2 Brush the bread with oil: Combine the olive oil, garlic, salt, and pepper to a small bowl. Use a pastry brush to coat each side of the bread. Set aside on a tray. 3 Heat your grill: Preheat your grill to high heat to around 500°F with two zones for direct and indirect grilling. 4 Grill the bread: Grill the slices of bread over direct high heat for 2 minutes on each side. Remove from the grill and set aside while grilling the chicken. 5 Grill the chicken: Grill the chicken for 5 minutes on one side without disturbing, then flip and move the chicken to the cooler side of the grill. Cook 3 to 5 minutes or until internal temperature reaches 165ºF. 6 Finish the salad: Tear the romaine hearts into bite sized pieces and toss with the dressing in a large bowl. Divide the greens between plates and top with the grilled chicken, shaved Parmesan, grilled croutons. Finish with freshly cracked black pepper.");

        r1.getIngredients().add(new Ingredient("Lemon", BigDecimal.ONE, r1, uomPiece.get()));
        r1.getIngredients().add(new Ingredient("Garlic", new BigDecimal("3"), r1, uomPiece.get()));
        r1.getIngredients().add(new Ingredient("Salt", BigDecimal.ONE, r1, uomTeaspoon.get()));
        r1.getIngredients().add(new Ingredient("Fresh ground black pepper", BigDecimal.ONE, r1, uomTeaspoon.get()));
        r1.getIngredients().add(new Ingredient("Boneless skinless chicken thighs", BigDecimal.ONE, r1, uomPound.get()));
        r1.getIngredients().add(new Ingredient("Olive oil", new BigDecimal("2"), r1, uomTablespoon.get()));
        r1.getIngredients().add(new Ingredient("Loaf of crusty baguette", BigDecimal.ONE, r1, uomPiece.get()));
        r1.getIngredients().add(new Ingredient("Romaine lettuce hearts", new BigDecimal("4"), r1, uomPiece.get()));
        r1.getIngredients().add(new Ingredient("Parmesan cheese", new BigDecimal("2"), r1, uomOunce.get()));
        r1.getIngredients().add(new Ingredient("Caesar dressing", new BigDecimal("2"), r1, uomOunce.get()));

        r1.getCategories().add(catChicken.get());
        r1.getCategories().add(catSalad.get());

        r1.setNote(new Note("Caesar salad is a classic. Crisp romaine, rich and garlicky dressing, crunchy croutons, and the pungent tang of good Parmesan—they come together to create a uniquely balanced salad. This version adds a smoky element with the grilled chicken and the grilled garlic croutons. This salad comes together quickly and easily with simple ingredients, many of which you probably already have on hand. I’m using boneless and skinless chicken thighs, which stay juicier than chicken breasts and cook very quickly. You can prepare the chicken ahead of time, either by marinating it up to 24 hours in advance or by grilling the chicken up to two days before and serving it as a cold salad. I use gluten-free sourdough to make my grilled croutons—which are basically slices of bread—but you can use any good crusty bread. Stale bread works great for croutons as it crisps up beautifully. If yours is very fresh, that’s fine too. You’ll get a crispy exterior with a softer interior. I typically serve this with 2 or 3 slices of grilled “croutons” per person. The chicken and croutons can be served hot, cold, or at room temperature. I like the contrast of the warm croutons and chicken with the crisp cold greens, but it’s great with cold chicken, too. Make sure your grill is hot before beginning to grill. Even a high-BTU gas grill needs at least 10 to 15 minutes to build up heat. If you have a thermometer on your grill, make sure it reads at least 500ºF. I like to grill the bread first, and then the chicken. That way I can watch each of them and also to make sure there is no cross contamination between the raw chicken and the lightly grilled bread. The bread only takes a couple of minutes and the chicken takes about 8 to 10 minutes depending on the size of the thighs.", r1));

        rRepo.save(r1);

        Recipe r2 = new Recipe();
        r2.setDescription("Grilled Pizza with Red Peppers, Broccoli, and Onions");
        r2.setPrepTime(20);
        r2.setCookTime(15);
        r2.setServings(2);
        r2.setUrl("https://www.simplyrecipes.com/recipes/grilled_pizza_with_red_peppers_broccoli_and_onions/");
        r2.setDifficulty(Difficulty.MODERATE);
        r2.setDirections("1 Divide and rest the dough: Divide the dough in half and gently shape into two balls. Gently coat each with olive oil and allow the dough to come to room temperature for about an hour, either in a large bowl or on the counter covered with a dishtowel. 2 Prep the toppings: Heat 1 tablespoon olive oil in a large skillet with a lid over medium heat.  Add the onions and peppers, season with salt and pepper, and sauté for 6 minutes, until tender and lightly browned. Add the chopped broccoli and sauté for another minute. Add 2 tablespoons water, and cover the pan with a lid. Cook for 1 minute, then remove the lid, and sauté for another minute or two until the broccoli is just tender and the water has evaporated. Stir in the olives and transfer the vegetables to a plate. 3 Shape the pizza rounds: Sprinkle a lightly oiled baking sheet with cornmeal or flour. Use two baking sheets if you want more space or are making multiple pizzas. Gently begin to stretch or roll each ball into a 12-inch circle, or a rectangle shape. You will need to stretch or roll it a bit, give it a few minutes to relax, and then continue to stretch it so that it doesn’t keep springing back into a smaller shape, which is easier to do when your dough is at room temperature. The goal is about to get it about 1/4 inch thick (it will puff up on the grill). Give it one final stretch and don’t worry if it’s unevenly shaped. 4 Heat the grill and gather your toppings: Heat a gas or charcoal grill to medium heat. Put your sauce in a small bowl, and bring that along with a brush, grill tongs, a grill spatula, the cheese, the dough, and the vegetables out to the grill. 5 Grill the dough on the first side: Brush the top of the shaped pizza rounds with olive oil, and with a swift motion, pick up one round of the dough by one edge and flip it, oiled-side down, onto the grill. Repeat with the other round of dough. Close the lid and don’t open it for 2 minutes, so the dough can rise a bit and firm up. Open the lid, peek underneath the dough using tongs, and check to see that it’s firm on the bottom (no wet dough), lifts easily, and has some light grill marks. Lightly brush the unbaked tops of the crusts with olive oil, and then flip them over to the other side. 6 Immediately add the sauce and toppings: Carefully brush the surface of each crust with the sauce. Sprinkle the cheese evenly over the tops followed by the toppings. Close the lid and cook for 4 minutes. Lift the lid and check the pizzas: the cheese should be completely melted and the crust should have a nicely browned underside and barely bend when you lift it with tongs. 7  Serve the pizzas: Remove the pizza from the grill with a large spatula, sprinkle with the fresh basil if using Let it sit for 1 to 2 minutes for the cheese to firm up slightly before cutting it into pieces.");

        r2.getIngredients().add(new Ingredient("Pizza dough", BigDecimal.ONE, r2, uomPound.get()));
        r2.getIngredients().add(new Ingredient("Olive oil", new BigDecimal("1.5"), r2, uomTablespoon.get()));
        r2.getIngredients().add(new Ingredient("Red onion", new BigDecimal("4"), r2, uomPiece.get()));
        r2.getIngredients().add(new Ingredient("Fresh ground black pepper", new BigDecimal("2"), r2, uomTeaspoon.get()));
        r2.getIngredients().add(new Ingredient("Salt", BigDecimal.ONE, r2, uomTeaspoon.get()));
        r2.getIngredients().add(new Ingredient("Chopped broccoli", new BigDecimal("4"), r2, uomPiece.get()));
        r2.getIngredients().add(new Ingredient("Black olive", new BigDecimal("0.25"), r2, uomPound.get()));
        r2.getIngredients().add(new Ingredient("Flour or cornmeal", new BigDecimal("4"), r2, uomTablespoon.get()));
        r2.getIngredients().add(new Ingredient("Tomato sauce", new BigDecimal("5"), r2, uomOunce.get()));
        r2.getIngredients().add(new Ingredient("Shredded mozarella cheese", new BigDecimal("8"), r2, uomOunce.get()));
        r2.getIngredients().add(new Ingredient("Slivered fresh basil leaves", BigDecimal.ONE, r2, uomOunce.get()));


        r2.getCategories().add(catPizza.get());
        r2.getCategories().add(catVegan.get());

        r2.setNote(new Note("Making pizza on the grill takes a little bit of courage the first time. Tossing uncooked dough directly onto the grate over live fire? Topping the pizza while it’s on the grill? Seems a bit daunting. But once you try it and see how delicious it is, you’ll go back for more. The good news? After a few times, you will be pretty much an expert at the whole thing, from getting the dough onto the grill, to flipping it, to adding your topping, and knowing when it’s done. Even better? Few grilled foods have this much “wow” factor as grilled pizza! The mere fact that you actually made a full-on pizza completely on the grill is impressive. The crust’s crunch and those lightly charred grill marks are delicious. If you want to go for the gold, you might top the pizza with some vegetables that you’ve grilled up earlier—now that’s making the most of your grill! In my house, this is how we celebrate grilling season pretty much every year, and it’s one of my most requested dishes of the summer from both family and friends—and you’ll see why!", r2));
        rRepo.save(r2);
    }
}
