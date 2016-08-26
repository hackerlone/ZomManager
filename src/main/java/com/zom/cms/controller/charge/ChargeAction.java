package com.zom.cms.controller.charge;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zom.cms.lh.tools.ActionUtil;
import com.zom.cms.model.User;


@Controller
public class ChargeAction {
	
	@RequestMapping(value = "/back/charge.html")
	public ModelAndView backCharge(ModelMap modelMap,HttpServletRequest request) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/charge/charge", modelMap);
	}
	
	
	
//	@RequestMapping(value = "/charge/index.html", method = RequestMethod.GET)
//	public ModelAndView chargeIndex(ModelMap modelMap,HttpServletRequest request) {
//		String path = PagePath.login;
//		try {
//			Object userIdObj = request.getSession().getAttribute("userId");
//			if(null != userIdObj){
//				Integer userId = (Integer)userIdObj;
//				Map<String,Object> map = new HashMap<String,Object>();
//				map.put("userId", userId);
//				User user = userService.getUserById(userId);
//				Double frozenMoney = user.getFrozenMoney();
//				Double incomeMoney = user.getIncomeMoney();
//				Double money = user.getMoney();
//				if(null == frozenMoney)frozenMoney = 0.0;
//				if(null == incomeMoney)incomeMoney = 0.0;
//				user.setFrozenMoney(frozenMoney);
//				user.setIncomeMoney(incomeMoney);
//				if(null == money)money = 0.0;
//				user.setTotalMoney(frozenMoney+money+incomeMoney);
//				modelMap.put("user", user);
//				modelMap = AdAction.getBannerAd(modelMap, adService);
//				path = PagePath.page_charge_index;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(path, modelMap);
//	}
//	
//	@RequestMapping(value = "/charge/account.html", method = RequestMethod.GET)
//	public ModelAndView chargeAccount(ModelMap modelMap,HttpServletRequest request) {
//		try {
//			Object userIdObj = request.getSession().getAttribute("userId");
//			if(null != userIdObj){
//				Integer userId = (Integer)userIdObj;
//				Map<String,Object> map = new HashMap<String,Object>();
//				map.put("userId", userId);
//				User user = userService.getUserById(userId);
//				modelMap.put("user", user);
//				map.clear();
//				modelMap = AdAction.getBannerAd(modelMap, adService);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(PagePath.page_charge_account, modelMap);
//	}
//	
//	@RequestMapping(value = "/charge/record.html", method = RequestMethod.GET)
//	public ModelAndView chargeRecord(ModelMap modelMap,HttpServletRequest request) {
//		try {
//			Object userIdObj = request.getSession().getAttribute("userId");
//			if(null != userIdObj){
//				Integer userId = (Integer)userIdObj;
//				Map<String,Object> map = new HashMap<String,Object>();
//				map.put("userId", userId);
//				User user = userService.getUserById(userId);
//				modelMap.put("user", user);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(PagePath.page_charge_record, modelMap);
//	}
//	
//	@RequestMapping(value = "/charge/jumpToPay.html")
//	public ModelAndView jumpToPay(ModelMap modelMap,HttpServletRequest request
//			,HttpServletResponse response,HttpSession session) {
//		String path = "/manager/login";
//		try {
//			request.setCharacterEncoding("utf-8");
//			Map<String, Object> map = ActionUtil.getAllParam(request);
//			Integer corpId= (Integer)session.getAttribute("corpId");
//			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
//			if (null == corpId)
//				corpId = 1;// TODO 临时固定一个值，后期修改
//			if (isAdmin != true) {
//				map.put("corpId", corpId);
//			}
//			map.put("corpId", corpId);
////			User user = userService.getUserById(userId);
//			
//			boolean flag = true;
//			String chargeForId = request.getParameter("for");
//			//String chargeFor = "";
//			if(null == chargeForId || "".equals(chargeForId)){
//				flag = false;
//			}else{
//				/*if(chargeForId.equals("1"))chargeFor = "购买服务";
//				if(chargeForId.equals("2"))chargeFor = "购买产品";
//				if(chargeForId.equals("3"))chargeFor = "换购积分";
//				if(chargeForId.equals("4"))chargeFor = "高级会员";
//				if(chargeForId.equals("5"))chargeFor = "竞价排名";
//				if(chargeForId.equals("6"))chargeFor = "广告推广";
//				if(chargeForId.equals("7"))chargeFor = "其他用途";*/
//			}
//			//付款金额
//			String money = new String(request.getParameter("money").getBytes("ISO-8859-1"),"UTF-8");
//			double m = 0;
//			if(null != money && !"".equals(money)){
//				try {
//					m = Double.parseDouble(money);
//					if(m <= 0)flag = false;
//				} catch (Exception e) {
//					flag = false;
//				}
//				
//			if(flag == true){
//				Date d = new Date();
//				String timeNum = String.valueOf(d.getTime());
//				System.out.println(timeNum);
//				String sHtmlText = assembleScript(user,timeNum,chargeForId,money);// chargeFor
//				response.setContentType("text/html; charset=utf-8");  
//				PrintWriter out = response.getWriter();
//				//out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>支付宝纯担保交易接口</title></head>");
//				out.println(sHtmlText);
//				//out.println("<body></body></html>");
//				out.flush();
//				out.close();
//				
//				Charge c = new Charge();
//				c.setAddBy(user.getUsername());
//				c.setAddTime(new Date());
//				c.setBody(chargeForId);
//				c.setIsDelete(1);
//				c.setLevel(1);
//				c.setOut_trade_no(timeNum);
//				c.setPrice(m);
//				double totalMoney = m;
//				if(totalMoney >= 20000){
//					totalMoney = totalMoney*1.1;//充值一次超过2万元的，另加10%资金
//				}else if(totalMoney >= 5000){
//					totalMoney = totalMoney*1.05;//充值一次超5000元的，另加5%资金
//				}
//				c.setTotalMoney(totalMoney);//充值后得到的总金额（包含优惠）
//				c.setStatus(0);
//				c.setSubject("充值");
//				c.setUserId(user.getId());
////				chargeService.addCharge(c);//跳转到支付宝的同时，新增一条充值记录，状态为1：已申请
//				
//				return null;
//			}
//		}	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(path, modelMap);
//	}
//	
//	private String assembleScript(User user,String timeNum,String chargeFor,String money){
//		//支付类型
//		String payment_type = "1";
//		//必填，不能修改
//		//服务器异步通知页面路径
//		String notify_url = AlipayConfig.notify_url;
//		//需http://格式的完整路径，不能加?id=123这类自定义参数
//
//		//页面跳转同步通知页面路径
//		String return_url = AlipayConfig.return_url;
//		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
//
//		//商户订单号
//		String out_trade_no = timeNum;
//		//商户网站订单系统中唯一订单号，必填
//
//		//订单名称
//		String subject = "充值";
//		//必填
//
//		String price = money;
//		//必填
//
//		//商品数量
//		String quantity = "1";
//		//必填，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品
//		//物流费用
//		String logistics_fee = "0.00";
//		//必填，即运费
//		//物流类型
//		String logistics_type = "EXPRESS";
//		//必填，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
//		//物流支付方式
//		String logistics_payment = "SELLER_PAY";
//		//必填，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
//		//订单描述
//
//		String body = chargeFor;
//		//商品展示地址
//		String show_url = AlipayConfig.show_url;
//		//需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html
//
//		//收货人姓名
//		String receive_name = user.getUsername();
//		//如：张三
//
//		//收货人地址
//		String receive_address = "无";
//		//如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号
//
//		//收货人邮编
//		String receive_zip = "123456";
//		//如：123456
//
//		//收货人电话号码
//		String receive_phone = "无";
//		//如：0571-88158090
//
//		//收货人手机号码
//		String p = user.getPhone();
//		if(null == p || "".equals(p))p = "无";
//		String receive_mobile = p;
//		//如：13312341234
//		
//		
//		//////////////////////////////////////////////////////////////////////////////////
//		
//		//把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>(20);
//		sParaTemp.put("service", "create_partner_trade_by_buyer");
//        sParaTemp.put("partner", AlipayConfig.partner);
//        sParaTemp.put("seller_email", AlipayConfig.seller_email);
//        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//		sParaTemp.put("payment_type", payment_type);
//		sParaTemp.put("notify_url", notify_url);
//		sParaTemp.put("return_url", return_url);
//		sParaTemp.put("out_trade_no", out_trade_no);
//		sParaTemp.put("subject", subject);
//		sParaTemp.put("price", price);
//		sParaTemp.put("quantity", quantity);
//		sParaTemp.put("logistics_fee", logistics_fee);
//		sParaTemp.put("logistics_type", logistics_type);
//		sParaTemp.put("logistics_payment", logistics_payment);
//		sParaTemp.put("body", body);
//		sParaTemp.put("show_url", show_url);
//		sParaTemp.put("receive_name", receive_name);
//		sParaTemp.put("receive_address", receive_address);
//		sParaTemp.put("receive_zip", receive_zip);
//		sParaTemp.put("receive_phone", receive_phone);
//		sParaTemp.put("receive_mobile", receive_mobile);
//		
//		//建立请求
//		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
//		
//		return sHtmlText;
//		
//	}
//	
//	@RequestMapping(value = "/charge/notify_url.html")
//	public ModelAndView notify_url(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
//		try {
//			//获取支付宝POST过来反馈信息
//			Map<String,String> params = new HashMap<String,String>();
//			Map<String, String[]> requestParams = request.getParameterMap();
//			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
//				String name = (String) iter.next();
//				String[] values = (String[]) requestParams.get(name);
//				String valueStr = "";
//				for (int i = 0; i < values.length; i++) {
//					valueStr = (i == values.length - 1) ? valueStr + values[i]
//							: valueStr + values[i] + ",";
//				}
//				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//				params.put(name, valueStr);
//			}
//			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//			//商户订单号
//			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//支付宝交易号
//			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//交易状态
//			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//			response.setContentType("text/html; charset=utf-8");  
//			PrintWriter out = response.getWriter();
//			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//			if(AlipayNotify.verify(params)){//验证成功
//				//////////////////////////////////////////////////////////////////////////////////////////
//				//请在这里加上商户的业务逻辑程序代码
//				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//				Map<String,Object> map = new HashMap<String,Object>(4);
//				map.put("out_trade_no", out_trade_no);
//				List<Charge> chargeList = chargeService.getChargeByCondition(map);
//				Charge c = null;
//				if(null != chargeList && chargeList.size()>0){
//					c = chargeList.get(0);
//				}
//				if(trade_status.equals("WAIT_BUYER_PAY")){
//					//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
//					if(null != c && null != c.getStatus() && c.getStatus() == 0){
//						c.setTrade_no(trade_no);
//						c.setStatus(1);
//						c.setUpdateBy("支付宝");
//						c.setUpdateTime(new Date());
//						chargeService.updateCharge(c);
//					}
//					out.println("success");	//请不要修改或删除
//				} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
//				//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
//					if(null != c && null != c.getStatus() && c.getStatus() == 1){
//						/*c.setStatus(2);
//						c.setUpdateBy("支付宝");
//						c.setUpdateTime(new Date());
//						chargeService.updateCharge(c);*/
//					}
//					out.println("success");	//请不要修改或删除
//				} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
//				//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
//					if(null != c && null != c.getStatus() && c.getStatus() == 2){
//						c.setStatus(3);
//						c.setUpdateBy("支付宝");
//						c.setUpdateTime(new Date());
//						chargeService.updateCharge(c);
//					}
//					out.println("success");	//请不要修改或删除
//				} else if(trade_status.equals("TRADE_FINISHED")){
//				//该判断表示买家已经确认收货，这笔交易完成
//					if(null != c && null != c.getStatus() && c.getStatus() == 3){
//						chargeService.updateChargeAndUserMoney(c);
//					}
//					out.println("success");	//请不要修改或删除
//				}
//				else {
//					out.println("success");	//请不要修改或删除
//				}
//				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//				//////////////////////////////////////////////////////////////////////////////////////////
//			}else{//验证失败
//				out.println("fail");
//			}
//			out.flush();
//			out.close();
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(PagePath.main, modelMap);
//	}
//	
//	@RequestMapping(value = "/charge/return_url.html")
//	public ModelAndView return_url(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
//		try {
//			//获取支付宝GET过来反馈信息
//			Map<String,String> params = new HashMap<String,String>();
//			Map<String, String[]> requestParams = request.getParameterMap();
//			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
//				String name = (String) iter.next();
//				String[] values = (String[]) requestParams.get(name);
//				String valueStr = "";
//				for (int i = 0; i < values.length; i++) {
//					valueStr = (i == values.length - 1) ? valueStr + values[i]
//							: valueStr + values[i] + ",";
//				}
//				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//				params.put(name, valueStr);
//			}
//			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//			//商户订单号
//			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//支付宝交易号
//			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//			//交易状态
//			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
//			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//			//计算得出通知验证结果
//			boolean verify_result = AlipayNotify.verify(params);
//			response.setContentType("text/html; charset=utf-8");  
//			//PrintWriter out = response.getWriter();
//			if(verify_result){//验证成功
//				//////////////////////////////////////////////////////////////////////////////////////////
//				if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
//					//判断该笔订单是否在商户网站中已经做过处理
//					Map<String,Object> map = new HashMap<String,Object>(4);
//					map.put("out_trade_no", out_trade_no);
//					List<Charge> chargeList = chargeService.getChargeByCondition(map);
//					Charge c = null;
//					if(null != chargeList && chargeList.size()>0){
//						c = chargeList.get(0);
//					}
//					
//					if(null != c && null != c.getStatus()){
//						int status = c.getStatus();//（0:跳转到支付页面,1：产生了交易记录未付款，2：已付款未发货，3：已发货未确认收货，4：确认收货交易完成）
//						if(status == 1){
//							c.setStatus(2);
//							c.setUpdateBy("支付宝");
//							c.setUpdateTime(new Date());
//							chargeService.updateCharge(c);
//						}
//						if(status == 1 || status == 2){
//							String sHtmlText = sendGoods(trade_no,modelMap);
//							if(sHtmlText.contains("<is_success>T</is_success>")){
//								c.setStatus(3);
//								c.setUpdateBy("支付宝");
//								c.setUpdateTime(new Date());
//								chargeService.updateCharge(c);
//								modelMap.put("result", "充值成功");
//								modelMap.put("status", 1);
//							}
//						}
//					}
//				}
//				//该页面可做页面美工编辑
//				//out.println("验证成功<br />");
//				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//				//////////////////////////////////////////////////////////////////////////////////////////
//			}else{
//				//该页面可做页面美工编辑
//				//out.println("验证失败");
//				modelMap.put("result", "充值失败");
//				modelMap.put("status", 0);
//			}
//			//out.flush();
//			//out.close();
//			//return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			modelMap.put("result", "充值过程中出现错误。");
//			modelMap.put("status", 0);
//		}
//		return new ModelAndView(PagePath.page_charge_complet, modelMap);
//	}
//	
//	@RequestMapping(value = "/charge/complet.html")
//	public ModelAndView complet(ModelMap modelMap,@RequestParam String status) {
//		try {
//			String result = "";
//			if(status.equals("1")){
//				result = "充值成功";
//			}else{
//				result = "充值失败";
//			}
//			modelMap.put("result", result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(PagePath.page_charge_complet, modelMap);
//	}
//	
//	private String sendGoods(String trade_no,ModelMap modelMap) throws Exception{
//		//支付宝交易号
//		//String trade_no = trade_no;
//		//必填
//		//物流公司名称
//		String logistics_name = "虚拟产品";
//		//必填
//		//物流发货单号
//		Date d = new Date();
//		String timeNum = String.valueOf(d.getTime());
//		String invoice_no = timeNum;
//		//物流运输类型
//		String transport_type = "EXPRESS";
//		//三个值可选：POST（平邮）、EXPRESS（快递）、EMS（EMS）
//		//////////////////////////////////////////////////////////////////////////////////
//		//把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "send_goods_confirm_by_platform");
//        sParaTemp.put("partner", AlipayConfig.partner);
//        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//		sParaTemp.put("trade_no", trade_no);
//		sParaTemp.put("logistics_name", logistics_name);
//		sParaTemp.put("invoice_no", invoice_no);
//		sParaTemp.put("transport_type", transport_type);
//		//建立请求
//		String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
//		return sHtmlText;
//	}
}
