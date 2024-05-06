package com.capstone.yeolmaeTeamProject.domain.user.dao;

import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
