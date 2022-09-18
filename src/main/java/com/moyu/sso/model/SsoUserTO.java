package com.moyu.sso.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SsoUserTO {
    private String userid;
    private String username;

    private String code;
    private String msg;
}
