package com.craftingdead.mod.util;

import com.craftingdead.mod.CraftingDead;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {

  public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
      new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, CraftingDead.ID);

  public static final RegistryObject<SoundEvent> ACR_SHOOT = register("acr_shoot");
  public static final RegistryObject<SoundEvent> AK47_RELOAD = register("ak47_reload");
  public static final RegistryObject<SoundEvent> AK47_SHOOT = register("ak47_shoot");
  public static final RegistryObject<SoundEvent> AK47_SILENCED_SHOOT = register("ak47_silenced_shoot");
  public static final RegistryObject<SoundEvent> AS50_RELOAD = register("as50_reload");
  public static final RegistryObject<SoundEvent> AS50_SHOOT = register("as50_shoot");
  public static final RegistryObject<SoundEvent> AWP_RELOAD = register("awp_reload");
  public static final RegistryObject<SoundEvent> AWP_SHOOT = register("awp_shoot");
  public static final RegistryObject<SoundEvent> CROSSBOW_SHOOT = register("crossbow_shoot");
  public static final RegistryObject<SoundEvent> DESERT_EAGLE_SHOOT = register("desert_eagle_shoot");
  public static final RegistryObject<SoundEvent> DMR_RELOAD = register("dmr_reload");
  public static final RegistryObject<SoundEvent> DMR_SHOOT = register("dmr_shoot");
  public static final RegistryObject<SoundEvent> DRAGUNOV_SHOOT = register("dragunov_shoot");
  public static final RegistryObject<SoundEvent> DRY_FIRE = register("dry_fire");
  public static final RegistryObject<SoundEvent> FN57_RELOAD = register("fn57_reload");
  public static final RegistryObject<SoundEvent> FN57_SHOOT = register("fn57_shoot");
  public static final RegistryObject<SoundEvent> FNFAL_SHOOT = register("fnfal_shoot");
  public static final RegistryObject<SoundEvent> G18_SHOOT = register("g18_shoot");
  public static final RegistryObject<SoundEvent> G36C_SHOOT = register("g36c_shoot");
  public static final RegistryObject<SoundEvent> HK417_SHOOT = register("hk417_shoot");
  public static final RegistryObject<SoundEvent> M107_RELOAD = register("m107_reload");
  public static final RegistryObject<SoundEvent> M107_SHOOT = register("m107_shoot");
  public static final RegistryObject<SoundEvent> M1911_RELOAD = register("m1911_reload");
  public static final RegistryObject<SoundEvent> M1911_SHOOT = register("m1911_shoot");
  public static final RegistryObject<SoundEvent> M1GARAND_SHOOT = register("m1garand_shoot");
  public static final RegistryObject<SoundEvent> M240B_RELOAD = register("m240b_reload");
  public static final RegistryObject<SoundEvent> M240B_SHOOT = register("m240b_shoot");
  public static final RegistryObject<SoundEvent> M240B_SILENCED_SHOOT = register("m240b_silenced_shoot");
  public static final RegistryObject<SoundEvent> M4A1_RELOAD = register("m4a1_reload");
  public static final RegistryObject<SoundEvent> M4A1_SHOOT = register("m4a1_shoot");
  public static final RegistryObject<SoundEvent> M4A1_SILENCED_SHOOT = register("m4a1_silenced_shoot");
  public static final RegistryObject<SoundEvent> M9_RELOAD = register("m9_reload");
  public static final RegistryObject<SoundEvent> M9_SHOOT = register("m9_shoot");
  public static final RegistryObject<SoundEvent> M9_SILENCED_SHOOT = register("m9_silenced_shoot");
  public static final RegistryObject<SoundEvent> MAC10_SHOOT = register("mac10_shoot");
  public static final RegistryObject<SoundEvent> MAGNUM_RELOAD = register("magnum_reload");
  public static final RegistryObject<SoundEvent> MAGNUM_SHOOT = register("magnum_shoot");
  public static final RegistryObject<SoundEvent> MINIGUN_BARREL = register("minigun_barrel");
  public static final RegistryObject<SoundEvent> MINIGUN_SHOOT = register("minigun_shoot");
  public static final RegistryObject<SoundEvent> MK48MOD_SHOOT = register("mk48mod_shoot");
  public static final RegistryObject<SoundEvent> MK48MOD_SILENCED_SHOOT = register("mk48mod_silenced_shoot");
  public static final RegistryObject<SoundEvent> MOSSBERG_RELOAD = register("mossberg_reload");
  public static final RegistryObject<SoundEvent> MOSSBERG_SHOOT = register("mossberg_shoot");
  public static final RegistryObject<SoundEvent> MP5A5_RELOAD = register("mp5a5_reload");
  public static final RegistryObject<SoundEvent> MP5A5_SHOOT = register("mp5a5_shoot");
  public static final RegistryObject<SoundEvent> MP5A5_SILENCED_SHOOT = register("mp5a5_silenced_shoot");
  public static final RegistryObject<SoundEvent> MPT_SHOOT = register("mpt_shoot");
  public static final RegistryObject<SoundEvent> P250_SHOOT = register("p250_shoot");
  public static final RegistryObject<SoundEvent> P90_RELOAD = register("p90_reload");
  public static final RegistryObject<SoundEvent> P90_SHOOT = register("p90_shoot");
  public static final RegistryObject<SoundEvent> P90_SILENCED_SHOOT = register("p90_silenced_shoot");
  public static final RegistryObject<SoundEvent> RPK_RELOAD = register("rpk_reload");
  public static final RegistryObject<SoundEvent> RPK_SHOOT = register("rpk_shoot");
  public static final RegistryObject<SoundEvent> RPK_SILENCED_SHOOT = register("rpk_silenced_shoot");
  public static final RegistryObject<SoundEvent> SCARH_SHOOT = register("scarh_shoot");
  public static final RegistryObject<SoundEvent> SHOTGUN_RELOAD = register("shotgun_reload");
  public static final RegistryObject<SoundEvent> SPORTER22_SHOOT = register("sporter22_shoot");
  public static final RegistryObject<SoundEvent> TASER_SHOOT = register("taser_shoot");
  public static final RegistryObject<SoundEvent> TOGGLE_FIRE_MODE = register("toggle_fire_mode");
  public static final RegistryObject<SoundEvent> TRENCHGUN_SHOOT = register("trenchgun_shoot");
  public static final RegistryObject<SoundEvent> VECTOR_SHOOT = register("vector_shoot");
  public static final RegistryObject<SoundEvent> ZOOM_IN = register("zoom_in");

  private static RegistryObject<SoundEvent> register(String name) {
    ResourceLocation registryName = new ResourceLocation(CraftingDead.ID, name);
    return SOUND_EVENTS.register(name, () -> new SoundEvent(registryName));
  }
}
