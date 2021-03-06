package com.fabbe50.compressedblocks.utility;

import com.fabbe50.compressedblocks.utility.LogHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import java.util.List;

/**
 * Created by fabbe50 on 03/02/2016.
 */
public class RecipeRemoval {
    @SuppressWarnings("unchecked")
    public static void RemoveRecipe(String toDelete)
    {
        ItemStack resultItem = new ItemStack((Item)Item.itemRegistry.getObject(toDelete));
        resultItem.stackSize = 1;
        resultItem.setItemDamage(0);

        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

        for (int i = 0; i < recipes.size(); i++)
        {
            IRecipe tmpRecipe = recipes.get(i);

            ItemStack recipeResult = tmpRecipe.getRecipeOutput();
            if(recipeResult != null)
            {
                recipeResult.stackSize = 1;
                recipeResult.setItemDamage(0);
            }

            if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
            {
                LogHelper.info("Removing recipe for: " + resultItem);
                recipes.remove(i--);
            }
        }
    }
}
