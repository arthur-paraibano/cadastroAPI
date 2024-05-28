/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cadastro.api.resource;

import com.cadastro.api.services.PessoaService;
import com.cadastro.api.util.base.BaseDados;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("cadastro")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        
        System.out.println("-------------------------------");
        BaseDados.testConnection();
        System.out.println("-------------------------------");
        System.out.println("*******************************");
       
        Set<Class<?>> resource = new HashSet<>();
        resource.add(PessoaService.class);
//        resource.add(AreaRuralService.class);
        return resource; //To change body of generated methods, choose Tools | Templates.
    }       
    
}
