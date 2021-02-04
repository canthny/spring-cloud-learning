package tanghao.learning.test.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisGeneratorMain {

    public static void main(String[] args) throws Exception {

        String author = new File("").getAbsolutePath();
        System.out.println(author);
        String path = author + "/tanghao-learning-test/src/main/resources/generatorConfig.xml";

        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(new File(path));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}