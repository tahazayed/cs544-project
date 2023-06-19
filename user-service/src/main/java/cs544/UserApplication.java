package cs544;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs544.Daos.IUserDao;
import cs544.Models.User;
import cs544.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cs544.Daos.IRoleDao;
import cs544.Models.Roles;
import cs544.Models.enums.ERoles;



@SpringBootApplication
public class UserApplication implements CommandLineRunner {

	@Autowired
	private IRoleDao roleRepository;
	@Autowired
	private IUserDao userRepository;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}


	@Override
	public void run(String...args) throws Exception {

		Set<Roles> roles = new HashSet<>();
		Roles adminRole = new Roles();
		adminRole.setRole(ERoles.ADMIN);
		Roles userRole = new Roles();
		userRole.setRole(ERoles.USER);

		roles.add(adminRole);
		roles.add(userRole);

		List < Roles > role = roleRepository.findAll();
		if (role.size() == 0) {


			roleRepository.saveAll(roles);
		}
		List<User> user =userRepository.findAll();
		if(user.size() ==0){

			User adminUser = new User();
			adminUser.setEmail("admin@miu.edu");
			adminUser.setPassword("admin");
			adminUser.setUsername("admin");

			List<Roles> adminUserRoles = new ArrayList<>();

			adminUserRoles.add(adminRole);
			adminUserRoles.add(userRole);
			adminUser.setRoles(adminUserRoles);
			userService.saveUser(adminUser);

			User userUser = new User();
			userUser.setEmail("user@miu.edu");
			userUser.setPassword("user");
			userUser.setUsername("user");

			List<Roles> userRoles = new ArrayList<>();

			userRoles.add(userRole);

			userUser.setRoles(userRoles);
			userService.saveUser(userUser);

		}
		System.out.println("Server is running!!!!!!");

	}

}