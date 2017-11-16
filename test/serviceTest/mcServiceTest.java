package serviceTest;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.jxccp.im.lib.core.rpc.entity.statistics.StcAgentAppOperationEntity;
import com.jxccp.im.lib.core.rpc.entity.statistics.StcAgentAppOperationResponseEntity;
import com.jxccp.im.lib.core.rpc.entity.statistics.StcIMAppOperationEntity;
import com.jxccp.im.lib.core.rpc.entity.statistics.StcIMAppOperationResponseEntity;
import com.jxccp.im.lib.core.rpc.entity.statistics.StcMarketChannelEntity;
import com.jxccp.im.lib.core.rpc.entity.statistics.StcMarketChannelResponseEntity;
import com.jxccp.im.lib.core.rpc.service.statistics.IStcAppOperationService;
import com.jxccp.im.lib.core.rpc.service.statistics.IStcMarketChannelService;

public class mcServiceTest {
	
	private ClassPathXmlApplicationContext context;
	
	private IStcMarketChannelService	mcService;
	private IStcAppOperationService		appService;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(new String[] { "META-INF/x_consumer.xml" });
		mcService = context.getBean("mcService", IStcMarketChannelService.class);
		appService = context.getBean("appService", IStcAppOperationService.class);
	}
	
	@Test
	public void testChannel() {
		String startTime = "2016-01-01";
		String endTime = "2016-05-30";
		try {
			StcMarketChannelResponseEntity s = mcService.getChannelStatistics(startTime, endTime,
					"", "1,15");
			List<StcMarketChannelEntity> resultList = s.getMarketChannelEntities();
			System.out.println(s.getCount());
			System.out.println(resultList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAgentAppOperationStatis() {
		String startTime = "2016-01-01";
		String endTime = "2016-07-30";
		try {
			StcAgentAppOperationResponseEntity agentEntity1 = appService
					.getAgentAppOperationStatistics(startTime, endTime, "1,15", 1);
			List<StcAgentAppOperationEntity> list1 = agentEntity1.getAgentOperationEntities();
			System.out.println(agentEntity1.getCount());
			System.out.println(list1.toString());
			
			StcAgentAppOperationResponseEntity agentEntity2 = appService
					.getAgentAppOperationStatistics(startTime, endTime, "1,15", 2);
			List<StcAgentAppOperationEntity> list2 = agentEntity2.getAgentOperationEntities();
			System.out.println(agentEntity2.getCount());
			System.out.println(list2.toString());
			
			StcAgentAppOperationResponseEntity agentEntity3 = appService
					.getAgentAppOperationStatistics(startTime, endTime, "1,15", 3);
			List<StcAgentAppOperationEntity> list3 = agentEntity3.getAgentOperationEntities();
			System.out.println(agentEntity3.getCount());
			System.out.println(list3.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIMAppOperationStatis() {
		String startTime = "2016-01-01";
		String endTime = "2016-07-30";
		try {
			StcIMAppOperationResponseEntity imEntity = appService.getIMAppOperationStatistics(startTime, endTime, "1,15");
					
			List<StcIMAppOperationEntity> list = imEntity.getImAppOperationEntities();
			System.out.println(imEntity.getCount());
			System.out.println(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
