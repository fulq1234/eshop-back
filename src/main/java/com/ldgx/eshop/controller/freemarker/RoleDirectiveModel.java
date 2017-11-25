package com.ldgx.eshop.controller.freemarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import freemarker.core.Environment;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

@Service
public class RoleDirectiveModel implements TemplateDirectiveModel {
	
	/***
	 * @param env:环境变量
	 * @param params:指令参数(存储你所需要的值，随便是什么Key-Value)
	 * @param loopVals:循环变量
	 * @param body:指令内容
	 * 除了params外，其它的都能是null
	 */
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVals, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		TemplateScalarModel user = (TemplateScalarModel) params.get("user");
		TemplateScalarModel role = (TemplateScalarModel) params.get("role");
		
		if("123456".equals(user.getAsString()) && "admin".equals(role.getAsString())) {
			//用户id
			loopVals[0] = TemplateBooleanModel.TRUE;
		}
		
		
		List<String> otherRights = new ArrayList<String>();
		otherRights.add("add");
		otherRights.add("delete");
		otherRights.add("update");
		loopVals[1] = new SimpleSequence(otherRights);
		body.render(env.getOut());
	}

}
