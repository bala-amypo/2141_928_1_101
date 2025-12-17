package com.example.demo.controller;
import java.util.Collection;
import org.springframework.beans.factory.annotation.AutoWired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Studentity;
import com.example.demo.service.Studservice;

@RestController
@RequestMapping("/student")
pucblic class Studctl{
    @AutoWired
    private Studservice ser;

    //post
    @PostMapping("/add")
    public Studentity addStudent(@RequestBody Studentity st){
        return ser.saveData(st);
    }

    //get all
    @GetMapping("/getall")
    public Collection<Studentity> getAllStudents(){
        return ser.getAll();
    }

    //get by id
    @GetMapping("/get/{id}")
    public Studentity getStudentById(@PathVariable int id ){
        return ser.getById(id);
    }

    //put
    @PutMapping()

}