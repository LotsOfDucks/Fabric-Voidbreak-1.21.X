package com.lotsofducks.voidbreak.block.custom;

import com.lotsofducks.voidbreak.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class BleedingBlock extends Block {
    public BleedingBlock(Settings settings) {
        super(settings);
    }

    boolean bl = false;
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = stack.getItem();
        if (stack.isOf(Items.GLASS_BOTTLE)) {
            stack.decrement(1);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, Blocks.STONE.getDefaultState() );
            if (stack.isEmpty()) {
                player.setStackInHand(hand, new ItemStack(ModItems.VOID_BOTTLE));
            } else if (!player.getInventory().insertStack(new ItemStack(ModItems.VOID_BOTTLE))) {
                player.dropItem(new ItemStack(ModItems.VOID_BOTTLE), false);
            }

            bl = true;
            world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
            return ItemActionResult.success(world.isClient);
        }
        if (!world.isClient() && bl) {
            player.incrementStat(Stats.USED.getOrCreateStat(item));
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}
