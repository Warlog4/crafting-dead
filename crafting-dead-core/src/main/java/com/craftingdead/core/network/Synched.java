/*
 * Crafting Dead
 * Copyright (C) 2021  NexusNode LTD
 *
 * This Non-Commercial Software License Agreement (the "Agreement") is made between you (the "Licensee") and NEXUSNODE (BRAD HUNTER). (the "Licensor").
 * By installing or otherwise using Crafting Dead (the "Software"), you agree to be bound by the terms and conditions of this Agreement as may be revised from time to time at Licensor's sole discretion.
 *
 * If you do not agree to the terms and conditions of this Agreement do not download, copy, reproduce or otherwise use any of the source code available online at any time.
 *
 * https://github.com/nexusnode/crafting-dead/blob/1.18.x/LICENSE.txt
 *
 * https://craftingdead.net/terms.php
 */

package com.craftingdead.core.network;

import io.netty.buffer.Unpooled;
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public interface Synched {

  void encode(FriendlyByteBuf out, boolean writeAll);

  void decode(FriendlyByteBuf in);

  boolean requiresSync();

  default void encodeNbt(CompoundTag nbt, boolean writeAll) {
    FriendlyByteBuf capabilityData = new FriendlyByteBuf(Unpooled.buffer());
    this.encode(capabilityData, writeAll);
    byte[] capabilityDataBytes = new byte[capabilityData.readableBytes()];
    capabilityData.readBytes(capabilityDataBytes);
    nbt.put("__FORGE_CAPABILITY__", new ByteArrayTag(capabilityDataBytes));
  }

  default void decodeNbt(CompoundTag nbt) {
    this.decode(new FriendlyByteBuf(Unpooled.wrappedBuffer(nbt.getByteArray("__FORGE_CAPABILITY__"))));
  }
}
