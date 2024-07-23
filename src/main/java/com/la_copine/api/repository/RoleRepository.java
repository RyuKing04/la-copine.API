package com.la_copine.api.repository;

import com.la_copine.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
}
