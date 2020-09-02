import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public class Run {
    @Test
    public void initTable(){
        // 创建流程引擎的配置
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");

        /**
         * true：如果不存在表就创建，存在就直接使用。（默认使用该策略，真实项目不推荐使用，如果目前使用较低版本，贸然引入了更高的版本后，
         * 上线之后，就会导致activiti数据库环境升级版本，可能造成线上部分表无法使用。顺便一提：activiti的版本只能向上升级，无法降级处理）
         * false：流程引擎启动的时候，不会创建表，如果不存在就报错，存在就直接使用。（建议使用）
         * create-drop：每次都是创建表，然后删除表，需要手动调用引擎类的close方法（更不建议使用）
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 流程图的部署 修改 删除的服务
        // RepositoryService repositoryService = processEngine.getRepositoryService();
        /*
         *select * from act_ge_bytearray                二进制文件表
         *select * from select * from act_ge_property   工作流的ID算法和版本信息表
         *select * from act_re_deployment               流程部署表
         *select * from act_re_procdef                  流程定义表
         */
        /*Deployment deployment = repositoryService.createDeployment()
                .name("报销流程")
                .addClasspathResource("SequenceFlow.bpmn")
                .addClasspathResource("SequenceFlow.png")
                .deploy();
        System.out.println("部署成功：流程部署ID" + deployment.getId());*/

        /*InputStream inputStream = this.getClass().getResourceAsStream("/designatedAgent.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = repositoryService.createDeployment()
                .name("请假申请流程")
                .addZipInputStream(zipInputStream)
                .deploy();
        System.out.println("部署成功：流程部署ID" + deployment.getId());*/

        // 流程的运行
        //RuntimeService runtimeService = processEngine.getRuntimeService();
        //runtimeService.signal("5");
        //Map<String, Object> variables = new HashMap<String, Object>();
        //variables.put("assignee", "aaa");
        //variables.put("请假原因", "生病");
        //variables.put("请假时间", new Date());
        //runtimeService.startProcessInstanceById("HelloWorld:1:4", variables);
        //runtimeService.startProcessInstanceByKey("designatedAgent", variables);
        //runtimeService.setVariable("2501", "用户", new User(1, "aaaa"));
        //System.out.println("流程启动成功");

        // 查询任务
        TaskService taskService = processEngine.getTaskService();
        // taskService.setVariable("2507", "请假天数", 1);
        /*List<Task> taskList = taskService.createTaskQuery().taskAssignee("张三").list();
        for(int index = 0; index < taskList.size(); index++){
            System.out.println(taskList.get(index).getId());// 任务id
            System.out.println(taskList.get(index).getExecutionId());// 执行实例id
            System.out.println(taskList.get(index).getProcessInstanceId());// 流程实例id
            System.out.println(taskList.get(index).getProcessDefinitionId());// 流程定义id
            System.out.println(taskList.get(index).getName());// 任务名称
            System.out.println(taskList.get(index).getAssignee());// 代理人
        }*/
        // 办理任务
        //Map<String, Object> variables = new HashMap<String, Object>();
        //variables.put("assignee", "bbb");
        //taskService.complete("2502", variables);
        // 查询历史记录的服务
        //HistoryService historyService = processEngine.getHistoryService();
        //historyService.createHistoricVariableInstanceQuery()

        // 页面表单的服务
        // FormService formService = processEngine.getFormService();

        // 对工作流的用户管理的服务
        // IdentityService identityService = processEngine.getIdentityService();

        // 管理服务
        // ManagementService managementService = processEngine.getManagementService();

        // ProcessDefinition ProcessInstance Execution

        // 部署流程定义
    }
}
