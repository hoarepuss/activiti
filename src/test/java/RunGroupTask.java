import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public class RunGroupTask {
    @Test
    public void initTable(){
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
        /*RepositoryService repositoryService = processEngine.getRepositoryService();
        InputStream inputStream = this.getClass().getResourceAsStream("/GroupTask.zip");
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
        //runtimeService.startProcessInstanceById("HelloWorld:1:4", variables);
        //runtimeService.startProcessInstanceByKey("GroupTask", variables);
        //runtimeService.setVariable("2501", "用户", new User(1, "aaaa"));
        //System.out.println("流程启动成功");

        // 查询任务
        TaskService taskService = processEngine.getTaskService();
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
        /*Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("users", "小a,小b,小c,小d");
        taskService.complete("9", variables);*/

        // 组任务的查询
        /*List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("小a").list();
        for(int index = 0; index < taskList.size(); index++){
            System.out.println(taskList.get(index).getId());// 任务id
            System.out.println(taskList.get(index).getExecutionId());// 执行实例id
            System.out.println(taskList.get(index).getProcessInstanceId());// 流程实例id
            System.out.println(taskList.get(index).getProcessDefinitionId());// 流程定义id
            System.out.println(taskList.get(index).getName());// 任务名称
            System.out.println(taskList.get(index).getAssignee());// 代理人
        }*/

        // 组任务的拾取
        //taskService.claim("2503", "小a");

        // 个人任务的查看
        /*List<Task> taskList = taskService.createTaskQuery().taskAssignee("小a").list();
        for(int index = 0; index < taskList.size(); index++){
            System.out.println(taskList.get(index).getId());// 任务id
            System.out.println(taskList.get(index).getExecutionId());// 执行实例id
            System.out.println(taskList.get(index).getProcessInstanceId());// 流程实例id
            System.out.println(taskList.get(index).getProcessDefinitionId());// 流程定义id
            System.out.println(taskList.get(index).getName());// 任务名称
            System.out.println(taskList.get(index).getAssignee());// 代理人
        }*/

        // 个人任务的回退
        // taskService.setAssignee("2503", null);

        // 组任务的成员查询
        List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask("2503");
        for(int index = 0; index < identityLinkList.size(); index++){
            System.out.println(identityLinkList.get(index).getUserId());
        }

        // 添加组成员
        //taskService.addCandidateUser("2503", "小e");

        // 删除组成员
        //taskService.deleteCandidateUser("2503", "小a");
    }
}
