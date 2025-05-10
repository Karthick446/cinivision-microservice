package com.example.karthick.userService.business.concretes;

import com.example.karthick.userService.business.abstracts.ClaimService;
import com.example.karthick.userService.dao.ClaimDao;
import com.example.karthick.userService.entity.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimDao claimDao;

    @Override
    public Claim getClaimByClaimName(String claimName) {
        return claimDao.findByClaimName(claimName);
    }
}
