package survey;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sniper.survey.model.Post;
import com.sniper.survey.service.impl.PostService;

public class TestPost {

	ApplicationContext ctx = null;

	@Before
	public void iniChannelService() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");

	}

	@Test
	public void testPost() {
		PostService postService = (PostService) ctx.getBean("postService");
		String hqlwhere = " 1=1";
		hqlwhere += " and  status  =1";
		//hqlwhere += " and  " + "c.id in(1,2,3,4,5,6,7)";
		//postService.setJoin(" LEFT JOIN " + postService.getEntityAsName()+ ".channels as c");

		postService.setWhere(hqlwhere);
		postService.setOrder("id desc");
		postService.pageList(200);
		List<Post> list = postService.getLists();
		//System.out.println(list);
		System.out.println("---->");
		for(Post post: list){
			System.out.println(post.getName());
		}
		System.out.println("<----");
	}
}
