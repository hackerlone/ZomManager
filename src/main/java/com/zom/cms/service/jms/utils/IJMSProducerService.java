package com.zom.cms.service.jms.utils;

import javax.jms.Destination;

public interface IJMSProducerService {
	
	public void sendMessage(Destination destination, String message);
}
