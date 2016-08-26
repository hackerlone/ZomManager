package com.zom.cms.lh.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.jeecgframework.poi.excel.export.template.ExcelExportOfTemplateUtil;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层com.zom.cms.lh.utils/ExcleUtils.java <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 王家明 <p>
 * <strong> 编写时间：</strong>2016年8月15日 上午9:56:58<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
public class ExcelUtils {
	 public static Workbook exportExcel(List<Map<String, Object>> list,ExcelType hssf){
		Workbook workbook = null;
		if(ExcelType.HSSF.equals(hssf)){
			workbook = new HSSFWorkbook();
		} else{
			workbook = new XSSFWorkbook();
		}
		for(Map<String, Object>map : list){
			ExcelExportServer server = new ExcelExportServer();
			server.createSheet(workbook, (ExportParams)map.get("title"), (Class<?>)map.get("entity")
					, (Collection<?>)map.get("data"));
		}
		return workbook;
	 }
}
