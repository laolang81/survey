package survey;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.service.impl.AdminRightService;

public class TestTree {

	private static AdminRightService rightService;

	@Before
	public void iniChannelService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		rightService = (AdminRightService) ctx.getBean("adminRightService");


	}

	@Test
	public void test() {
		
		List<AdminRight> adminRights = rightService.findAllEntitles();
		Right root = new Right();
		root.id = 0;
		for (AdminRight adminRight : adminRights) {
			Right right = new Right(adminRight);
			root.add(right);
		}
		root.output();
	}

	class Right
	{
		public int id = 0;
		public int parentId = 0;
		public String name;
		public Right parent;
		public int level = 0;
		
		protected List<Right> children = new ArrayList<>();
		
		public Right(){}
		
		public Right(AdminRight right)
		{
			id = right.getId();
			parentId = right.getFid();
			name = right.getName();
		}
		
		public boolean add(Right right)
		{
			if (right.parentId == id) {
				right.parent = right;
				right.level = level + 1;
				children.add(right);
			}
			for (Right r : children) {
				boolean success = r.add(right);
				if (success) {
					return true;
				}
			}
			return false;
		}
		
		public void output()
		{
			String prefix = "";
			for (int i = 0; i < level; i++) {
				prefix += "-";
			}
			System.out.println( prefix + id + name );
			for (Right child : children) {
				child.output();
			}
		}
	}
}
