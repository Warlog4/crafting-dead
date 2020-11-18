/**
 * Crafting Dead
 * Copyright (C) 2020  Nexus Node
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.craftingdead.immerse.game.survival;

import com.craftingdead.core.CraftingDead;
import com.craftingdead.immerse.game.ITeam;
import net.minecraft.util.ResourceLocation;

public class SurvivorsTeam implements ITeam {

  static final SurvivorsTeam INSTANCE = new SurvivorsTeam();

  @Override
  public ResourceLocation getId() {
    return new ResourceLocation(CraftingDead.ID, "survivors");
  }
}
