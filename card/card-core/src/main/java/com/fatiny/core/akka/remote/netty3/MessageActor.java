package com.fatiny.core.akka.remote.netty3;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;

public class MessageActor extends AbstractActor {
	
	private int index;
	private String remoteAddr;
	
	public MessageActor(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	private int actorIndex() {
		return Math.abs(++ index) % AkkaServer.ACTOR_COUNT;
	}

	@Override
	public Receive createReceive() {
		int idx = actorIndex();
		ActorSelection selection = this.getContext().actorSelection(remoteAddr + idx);
		return receiveBuilder()
				.matchEquals("Ping", s -> System.out.println("It's Ping: " + s))
				.matchAny(o -> {
					selection.tell(o, sender());
				}).build();
	}
	
}
