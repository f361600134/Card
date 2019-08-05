package com.fatiny.core.client.actor;

import com.fatiny.core.akka.remote.ActorMessage;
import com.fatiny.core.akka.remote.RemoteFailure;

import akka.actor.ActorRef;

/**
 * Actor回调
 * 
 */
public class ActorCallback {
	
	private ActorRef sender;
	private CallbackTask callbackTask;

	// MethodCall.isOneWay()
	static ActorCallback create(ActorRef sender, boolean isOneWay) {
		ActorCallback callbackObj = new ActorCallback();
		if (!isOneWay) {
			callbackObj.sender = sender;
		}
		return callbackObj;
	}
	
	static ActorCallback create(ActorRef sender, CallbackTask task) {
		ActorCallback callbackObj = new ActorCallback(sender);
		callbackObj.callbackTask = task;
		return callbackObj;
	}
	
	private ActorCallback() {
		
	}
	
	public ActorCallback(ActorRef sender) {
		this.sender = sender;
	}

	public ActorRef getSender() {
		return sender;
	}

	public void onCall(ActorMessage response) {
		if (callbackTask != null) {
			callbackByActor(response);
		} else {
			callActorIfNotNull(response);
		}
	}
	
	
	private void callActorIfNotNull(ActorMessage response) {
		if (sender == null) return;
		Object returnVal = response.getMessage();
		if (returnVal instanceof RemoteFailure) {
			RemoteFailure obj = (RemoteFailure) returnVal;
			sender.tell(obj.statusFail(), ActorRef.noSender());
		} else {
			sender.tell(returnVal, ActorRef.noSender());
		}
	}
	
	
	private void callbackByActor(ActorMessage response) {
		Object returnVal = response.getMessage();
		callbackTask.response(returnVal);
		sender.tell(callbackTask, ActorRef.noSender());
	}
	
}
