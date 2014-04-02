package survey;

import org.junit.Test;

import com.sniper.survey.custom.rbac.Rbac;
import com.sniper.survey.custom.rbac.Role;
import com.sniper.survey.custom.rbac.RoleInterface;

public class TestRbac {

	@Test
	public void test() {
		RoleInterface foo = new Role("foo");
		RoleInterface foo1 = new Role("foo1");
		RoleInterface foo2 = new Role("foo2");
		RoleInterface foo3 = new Role("foo3");
		
		Rbac rbac = new Rbac();
		
		foo.addChild(foo1);
		foo.addChild(foo2);
		
		rbac.addRole(foo);
		rbac.addRole(foo3);
		
		
		//System.out.println(rbac.getRole("foo").getName());
		String resource = "/admin/user/edit";
		String resource2 = "/admin/user/add";
		
		foo.addPermission(resource);
		
		System.out.println(rbac.isGranted(foo, resource));
		System.out.println(rbac.isGranted(foo, resource2));
		
		System.out.println(rbac.isGranted(foo3, resource));
		System.out.println(rbac.isGranted(foo3, resource2));
	}

}
