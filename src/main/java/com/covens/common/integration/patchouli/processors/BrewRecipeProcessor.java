package com.covens.common.integration.patchouli.processors;

import com.covens.common.content.cauldron.CauldronRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextFormatting;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.Objects;

public class BrewRecipeProcessor implements IComponentProcessor {

	Ingredient brew;
	Potion p;

	@Override
	public void setup(IVariableProvider<String> json) {
		p = Potion.getPotionFromResourceLocation(json.get("brew"));
		Objects.requireNonNull(p);
		brew = CauldronRegistry.getIngredientFromBrew(CauldronRegistry.getBrewFromPotion(p)).orElseThrow(() -> new NullPointerException("Can't find ingredient"));
	}

	@Override
	public String process(String val) {
		try {
			if (val.equals("potionname")) {
				return (p.isBeneficial()?TextFormatting.DARK_BLUE:TextFormatting.DARK_RED)+TextFormatting.BOLD.toString()+I18n.format(p.getName());
			}
			if (val.equals("ingredient")) {
				return PatchouliAPI.instance.serializeIngredient(brew);
			} 
			if (val.equals("cost")) {
				return I18n.format("covens.brew.cost", 10);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		throw new RuntimeException("Unrecognized potion/brew value: " + val);
	}

}