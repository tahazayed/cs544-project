package cs544.Daos;

import cs544.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import cs544.Models.enums.ERoles;

public interface IRoleDao extends JpaRepository<Roles,Long> {
    
    public Roles findOneByRole(ERoles nameOfRole);
}
