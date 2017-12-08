package com.jyss.yqy.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.jyss.yqy.entity.Cwzf;
import com.jyss.yqy.service.CwzfService;

@Controller
public class AlipayAction {

	@Autowired
	private CwzfService cwService;

	private static Log log = LogFactory.getLog(AlipayAction.class);

	// 支付宝当面付2.0服务
	private static AlipayTradeService tradeService;

	// 支付宝当面付2.0服务（集成了交易保障接口逻辑）
	private static AlipayTradeService tradeWithHBService;

	// 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
	private static AlipayMonitorService monitorService;

	static {
		/**
		 * 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
		 * Configs会读取classpath下的zfbinfo
		 * .properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
		 */
		Configs.init("zfbinfo.properties");

		/**
		 * 使用Configs提供的默认参数 AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
		 */
		tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

		// 支付宝当面付2.0服务（集成了交易保障接口逻辑）
		tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder()
				.build();

		/**
		 * 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数
		 * 否则使用代码中的默认设置
		 */
		monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
				.setGatewayUrl("http://mcloudmonitor.com/gateway.do")
				.setCharset("GBK").setFormat("json").build();
	}

	// 预下单--当面付--生成二维码 --type 视频== 2=视频= 3=通话=',
	@RequestMapping(value = "/pat/alipay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> alipay(@RequestParam int money,
			@RequestParam int timeAccount, @RequestParam int type,
			@RequestParam String macId) {
		Map<String, String> m = new HashMap<String, String>();
		// (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
		// 需保证商户系统端不能重复，建议通过数据库sequence生成，
		String outTradeNo = macId + System.currentTimeMillis() / 1000;
		// + (long) (Math.random() * 1000L);

		// (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
		String subject = "明斯特";
		if (type == 2) {
			subject += "视频套餐扫码支付";
		} else if (type == 3) {
			subject += "通话套餐扫码支付";
		}

		// (必填) 订单总金额，单位为元，不能超过1亿元
		// 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
		String totalAmount = money + "";

		// (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
		// 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
		String undiscountableAmount = "0";

		// 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
		// 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
		String sellerId = "";

		// 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
		String body = "套餐：" + timeAccount + "分钟" + money + "元";

		// 商户操作员编号，添加此参数可以为商户操作员做销售统计
		String operatorId = "test_operator_id";

		// (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
		String storeId = "2088102172143232";// test_store_id"=2088802858369537

		// 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
		ExtendParams extendParams = new ExtendParams();
		extendParams.setSysServiceProviderId("2088100200300400500");

		// 支付超时，定义为120分钟
		String timeoutExpress = "120m";

		// 商品明细列表，需填写购买商品详细信息，
		// List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
		// 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
		// GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001",
		// "xxx小面包",1000, 1);
		// 创建好一个商品后添加至商品明细列表
		// goodsDetailList.add(goods1);

		// 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
		// GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002",
		// "xxx牙刷",500, 2);
		// goodsDetailList.add(goods2);

		// 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
				.setSubject(subject).setTotalAmount(totalAmount)
				.setOutTradeNo(outTradeNo)
				.setUndiscountableAmount(undiscountableAmount)
				.setSellerId(sellerId).setBody(body).setOperatorId(operatorId)
				.setStoreId(storeId).setExtendParams(extendParams)
				.setTimeoutExpress(timeoutExpress)
				.setNotifyUrl("http://192.168.0.26:8080/SSM/pat/notify.action");// 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
		// .setGoodsDetailList(goodsDetailList);

		AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
		switch (result.getTradeStatus()) {
		case SUCCESS:
			log.info("支付宝预下单成功: )");

			AlipayTradePrecreateResponse response = result.getResponse();
			dumpResponse(response);

			// 需要修改为运行机器上的路径
			// String filePath =
			// String.format("/Users/sudo/Desktop/qr-%s.png",response.getOutTradeNo());
			// log.info("filePath:" + filePath);
			// ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);

			// 自我业务处理
			// 生成订单，插入数据库
			Cwzf cwzf = new Cwzf(0, macId, 1, timeAccount, money, type, 1, 1,
					null, null, "", "", "付钱账号", "付钱昵称", outTradeNo, subject,
					body, "支付宝订单");
			int count = 0;
			count = cwService.addCw(cwzf);
			if (count == 1) {
				m.put("status", "true");
				m.put("qrcode", response.getQrCode()); // 返回给客户端二维码
				m.put("outtradeno", outTradeNo);
				return m;
			}

		case FAILED:
			log.error("支付宝预下单失败!!!");
			m.put("status", "false");
			break;

		case UNKNOWN:
			log.error("系统异常，预下单状态未知!!!");
			m.put("status", "false");
			break;

		default:
			log.error("不支持的交易状态，交易返回异常!!!");
			m.put("status", "false");
			break;
		}

		return m;
	}

	@RequestMapping(value = "/pat/notify", method = RequestMethod.POST)
	public String notifyResult(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("收到支付宝异步通知！");
		Map<String, String> params = new HashMap<String, String>();

		// 取出所有参数是为了验证签名
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			params.put(parameterName, request.getParameter(parameterName));
		}
		boolean signVerified;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params,
					Configs.getAlipayPublicKey(), "UTF-8");
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return "failed";
		}
		if (signVerified) {
			String outtradeno = params.get("out_trade_no");
			log.info(outtradeno + "号订单回调通知。");
			// System.out.println("验证签名成功！");
			log.info("验证签名成功！");

			// 若参数中的appid和填入的appid不相同，则为异常通知
			if (!Configs.getAppid().equals(params.get("app_id"))) {
				log.warn("与付款时的appid不同，此为异常通知，应忽略！");
				return "failed";
			}

			// 在数据库中查找订单号对应的订单，并将其金额与数据库中的金额对比，若对不上，也为异常通知
			Cwzf cw = null;
			cw = cwService.getCwByNo(outtradeno).get(0);
			if (cw == null) {
				log.warn(outtradeno + "查无此订单！");
				return "failed";
			}
			if (cw.getCzMoney() != Double.parseDouble(params
					.get("total_amount"))) {
				log.warn("与付款时的金额不同，此为异常通知，应忽略！");
				return "failed";
			}
			// 0 未知状态 1预下单状态 2支付成功 3交易超时 4交易失败 5等待付款 ',
			if (cw.getStatus() == 2)
				return "success"; // 如果订单已经支付成功了，就直接忽略这次通知

			String status = params.get("trade_status");
			Cwzf cwzf = null;
			cwzf.setMacOrderId(outtradeno);
			int isSucc = 0;
			if (status.equals("WAIT_BUYER_PAY")) { // 如果状态是正在等待用户付款
				if (cw.getStatus() != 5)
					cwzf.setStatus(5);
			} else if (status.equals("TRADE_CLOSED")) { // 如果状态是未付款交易超时关闭，或支付完成后全额退款
				if (cw.getStatus() != 3)
					cwzf.setStatus(3);
			} else if (status.equals("TRADE_SUCCESS")
					|| status.equals("TRADE_FINISHED")) { // 如果状态是已经支付成功
				cwzf.setStatus(2);
			} else {
				cwzf.setStatus(0);
			}
			// 修改订单状态
			isSucc = cwService.upCw(cwzf);
			if (isSucc == 1) {
				// 具体对应业务
				isSucc = 0;
				int cztYPE = cw.getCzType();
				// 1 基础付费设置 2视频套餐设置 3通话套餐设置',
				if (cztYPE == 3) {
					// isSucc = patService.upTalkTimeByCz(cw.getCzTime(),
					// cw.getAccount());
				} else if (cztYPE == 2) {
					// isSucc = patService.upVedioTimeByCz(cw.getCzTime(),
					// cw.getAccount());
				}
				if (isSucc == 1) {
					log.info(outtradeno + "订单的状态已经修改为" + status);
					return "success";
				}
			}

		} else { // 如果验证签名没有通过
			return "failed";
		}
		return "success";
	}

	// 简单打印应答
	private void dumpResponse(AlipayResponse response) {
		if (response != null) {
			log.info(String.format("code:%s, msg:%s", response.getCode(),
					response.getMsg()));
			if (StringUtils.isNotEmpty(response.getSubCode())) {
				log.info(String.format("subCode:%s, subMsg:%s",
						response.getSubCode(), response.getSubMsg()));
			}
			log.info("body:" + response.getBody());
		}
	}

}
