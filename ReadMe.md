@Service用于标注业务层组件
@Controller用于标注控制层组件（如struts中的action）
@Repository用于标注数据访问组件，即DAO组件
@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。

1.mybatis代码生成器 参考blog：http://blog.csdn.net/tianhouquan/article/details/73800916
    修改src/main/resources/mybatis-generator/generatorConfig-learn.xml的配置
2.新增mybatisCofig，配置sqlSessionFactory sessionTemple,在Application中添加@ComponentScan(basePackages = {"com.ahut.core.common.db.config","com.ahut.core"})//扫描包路径
  在mybatisConfig头添加@MapperScan(basePackages="com.ahut.core.dao",sqlSessionTemplateRef="sessionTemple")//将接口转换为Spring容器中的Bean
3.自定义mybatis拦截器 参考blog http://blog.csdn.net/u012089657/article/details/49763631
4.新建BaseRequest.BaseResponse 和RequestContext和Packet JsonUtils工具类
5.新建Servic过滤器 并在service模块注册
6.搭建userService的基础矿架 并进行demo测试（注意每个模块注解的含义和Application上注解的含义 不要有遗漏）
7.在common里自定义异常BizException 并自定义异常处理GlobalExceptionHandler并在UserService模块注册