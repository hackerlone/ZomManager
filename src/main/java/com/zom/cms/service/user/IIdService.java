package com.zom.cms.service.user;


public interface IIdService {
 
	public Long  getZoneCurUid(final int zoneId);
//	public int  writeZoneMaxUid(final int zoneId,final Long id);	
	public Long  getZoneCurTgid(final int zoneId);
//	public int  writeZoneMaxTgid(final int zoneId, final Long id);	
    
}
