package com.acar.project.repos;

import com.acar.project.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
     RefreshToken findByUserId(Long userId);
}
