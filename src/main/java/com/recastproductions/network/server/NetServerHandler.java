package com.recastproductions.network.server;

import javax.annotation.Nullable;

import com.recastproductions.network.NetHandler;
import com.recastproductions.network.Session;
import com.recastproductions.network.packet.IHandshakePacket;

import io.netty.channel.Channel;

/**
 * An interface used to handle server side logic for authenticated clients or
 * clients attempting to authenticate
 * 
 * @author Sm0keySa1m0n
 *
 * @param <HS>
 *            - the {@link IHandshakePacket} that this {@link INetServerHandler}
 *            listens for
 */
public abstract class NetServerHandler<HS extends IHandshakePacket, S extends Session<?>> extends NetHandler<HS> {

	/**
	 * Processes a handshake sent by the client
	 * 
	 * @param packet
	 *            - the handshake itself
	 * @param ch
	 *            - the channel sending the handshake
	 * @return if the client is allowed to send any more handshakes
	 */
	@Nullable
	public abstract S processHandshake(HS packet, Channel ch);

}