package club.bugmakers.service;

import club.bugmakers.bean.GenConf;
import club.bugmakers.bean.Table;
import club.bugmakers.bean.TemplatePath;
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

@Service
public class CodeGenService {
	private static final Logger logger = LoggerFactory.getLogger(CodeGenService.class);
	@Resource
	private TableService tableService;
	@Resource
	private GenConf genConf;

	public void genCode() throws Exception {
		logger.info("gen code,genConf={}", genConf);

		if (StrUtil.isBlank(genConf.getOutputDirMgt())) {
			logger.error("output dir can NOT be null");
			return;
		}

		if (StrUtil.isBlank(genConf.getOutputDirWeb())) {
			logger.error("output dir can NOT be null");
			return;
		}

		// 检查输出文件夹
		initOutputDir(genConf.getOutputDirMgt());
		initOutputDir(genConf.getOutputDirWeb());

		String sep = File.separator;

		String javaCodePath = new StringBuilder(genConf.getOutputDirMgt()).append(javaCodeRoot()).toString();
		String xmlCodePath = new StringBuilder(genConf.getOutputDirMgt()).append(xmlCodeRoot()).toString();
		String vueCodePath = new StringBuilder(genConf.getOutputDirWeb()).append(vueCodeRoot()).toString();

		String bizPath = new StringBuilder(sep).append(genConf.getTableInclude()).append(sep).toString();

		// 初始化输出文件目录
		File poFile = new File(new StringBuilder(javaCodePath).append(sep).append(toPath(genConf.getBeanPoPackage())).toString());
		File boFile = new File(new StringBuilder(javaCodePath).append(sep).append(toPath(genConf.getBeanBoPackage())).toString());
		File dtoFile = new File(new StringBuilder(javaCodePath).append(sep).append(toPath(genConf.getBeanDtoPackage())).toString());

		File mapperFile = new File(new StringBuilder(javaCodePath).append(sep).append(toPath(genConf.getBeanMapperPackage())).toString());
		File serviceFile = new File(new StringBuilder(javaCodePath).append(sep).append(toPath(genConf.getBeanServicePackage())).toString());
		File controllerFile = new File(new StringBuilder(javaCodePath).append(sep).append(toPath(genConf.getBeanControllerPackage())).toString());

		File xmlFile = new File(xmlCodePath.toString());

		File vueBeanFile = new File(new StringBuilder(vueCodePath).append("src").append(sep).append("pages").append(bizPath).toString());
		File vueMockFile = new File(new StringBuilder(vueCodePath).append("mock").toString());
		File vueRouterFile = new File(new StringBuilder(vueCodePath).append("src").append(sep).append("router").toString());

		poFile.mkdirs();
		boFile.mkdirs();
		dtoFile.mkdirs();

		mapperFile.mkdirs();
		serviceFile.mkdirs();
		controllerFile.mkdirs();

		xmlFile.mkdirs();

		vueBeanFile.mkdirs();
		vueMockFile.mkdirs();
		vueRouterFile.mkdirs();

		// 获取所有表
		List<Table> tableList = tableService.getTableInfoList();

		for (Table table : tableList) {

			logger.debug("gen code for table,tableName={}", table.getTableName());

			// 数据
			Map<String, Object> data = Maps.newHashMap();
			data.put("table", table);
			data.put("cfg", genConf);

			// 代码输出路径
			StringBuilder poPath = new StringBuilder(poFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("PO.java");
			StringBuilder boPagePath = new StringBuilder(boFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("BoPage.java");
			StringBuilder boPath = new StringBuilder(boFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("BO.java");
			StringBuilder dtoPath = new StringBuilder(dtoFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("DTO.java");
			StringBuilder mapperPath = new StringBuilder(mapperFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("Mapper.java");
			StringBuilder servicePath = new StringBuilder(serviceFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("Service.java");
			StringBuilder controllerPath = new StringBuilder(controllerFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("Controller.java");

			StringBuilder xmlPath = new StringBuilder(xmlFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("Mapper.xml");

			StringBuilder vueBeanPath = new StringBuilder(vueBeanFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append(".vue");
			StringBuilder vueMockPath = new StringBuilder(vueMockFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("Mock.json");
			StringBuilder vueRouterPath = new StringBuilder(vueRouterFile.getAbsolutePath()).append(sep).append(table.getBeanName()).append("Router.js");

			// 生成代码
			genCode(poPath, TemplatePath.CLASS_PO, data);
			genCode(boPagePath, TemplatePath.CLASS_BO_PAGE, data);
			genCode(boPath, TemplatePath.CLASS_BO, data);
			genCode(dtoPath, TemplatePath.CLASS_DTO, data);
			genCode(mapperPath, TemplatePath.CLASS_MAPPER, data);
			genCode(servicePath, TemplatePath.CLASS_SERVICE, data);
			genCode(controllerPath, TemplatePath.CLASS_CONTROLLER, data);

			genCode(xmlPath, TemplatePath.XML_MAPPER, data);

			genCode(vueBeanPath, TemplatePath.VUE_BEAN, data);
			genCode(vueMockPath, TemplatePath.VUE_MOCK, data);
			genCode(vueRouterPath, TemplatePath.VUE_ROUTER, data);

			logger.info("import {}Router from './{}Router'", table.getBeanName(), table.getBeanName());

		}
		
		dumpTableExcel(tableList);
		
	}

	private void initOutputDir(String outputDirPath) {
		File outputDir = new File(outputDirPath);
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		} else {
			outputDir.deleteOnExit();
			outputDir.mkdirs();
		}
	}

	private String javaCodeRoot() {
		return toPath(new StringBuilder(".").append(genConf.getProjectNameMgt()).append(".src.main.java.").toString());
	}

	private String xmlCodeRoot() {
		return toPath(new StringBuilder(".").append(genConf.getProjectNameMgt()).append(".src.main.resources.mapper.").toString());
	}

	private String vueCodeRoot() {
		return toPath(new StringBuilder(".").append(genConf.getProjectNameWeb()).append(".").toString());
	}

	private void genCode(StringBuilder codeOutputPath, String codeTemplatePath, Map<String, Object> data) throws Exception {
		try(
			FileWriter writer = new FileWriter(codeOutputPath.toString());
		){
			// 获取模板
			File template = ResourceUtils.getFile(codeTemplatePath);
			// 数据刷入模板
			FreemarkerUtil.flushData(template.getAbsolutePath(), writer, data);
		}
	}

	private String toPath(String pkgPath) {
		return pkgPath.replace(".", File.separator);
	}

	private void dumpTableExcel(List<Table> tableList){
		logger.info("共生成 {} 个模块", tableList.size());
		logger.info("备份数据到 excel 表格.  (略)");
		logger.info("代码已生成！");
	}

}
