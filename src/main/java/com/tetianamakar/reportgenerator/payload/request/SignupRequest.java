package com.tetianamakar.reportgenerator.payload.request;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private String username;

    private String password;

    private Set<String> role;
}
