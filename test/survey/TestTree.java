package survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.model.AdminRight;
import com.sniper.survey.model.MeetUser;
import com.sniper.survey.service.impl.AdminRightService;
import com.sniper.survey.service.impl.MeetUserService;
import com.sniper.survey.util.PathUtil;
import com.sniper.survey.util.PoiExeclExportUtil;

public class TestTree {

	ApplicationContext ctx = null;

	@Before
	public void iniChannelService() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");

	}

	@After
	public void after() {

	}

	@Test
	public void test1() throws IOException, IllegalAccessException {
		MeetUserService meetUserService;
		meetUserService = (MeetUserService) ctx.getBean("meetUserService");

		PoiExeclExportUtil<MeetUser> execlExportUtil = new PoiExeclExportUtil<>();
		List<MeetUser> list = meetUserService.findAllEntitles();
		execlExportUtil.setList(list);
		execlExportUtil.intoData();
		PathUtil pathUtil = new PathUtil();
		String path = pathUtil.getWebRoot();
		execlExportUtil.write(path + "/data/人员采集名单.xlsx");
	}

	public void test() {

		AdminRightService rightService;
		rightService = (AdminRightService) ctx.getBean("adminRightService");
		List<AdminRight> adminRights = rightService.findAllEntitles();
		Right root = new Right();
		root.id = 0;
		for (AdminRight adminRight : adminRights) {
			Right right = new Right(adminRight);
			root.add(right);
		}
		root.output();
	}

	class Right {
		public int id = 0;
		public int parentId = 0;
		public String name;
		public Right parent;
		public int level = 0;

		protected List<Right> children = new ArrayList<>();

		public Right() {
		}

		public Right(AdminRight right) {
			id = right.getId();
			parentId = right.getFid();
			name = right.getName();
		}

		public boolean add(Right right) {
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

		public void output() {
			String prefix = "";
			for (int i = 0; i < level; i++) {
				prefix += "-";
			}
			System.out.println(prefix + id + name);
			for (Right child : children) {
				child.output();
			}
		}
	}

	
}
