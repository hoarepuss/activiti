import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.junit.Test;

import java.util.List;

public class RunHistoryService {
    @Test
    public void historyService(){
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");

        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 查询历史记录的服务
        HistoryService historyService = processEngine.getHistoryService();
        /*List<HistoricActivityInstance> historicActivityInstanceList =  historyService.createHistoricActivityInstanceQuery().executionId("2501").list();
        for(int index = 0; index < historicActivityInstanceList.size(); index++){
            System.out.println(historicActivityInstanceList.get(index).getId());// 任务id
            System.out.println(historicActivityInstanceList.get(index).getExecutionId());// 执行实例id
            System.out.println(historicActivityInstanceList.get(index).getProcessInstanceId());// 流程实例id
            System.out.println(historicActivityInstanceList.get(index).getProcessDefinitionId());// 流程定义id
            System.out.println(historicActivityInstanceList.get(index).getActivityId());// 流程定义id
            System.out.println("===");
        }*/
        historyService.createHistoricTaskInstanceQuery().executionId("").finished().list();
    }
}
