package com.cqjtu.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 
 * <p>ClassName:MybatisGenerator</p>
 * <p>Description:逆向工程</p> 
 * @author ZJH
 * @date 2017年5月31日下午7:13:52
 */
public class MybatisGenerator {

	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(MybatisGenerator.class.getResource("/").getPath() + "mybatisGenerator.xml");
		System.out.println(configFile.toString());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		System.out.println("mybatis逆向工程完成");
	}

}
