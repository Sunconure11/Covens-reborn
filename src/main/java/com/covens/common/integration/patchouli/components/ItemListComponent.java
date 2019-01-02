package com.covens.common.integration.patchouli.components;

import java.util.List;

import com.covens.common.integration.patchouli.Patchouli;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.crafting.Ingredient;
import vazkii.patchouli.api.IComponentRenderContext;
import vazkii.patchouli.api.ICustomComponent;
import vazkii.patchouli.api.VariableHolder;

public class ItemListComponent implements ICustomComponent {

	private int x, y;

	@VariableHolder
	public int slots;

	@VariableHolder
	public String registry;

	@VariableHolder
	public String name;

	@VariableHolder
	public String list_type;

	private transient List<Ingredient> ingredients;

	@Override
	public void build(int componentX, int componentY, int pageNum) {
		this.x = componentX;
		this.y = componentY;
		this.ingredients = Patchouli.getInputsFromRegistry(this.registry, this.name, this.list_type);
	}

	@Override
	public void render(IComponentRenderContext context, float pticks, int mouseX, int mouseY) {
		if (this.ingredients.size() > 0) {
			int remaining = this.ingredients.size();
			int row = 0;
			int border = this.slots <= this.ingredients.size() ? 0 : ((this.slots - this.ingredients.size()) * 17) / 2;
			GlStateManager.pushMatrix();
			while (remaining > 0) {
				for (int i = 0; i < Math.min(this.slots, remaining); i++) {
					context.renderIngredient(border + this.x + (17 * i), this.y + (17 * row), mouseX, mouseY, this.ingredients.get((row * this.slots) + i));
				}
				row++;
				remaining -= this.slots;
			}
			GlStateManager.popMatrix();
		}

	}

}
