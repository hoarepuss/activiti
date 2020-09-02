import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

/**
  * 管理流程定义
  */
public class RunProcessDef {
    @Test
    public void processDef02(){
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 流程图的部署 修改 删除的服务
        RepositoryService repositoryService = processEngine.getRepositoryService();

        InputStream inputStream = this.getClass().getResourceAsStream("/HelloWorld.zip");

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        Deployment deployment = repositoryService.createDeployment()
                .name("请假流程001")
                .addZipInputStream(zipInputStream)
                .deploy();
        System.out.println("部署成功：流程部署ID" + deployment.getId());
    }

    /*
     * 流程部署查询
     */
    @Test
    public  void deploymentQuery() {
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 流程图的部署 修改 删除的服务
        RepositoryService repositoryService = processEngine.getRepositoryService();

        List<Deployment> deployment = repositoryService
                .createDeploymentQuery()
                // 条件
                .deploymentId("1")
                // 排序
                .orderByDeploymentId()
                .asc()
                // 结果集
                // .listPage() 分页查询
                // .singleResult() 返回单个对象
                // .count() 统计数量
                .list();

        for(int index = 0; index < deployment.size(); index++) {
            System.out.println("流程部署的ID: " + deployment.get(index).getId());
        }
    }

    /*
     * 流程定义查询
     */
    @Test
    public  void processDefinitionQuery() {
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 流程图的部署 修改 删除的服务
        RepositoryService repositoryService = processEngine.getRepositoryService();

        List<ProcessDefinition> processDefinitions = repositoryService
                .createProcessDefinitionQuery()
                .list();

        for(int index = 0; index < processDefinitions.size(); index++) {
            System.out.println("流程定义的ID: " + processDefinitions.get(index).getId());
        }
    }

    @Test
    public void deleteProcessDefinition() {
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 流程图的部署 修改 删除的服务
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 根据流程部署id删除流程定义，如果当前id的流程正在执行，那麽会报错
        // repositoryService.deleteDeployment("2501");
        // 根据流程部署id删除流程定义，如果当前id的流程正在执行，会把正在执行的流程数据删除act_ru_*和act_hi_*
        repositoryService.deleteDeployment("2501", true);
    }

    // 修改流程定义信息
    // 修改流程图后重新部署 只要key不变 它的版本号就会+1

    // 查询流程图 通过流程定义的ID或者流程部署ID
    @Test
    public void viewDiagram(){
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 流程图的部署 修改 删除的服务
        RepositoryService repositoryService = processEngine.getRepositoryService();

        InputStream inputStream = repositoryService.getProcessDiagram("HelloWorld:1:4");

        File file = new File("d:/1.png");//关联文件

        BufferedOutputStream outputStream = null;//升级为高速缓冲写出流
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buf = new byte[1024];//创建1kb的读取缓冲数组
            int len = 0 ;
            while((len = inputStream.read(buf)) != -1){//读取内容记录在buf数组中
                outputStream.write(buf, 0, len);//带字符数组缓冲的高效写出流，格式为buf.write(buf,0,len)
                //其中buf为缓冲数组，BufferedOutputStream本身具有8Kb的缓冲大小
                //每一将数组中1kb的数据加载到8Kb的缓冲区中，待到8kb缓冲区全部装满后
                //系统会自动的将缓冲区的8kb内容一次性全部刷新到磁盘文件中；
                //0为写入的开始，len为写入的长度，范围为（0-len）包括0不包括len
                outputStream.flush();//字节流不一定非要写刷新，但是在结束虚拟机之前一定要关闭缓冲流的写出，
                //否则写出的文件不能被写入到本地磁盘中，会被当做垃圾被虚拟机回收！
            }
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 查询最新版本的流程定义
     **/
    @Test
    public void createLastedVersion(){
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 流程图的部署 修改 删除的服务
        RepositoryService repositoryService = processEngine.getRepositoryService();

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .asc()
                .list();
        Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
        for (int index = 0; index < processDefinitionList.size(); index++) {
            map.put(processDefinitionList.get(index).getKey(), processDefinitionList.get(index));
        }
        for(Object key:map.keySet()){
            ProcessDefinition processDefinition = map.get(key);
            System.out.println(processDefinition.getId());
        }
    }
}
