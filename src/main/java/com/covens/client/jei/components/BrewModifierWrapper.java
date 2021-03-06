package com.covens.client.jei.components;

import java.util.ArrayList;
import java.util.Arrays;

import com.covens.api.cauldron.IBrewModifier;
import com.covens.common.content.cauldron.BrewData;
import com.covens.common.content.cauldron.BrewData.BrewEntry;
import com.covens.common.content.cauldron.BrewModifierListImpl;
import com.covens.common.content.cauldron.CauldronRegistry;
import com.covens.common.item.ModItems;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class BrewModifierWrapper implements IRecipeWrapper {

	private ItemStack[] in;
	private ArrayList<ItemStack> validBrews = new ArrayList<ItemStack>(CauldronRegistry.BREW_POTION_MAP.size());
	private String name;

	public BrewModifierWrapper(IBrewModifier modifier) {
		this.in = modifier.getJEIStackRepresentative().getMatchingStacks();
		this.name = "modifier.description." + modifier.getRegistryName().toString().replace(':', '.');
		CauldronRegistry.BREW_POTION_MAP.forEach((brew, potion) -> this.addStackFor(potion));
	}

	private void addStackFor(Potion potion) {
		ItemStack stack = new ItemStack(ModItems.brew_phial_drink);
		BrewData bd = new BrewData();
		bd.addEntry(new BrewEntry(potion, new BrewModifierListImpl()));
		bd.saveToStack(stack);
		this.validBrews.add(stack);
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, Arrays.asList(Arrays.asList(this.in)));
		ingredients.setOutputLists(VanillaTypes.ITEM, Arrays.asList(this.validBrews));
	}

	@Override
	public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		FontRenderer fr = mc.fontRenderer;
		String res = I18n.format(this.name);
		fr.drawString(res, (recipeWidth - fr.getStringWidth(res)) / 2, recipeHeight - fr.FONT_HEIGHT, 0, false);
	}

}
