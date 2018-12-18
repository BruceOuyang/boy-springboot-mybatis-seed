package club.bugmakers.service;

import club.bugmakers.bean.GenConf;
import club.bugmakers.bean.Table;
import club.bugmakers.util.FreemarkerUtil;
import club.bugmakers.util.StrUtil;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * 代码生成服务
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Service
public class CodeGenService {
	private static final Logger logger = LoggerFactory.getLogger(CodeGenService.class);
	@Resource
	private TableService tableService;
	@Resource
	private GenConf genConf;

	public void genCode() throws Exception {
		logger.info("gen code,genConf={}", genConf);

		if (StrUtil.isBlank(genConf.getOutputDir())) {
			logger.error("output dir can NOT be null");
			return;
		}

		// 检查输出文件夹
		File outputDir = new File(genConf.getOutputDir());
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		} else {
			outputDir.delete();
			outputDir.mkdirs();
		}

		// 初始化输出文件目录
		File poFile = new File(genConf.getOutputDir() + "/po");
		File boFile = new File(genConf.getOutputDir() + "/bo");
		File dtoFile = new File(genConf.getOutputDir() + "/dto");

		File mapperFile = new File(genConf.getOutputDir() + "/mapper");
		File serviceFile = new File(genConf.getOutputDir() + "/service");
		File controllerFile = new File(genConf.getOutputDir() + "/controller");

		File xmlFile = new File(genConf.getOutputDir() + "/xml");

		poFile.mkdirs();
		boFile.mkdirs();
		dtoFile.mkdirs();

		mapperFile.mkdirs();
		serviceFile.mkdirs();
		controllerFile.mkdirs();

		xmlFile.mkdirs();

		// 获取所有表
		List<Table> tableList = tableService.getTableInfoList();

		for (Table table : tableList) {

			logger.info("gen code for table,tableName={}", table.getTableName());

			Map<String, Object> rootMap = Maps.newHashMap();
			rootMap.put("table", table);
			rootMap.put("cfg", genConf);

			// 将数据刷入模板，生成目标代码
            try(
				FileWriter poWriter = new FileWriter(poFile.getAbsolutePath() + "/" + table.getBeanName() + "PO.java");
				FileWriter boPageWriter = new FileWriter(boFile.getAbsolutePath() + "/" + table.getBeanName() + "BoPage.java");
				FileWriter boWriter = new FileWriter(boFile.getAbsolutePath() + "/" + table.getBeanName() + "BO.java");
				FileWriter dtoWriter = new FileWriter(dtoFile.getAbsolutePath() + "/" + table.getBeanName() + "DTO.java");
				FileWriter mapperWriter = new FileWriter(mapperFile.getAbsolutePath() + "/" + table.getBeanName() + "Mapper.java");
				FileWriter serviceWriter = new FileWriter(serviceFile.getAbsolutePath() + "/" + table.getBeanName() + "Service.java");
				FileWriter controllerWriter = new FileWriter(controllerFile.getAbsolutePath() + "/" + table.getBeanName() + "Controller.java");
				FileWriter xmlWriter = new FileWriter(xmlFile.getAbsolutePath() + "/" + table.getBeanName() + "Mapper.xml");
            ) {
				// 获取模板
				File poTemplate = ResourceUtils.getFile("classpath:templates/ssi/class_bean_po.ftl");
				File boPageTemplate = ResourceUtils.getFile("classpath:templates/ssi/class_bean_bo_page.ftl");
				File boTemplate = ResourceUtils.getFile("classpath:templates/ssi/class_bean_bo.ftl");
				File dtoTemplate = ResourceUtils.getFile("classpath:templates/ssi/class_bean_dto.ftl");
				File mapperTemplate = ResourceUtils.getFile("classpath:templates/ssi/class_mapper.ftl");
				File serviceTemplate = ResourceUtils.getFile("classpath:templates/ssi/class_service.ftl");
				File controllerTemplate = ResourceUtils.getFile("classpath:templates/ssi/class_controller.ftl");
				File xmlTemplate = ResourceUtils.getFile("classpath:templates/ssi/xml_mapper.ftl");

				// 数据刷入模板
                FreemarkerUtil.flushData(poTemplate.getAbsolutePath(), poWriter, rootMap);
                FreemarkerUtil.flushData(boPageTemplate.getAbsolutePath(), boPageWriter, rootMap);
                FreemarkerUtil.flushData(boTemplate.getAbsolutePath(), boWriter, rootMap);
                FreemarkerUtil.flushData(dtoTemplate.getAbsolutePath(), dtoWriter, rootMap);
                FreemarkerUtil.flushData(mapperTemplate.getAbsolutePath(), mapperWriter, rootMap);
                FreemarkerUtil.flushData(serviceTemplate.getAbsolutePath(), serviceWriter, rootMap);
                FreemarkerUtil.flushData(controllerTemplate.getAbsolutePath(), controllerWriter, rootMap);
                FreemarkerUtil.flushData(xmlTemplate.getAbsolutePath(), xmlWriter, rootMap);
            }
		}
		dumpTableExcel(tableList);
	}

	private void dumpTableExcel(List<Table> tableList){
		System.out.println("备份数据到 excel 表格.  (略)");
		System.out.println("代码已生成！目录为：" + genConf.getOutputDir());
	}

}
