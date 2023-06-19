package cs544.daos;

import cs544.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import cs544.models.enums.ERoles;

public interface IRoleDao extends JpaRepository<Roles,Long> {
    
    public Roles findOneByRole(ERoles nameOfRole);
}
