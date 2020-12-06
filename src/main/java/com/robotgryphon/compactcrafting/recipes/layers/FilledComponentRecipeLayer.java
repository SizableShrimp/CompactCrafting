package com.robotgryphon.compactcrafting.recipes.layers;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class FilledComponentRecipeLayer implements IRecipeLayer, IDynamicRecipeLayer {

    private String componentKey;
    private AxisAlignedBB recipeDimensions;

    public FilledComponentRecipeLayer(String component) {
        this.componentKey = component;
    }

    @Override
    public Map<String, Integer> getComponentTotals() {
        return Collections.singletonMap(componentKey, getNumberFilledPositions());
    }

    @Override
    public String getRequiredComponentKeyForPosition(BlockPos pos) {
        return componentKey;
    }

    /**
     * Gets a set of non-air positions that are required for the layer to match.
     * This is expected to trim the air positions off the edges and return the positions with NW
     * in the 0, 0 position.
     *
     * @return
     */
    @Override
    public Collection<BlockPos> getNonAirPositions() {
        AxisAlignedBB layerBounds = new AxisAlignedBB(0, 0, 0, recipeDimensions.getXSize(), 1, recipeDimensions.getZSize());
        return BlockPos.getAllInBox(layerBounds)
                .parallel()
                .map(BlockPos::toImmutable)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isPositionRequired(BlockPos pos) {
        return true;
    }

    @Override
    public int getNumberFilledPositions() {
        return (int) Math.ceil(recipeDimensions.getXSize() * recipeDimensions.getYSize());
    }

    public void setComponent(String component) {
        this.componentKey = component;
    }

    /**
     * Used to update a recipe layer to change the size of the recipe base.
     *
     * @param dimensions The new dimensions of the recipe.
     */
    @Override
    public void setRecipeDimensions(AxisAlignedBB dimensions) {
        this.recipeDimensions = dimensions;
        this.recalculateRequirements();
    }

    /**
     * Used to recalculate dynamic-sized recipe layers. Expected to be called
     * any time components or base recipe dimensions change.
     */
    @Override
    public void recalculateRequirements() {

    }
}
