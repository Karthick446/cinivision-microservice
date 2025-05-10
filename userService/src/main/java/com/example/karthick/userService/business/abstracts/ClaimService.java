package com.example.karthick.userService.business.abstracts;

import com.example.karthick.userService.entity.Claim;

public interface ClaimService {

    Claim getClaimByClaimName(String claimName);
}
