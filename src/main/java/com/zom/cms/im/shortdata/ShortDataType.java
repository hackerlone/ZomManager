package com.zom.cms.im.shortdata;

public interface ShortDataType {
	public static int SDInviteTG = 0x01;
	public static int SDJoinTG = 0x02;
	public static int SDQuitTG = 0x03;
	public static int SDKickoutTG = 0x04;
	public static int SDTGChange = 0x05;
	public static int SDProfileChange = 0x06;
	public static int SDAttachList = 0x07;
	public static int SDIntegrityComplete = 0x08;
	public static int SDTGAlert = 0x09;
	public static int SMS = 0x0A;  //This SMS type is used to send to test platform
}
