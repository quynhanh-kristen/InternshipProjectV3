package com.internship.InternshipProjectV3.post.impl;

import com.internship.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    public static final short ADMIN = 1;
    public static final short UPLOADER = 2;
}
