package com.lhl.boot.utils;

import com.lhl.boot.exception.BusinessException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * freemarker-工具类
 * 
 * @author chenzhenjiang
 *
 */
public class FreemarkerUtil {
	// 默认数值格式化
	private final static String DEFAULT_NUM_FORMATE = "0.######";
	// 千分位数值格式化
	private final static String THOUSANDS_NUM_FORMATE = "###,###.##";
	// 字符集
	private final static String UTF_8 = "UTF-8";

	/**
	 * 解析模板，返回解析后的字符串
	 * 
	 * @param clazz
	 * @param pathPrefix
	 * @param templateName
	 * @param map
	 * @param thousandsFlag 是否千分位
	 * @return
	 * @throws Exception
	 */
	public static String processTemplate(Class<?> clazz, String pathPrefix, String templateName,
			Map<String, Object> map) throws Exception {
		return FreemarkerUtil.processTemplate(clazz, pathPrefix, templateName, map, false);
	}

	/**
	 * 解析模板，返回解析后的字符串
	 * 
	 * @param clazz
	 * @param pathPrefix
	 * @param templateName
	 * @param map
	 * @param thousandsFlag 是否千分位
	 * @return
	 * @throws Exception
	 */
	public static String processTemplate(Class<?> clazz, String pathPrefix, String templateName,
			Map<String, Object> map, boolean thousandsFlag) throws Exception {
		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setClassForTemplateLoading(clazz, pathPrefix);
		freemarkerCfg.setEncoding(Locale.getDefault(), UTF_8);
		if (thousandsFlag) {
			freemarkerCfg.setNumberFormat(THOUSANDS_NUM_FORMATE);
		} else {
			freemarkerCfg.setNumberFormat(DEFAULT_NUM_FORMATE);
		}
		Template template = freemarkerCfg.getTemplate(templateName);
		template.setEncoding(UTF_8);
		StringWriter writer = new StringWriter();
		template.process(map, writer);
		return writer.toString();
	}

	/**
	 * 解析模板，返回解析后的字符串
	 * 
	 * @param templatePath
	 * @param templateName
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String processTemplateStr(String templateStr, String templateName, Map<String, Object> map)
			throws Exception {
		/* 在整个应用的生命周期中，这个工作你应该只做一次。 */
		/* 创建和调整配置。 */
		Configuration freemarkerCfg = new Configuration();
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate(templateName, templateStr);
		freemarkerCfg.setTemplateLoader(stringLoader);
		freemarkerCfg.setEncoding(Locale.getDefault(), UTF_8);
		freemarkerCfg.setNumberFormat(DEFAULT_NUM_FORMATE);
		/* 创建数据模型 */
		Template template = freemarkerCfg.getTemplate(templateName);
		template.setEncoding(UTF_8);
		StringWriter writer = new StringWriter();
		template.process(map, writer);
		return writer.toString();
	}

	/**
	 * 生成html
	 * 
	 * @param templatePath
	 * @param templateName
	 * @param htmlPath
	 * @param htmlName
	 * @param map
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void generateHtml(String templatePath, String templateName, String htmlPath, String htmlName,
			Map<String, Object> map) throws IOException, TemplateException {
		Configuration freemarkerCfg = new Configuration();
		File templateFile = new File(templatePath);
		if (!templateFile.exists()) {
			throw new BusinessException("模板不存在");
		} else {
			freemarkerCfg.setDirectoryForTemplateLoading(templateFile);
			freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			File f = new File(htmlPath);
			if (!f.exists() && !f.mkdirs()) {
				System.out.print("创建文件夹失败");
			}

			File htmlFile = new File(htmlPath + htmlName);
			if (htmlFile.exists()) {
				htmlFile.delete();
			}

			htmlFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			template.process(map, out);
			out.flush();
			out.close();
		}
	}

	/**
	 * 根据模板、数据获取html
	 * 
	 * @param prefix
	 * @param templateName
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String processTemplate(String prefix, String templateName, Map<String, Object> map) throws Exception {
		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setClassForTemplateLoading(FreemarkerUtil.class, prefix);
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		freemarkerCfg.setNumberFormat("0.######");
		Template template = freemarkerCfg.getTemplate(templateName);
		template.setEncoding("UTF-8");
		StringWriter writer = new StringWriter();
		template.process(map, writer);
		return writer.toString();
	}
}