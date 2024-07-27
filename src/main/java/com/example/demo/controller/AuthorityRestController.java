package com.example.demo.controller;


import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.AuthorityDAO;
import com.example.demo.dao.RoleDAO;
import com.example.demo.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorityRestController {
    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    AccountDAO accountDAO;

    @GetMapping("/rest/authorities")
    public Map<String, Object> getAuthorities (){
        Map<String, Object> data = new HashMap<>();
        data.put("authorities", authorityDAO.findAll());
        data.put("roles", roleDAO.findAll());
        data.put("account", accountDAO.findAll());
        return data;
    }
    @PostMapping("/rest/authorities")
    public Authority create(@RequestBody Authority authority){
        return authorityDAO.save(authority);
    }
    @DeleteMapping("/rest/authorities/{id}")
    public  void  delete (@PathVariable("id") Integer id){
            authorityDAO.deleteById(id);
    }

}
