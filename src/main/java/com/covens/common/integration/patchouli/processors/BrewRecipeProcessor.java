package com.covens.common.integration.patchouli.processors;

import java.util.Objects;

import com.covens.common.content.cauldron.CauldronRegistry;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextFormatting;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.api.PatchouliAPI;

public class BrewRecipeProcessor implements IComponentProcessor {

	private Ingredient brew;
	private Potion p;

	@Override
	public void setup(IVariableProvider<String> json) {
		this.p = Potion.getPotionFromResourceLocation(json.get("brew"));
		Objects.requireNonNull(this.p);
		this.brew = CauldronRegistry.getIngredientFromBrew(CauldronRegistry.getBrewFromPotion(this.p)).orElseThrow(() -> new IllegalArgumentException("Can't find ingredient"));
	}

	@Override
	public String process(String val) {
		try {
			if ("potionname".equals(val)) {
				return (this.p.isBeneficial() ? TextFormatting.DARK_BLUE : TextFormatting.DARK_RED) + TextFormatting.BOLD.toString() + I18n.format(this.p.getName());
			}
			if ("ingredient".equals(val)) {
				return PatchouliAPI.instance.serializeIngredient(this.brew);
			}
			if ("cost".equals(val)) {
				return I18n.format("covens.brew.cost", 10);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		throw new IllegalArgumentException("Unrecognized potion/brew value: " + val);
	}

}
