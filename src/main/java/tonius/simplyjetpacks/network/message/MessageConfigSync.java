package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.meta.PackBase;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage>
{
	public NBTTagCompound recv;

	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound toSend = new NBTTagCompound();
		PackBase.writeAllConfigsToNBT(toSend);
		ByteBufUtils.writeTag(buf, toSend);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.recv = ByteBufUtils.readTag(buf);
	}

	@Override
	public IMessage onMessage(MessageConfigSync msg, MessageContext ctx)
	{
		PackBase.readAllConfigsFromNBT(msg.recv);
		SimplyJetpacks.logger.info("Received server configuration");
		return null;
	}
}
