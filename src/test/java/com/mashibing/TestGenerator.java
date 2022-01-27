package test.java.com.mashibing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class TestGenerator {

    @Test
    public void testGenerator() {
        AutoGenerator autoGenerator = new AutoGenerator();

        //ȫ������
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("lian")
                .setOutputDir("D:\\workspace\\family_service_platform\\src\\main\\java")//�������·��
                .setFileOverride(true)//�����ļ�����
                .setIdType(IdType.AUTO)//�����������ɲ���
                .setServiceName("%sService")//service�ӿڵ�����
                .setBaseResultMap(true)//�����������
                .setBaseColumnList(true)//���û�������
                .setControllerName("%sController");

        //��������Դ
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver").setUrl("jdbc:mysql://localhost:3306/family_service_platform?serverTimezone=UTC")
                .setUsername("root").setPassword("root");

        //��������
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)//����ȫ�ִ�д����
                .setNaming(NamingStrategy.underline_to_camel)//���ݿ��ӳ�䵽ʵ�����������
                //.setTablePrefix("tbl_")//���ñ���ǰ׺
                .setInclude();

        //��������
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.mashibing").setMapper("mapper")
                .setService("service").setController("controller")
                .setEntity("bean").setXml("mapper");

        autoGenerator.setGlobalConfig(globalConfig).setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig).setPackageInfo(packageConfig);

        autoGenerator.execute();
    }
}
