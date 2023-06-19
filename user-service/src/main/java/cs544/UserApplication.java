package cs544;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cs544.Daos.IRoleDao;
import cs544.Models.Roles;
import cs544.Models.enums.ERoles;
import lombok.RequiredArgsConstructor;


@SpringBootApplication
@RequiredArgsConstructor
public class UserApplication implements CommandLineRunner {

	@Autowired
	private IRoleDao roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}


	@Override
	public void run(String...args) throws Exception {

		List < Roles > role = roleRepository.findAll();
		if (role.size() == 0) {
			Set<Roles> roles = new HashSet<>();
			Roles r1 = new Roles();
			r1.setRole(ERoles.ADMIN);
			Roles r2 = new Roles();
			r2.setRole(ERoles.USER);

			roles.add(r1);
			roles.add(r2);
			// roles.add(new Role());;
			// roles.add(new Role("USER"));
			// roles.add(new Role("STUDENT"));
			// roles.add(new Role("STAFF"));;
			roleRepository.saveAll(roles);
		}
		System.out.println("Server is running!!!!!!");

	}

}