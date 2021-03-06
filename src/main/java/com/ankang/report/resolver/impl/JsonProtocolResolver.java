/**
 **Copyright (c) 2015, ancher  安康 (676239139@qq.com).
 ** 
 ** This Source Code Form is subject to the terms of the Mozilla Public
 ** License, v. 2.0. If a copy of the MPL was not distributed with this
 ** file, You can obtain one at 
 ** 
 ** 	http://mozilla.org/MPL/2.0/.
 **
 **If it is not possible or desirable to put the notice in a particular
 **file, then You may include the notice in a location (such as a LICENSE
 **file in a relevant directory) where a recipient would be likely to look
 **for such a notice.
 **/
package com.ankang.report.resolver.impl;


import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.ankang.report.annotation.Alias;
import com.ankang.report.model.ReportResponse;
import com.ankang.report.resolver.AbstractReportResolver;
import com.ankang.report.util.GsonUtil;

@Alias(alias="json")
public class JsonProtocolResolver extends AbstractReportResolver{
	
	@Override
	public <IN> IN in(String input, Class<IN> targerCalss) {
		return JSON.parseObject(null == input?this.params.toJSONString():input, targerCalss);
	}

	@Override
	public <OUT> String out(OUT output) {
		/*return JSON.toJSONString(output,
				SerializerFeature.PrettyFormat,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse, 
				SerializerFeature.WriteNullStringAsEmpty );*/
		return GsonUtil.toJson(output);
	}

	@Override
	public String error(int code, String message) {
		
		ReportResponse responseMsg = new ReportResponse();
		responseMsg.setCode(code);
		responseMsg.setMessage("请求失败");
		responseMsg.setResponse(message);
		return out(responseMsg);
	}

	@Override
	public String getContextType() {
		return "text/json;charset=utf-8";
	}

	@Override
	public void resolverParamter(HttpServletRequest request) {
		resolverParamter(request, JSON_DUFALUE_BODY);
	}
}
