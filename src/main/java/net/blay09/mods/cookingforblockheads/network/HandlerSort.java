package net.blay09.mods.cookingforblockheads.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.blay09.mods.cookingforblockheads.container.ComparatorHunger;
import net.blay09.mods.cookingforblockheads.container.ComparatorName;
import net.blay09.mods.cookingforblockheads.container.ComparatorSaturation;
import net.blay09.mods.cookingforblockheads.container.ContainerRecipeBook;
import net.minecraft.inventory.Container;

public class HandlerSort implements IMessageHandler<MessageSort, IMessage> {

    @Override
    public IMessage onMessage(MessageSort message, MessageContext ctx) {
        Container container = ctx.getServerHandler().playerEntity.openContainer;
        if(container instanceof ContainerRecipeBook) {
            switch(message.getSortingId()) {
                case 0:
                    ((ContainerRecipeBook) container).sortRecipes(new ComparatorName());
                    break;
                case 1:
                    ((ContainerRecipeBook) container).sortRecipes(new ComparatorHunger(ctx.getServerHandler().playerEntity));
                    break;
                case 2:
                    ((ContainerRecipeBook) container).sortRecipes(new ComparatorSaturation(ctx.getServerHandler().playerEntity));
                    break;
            }
        }
        return null;
    }

}