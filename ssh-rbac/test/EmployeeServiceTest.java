

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.ssh.domain.Employee;
import com.company.ssh.domain.Permission;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IEmployeeService;
import com.company.ssh.service.IPermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPermissionService permissionService;

	@Test
	public void testSave() {
		Employee e = new Employee();
		e.setName("name");
		e.setPassword("password");
		employeeService.save(e);
	}

	@Test
	public void testUpdate() {
		Employee e = new Employee();
		e.setId(1L);
		e.setName("update");
		employeeService.update(e);
	}

	@Test
	public void testDelete() {
		Employee e = new Employee();
		e.setId(1L);
		employeeService.delete(e);
	}

	@Test
	public void testGet() {
		Employee employee = employeeService.get(1L);
		System.out.println(employee);
	}

	@Test
	public void testList() {
		List<Permission> list = permissionService.list();
		for (Permission permission : list) {
			System.out.println(permission);
		}
/*		List<Employee> list = employeeService.list();
		for (Employee employee : list) {
			System.out.println(employee);
		}
*/	}
	@Test
	public void testQuery() {
		QueryObject qo = new QueryObject();
		PageResult query = employeeService.query(qo);
		System.out.println(query.getPrev());
		System.out.println(query.getNext());
		System.out.println(query.getTotalPage());
		List<?> result = query.getResult();
		for (Object object : result) {
			System.out.println(object);
		}
	}

}
