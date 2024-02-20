package net.coolsimulations.FloatingItems.mixin;

import net.coolsimulations.FloatingItems.FIConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import cpw.mods.fml.common.registry.GameRegistry;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {

    public EntityItemMixin(World worldIn) {
        super(worldIn);
    }

    @Shadow
    public abstract ItemStack getEntityItem();

    @Inject(at = @At("HEAD"), method = "onUpdate", cancellable = true)
    public void onUpdate(CallbackInfo info) {
        boolean shouldFloat = false;

        if (!getEntityItem().getItem()
            .onEntityItemUpdate(((EntityItem) (Object) this))) {
            ItemStack entityItem = getEntityItem();

            if (entityItem != null) {
                Item item = entityItem.getItem();

                if (item != null) {
                    boolean isValid = true;
                    String itemName = GameRegistry.findUniqueIdentifierFor(item)
                        .toString();
                    int itemMeta = entityItem.getItemDamage();

                    if (itemMeta != 0 && entityItem.getMaxDamage() == 0) {
                        itemName = itemName + ":" + itemMeta;
                    }

                    if (isValid) {
                        for (String blacklistName : FIConfig.blacklistItems) {
                            if (itemName.equals(blacklistName)) {
                                isValid = false;
                            }
                        }
                    }

                    if (isValid) {
                        for (String blacklistName : FIConfig.blacklistItems) {
                            int[] oreIDs = OreDictionary.getOreIDs(entityItem);
                            for (int oreID : oreIDs) {
                                String oreName = OreDictionary.getOreName(oreID);
                                if (oreName.equals(blacklistName)) {
                                    isValid = false;
                                }
                            }
                        }
                    }

                    if (isValid) {
                        shouldFloat = true;
                    }
                }
            }
        }

        if (shouldFloat) {

            Block state = this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ);
            float eye = this.getEyeHeight() - 0.11111111F;

            if ((state.getMaterial()
                .isLiquid() && state.getMaterial() != Material.lava)
                && BlockLiquid.getLiquidHeightPercent(
                    this.worldObj.getBlockMetadata(
                        MathHelper.floor_double(this.posX),
                        MathHelper.floor_double(this.posY),
                        MathHelper.floor_double(this.posZ)))
                    > eye) {
                setUnderWaterMovement(this);
                this.motionY += 0.03999999910593033D;
            }
        }
    }

    @Unique
    private static void setUnderWaterMovement(Entity entity) {
        entity.motionX *= 0.95D;
        entity.motionY += entity.motionY < 0.06D ? 5.0E-4D : 0.0D;
        entity.motionZ *= 0.95D;
    }
}
