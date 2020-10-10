package com.robotgryphon.compactcrafting.recipes.layers;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.Collection;
import java.util.Map;

public interface IRecipeLayer {

    Map<String, Integer> getComponentTotals(AxisAlignedBB recipeDims);

    /**
     * Gets a component key for the given (normalized) position.
     * @param pos
     * @return
     */
    String getRequiredComponentKeyForPosition(BlockPos pos);

    /**
     * Gets a set of non-air positions that are required for the layer to match.
     * This is expected to trim the air positions off the edges and return the positions with NW
     * in the 0, 0 position.
     *
     * @return
     */
    Collection<BlockPos> getNonAirPositions(AxisAlignedBB recipeDims);

    boolean isPositionRequired(BlockPos pos);

    int getNumberFilledPositions(AxisAlignedBB recipeDims);
}