package com.fatiny.core.akka.remote.netty3;

import java.util.TreeMap;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

public class MessageActor extends UntypedActor {
	
	private int index;
	private String remoteAddr;
	
	public MessageActor(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		int idx = actorIndex();
		ActorSelection selection = this.getContext().actorSelection(remoteAddr + idx);
		selection.tell(message, getSender());
	}
	
	
	public static void main(String[] args) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(1, 1);
		map.put(3, 3);
		map.put(5, 5);
		System.out.println(map.subMap(1, 0));
		System.out.println(map.subMap(1, true, 0, true));
	}
	
	private int actorIndex() {
		return Math.abs(++ index) % AkkaServer.ACTOR_COUNT;
	}
	
}
