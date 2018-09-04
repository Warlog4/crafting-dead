package com.craftingdead.mod.common.core;

import com.craftingdead.mod.common.server.LogicalServer;
import com.recastproductions.network.impl.client.NetClientHandler;

/**
 * Implemented by each side of the game, one for the client and one for the
 * server
 * 
 * @author Sm0keySa1m0n
 *
 * @param <L> - the {@link LogicalServer} associated with this side
 */
public interface ISidedMod<L extends LogicalServer, H extends NetClientHandler<?, ?>> {

	/**
	 * Called before minecraft is loaded, do any pre loading here
	 * 
	 * @param mod - the {@link CraftingDead} instance
	 */
	void setup(CraftingDead mod);

	/**
	 * Get the {@link LogicalServer} associated with this side
	 * 
	 * @return the server instance
	 */
	L getLogicalServer();

	/**
	 * Signals the shutdown of the game, do any shutdown tasks here, <b>runs in
	 * separate shutdown thread<b>
	 */
	void shutdown();

	H getNetHandler();

}