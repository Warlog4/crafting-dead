package com.craftingdead.mod.masterserver.message.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.craftingdead.mod.masterserver.session.PlayerSession;

import io.netty.buffer.ByteBuf;
import sm0keysa1m0n.network.message.Message;
import sm0keysa1m0n.network.message.MessageHandler;
import sm0keysa1m0n.network.util.ByteBufUtil;
import sm0keysa1m0n.network.wrapper.Session;

public class MessageUpdateNews implements Message {

	private static final Logger LOGGER = LogManager.getLogger();

	private List<String> news;

	public MessageUpdateNews() {
		;
	}

	public MessageUpdateNews(List<String> news) {
		this.news = new ArrayList<String>(news);
	}

	@Override
	public void toBytes(ByteBuf out) throws Exception {
		ByteBufUtil.writeStringArray(out, news.toArray(new String[0]));
	}

	@Override
	public void fromBytes(ByteBuf in) throws Exception {
		news = Arrays.asList(ByteBufUtil.readStringArray(in));
	}

	public static class MessageHandlerUpdateNews implements MessageHandler<MessageUpdateNews, Message> {

		@Override
		public Message processMessage(MessageUpdateNews msg, Session session) {
			if (session instanceof PlayerSession) {
				LOGGER.info("Updating news from master server");
				List<String> news = ((PlayerSession) session).getClient().getNews();
				news.clear();
				news.addAll(msg.news);
			}
			return null;
		}

	}

}