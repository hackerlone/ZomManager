package com.zom.cms.service.user;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zom.cms.dao.user.ITuIdDao;
import com.zom.cms.model.CmsException;
import com.zom.cms.model.TuId;


@Service("idService")
public class IdService implements IIdService {

		private static final Logger logger = Logger.getLogger(IdService.class);
	  
	  @Autowired
	  private ITuIdDao tuIdDao;    
	  
	@Override
	public Long getZoneCurUid(final int zoneId) {
		
		TuId tuId;
		 TuId tuId1;
		try {
                tuId = tuIdDao.load(zoneId);
                tuIdDao.updateUid(zoneId);
                tuId1 = tuIdDao.load(zoneId);
                if(tuId1.getCurUid().longValue()>=tuId.getMaxUid().longValue()){
               	 throw new CmsException("已到最大用户数，请联系零壹众科技有限公司");
                }
                if(tuId.getCurUid().longValue()!=tuId1.getCurUid().longValue()-1){
                	throw new CmsException("系统有竞争，生成失败");
                }
                



		} finally {
			
		}

		return  tuId1.getCurUid();
	}

	@Override
	public Long getZoneCurTgid(final int zoneId) {
		long tuid=0;
		TuId tuId;
		 TuId tuId1;
		try {
                tuId = tuIdDao.load(zoneId);
                tuIdDao.updateTgid(zoneId);
                tuId1 = tuIdDao.load(zoneId);
                if(tuId1.getCurTgid().longValue()>=tuId.getMaxTgid().longValue()){
               	 throw new CmsException("已到最大用户数，请联系零壹众科技有限公司");
               }
                if(tuId.getCurTgid().longValue()!=tuId1.getCurTgid().longValue()-1){
                	throw new CmsException("系统有竞争，生成失败");
                }
 
		} finally {
			
		}
		return tuId1.getCurTgid();
	}


}
