module co.edu.uniquindio.proyectosubastaquindio {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires java.logging;
    requires org.mapstruct.processor;
    requires org.mapstruct;
    requires com.rabbitmq.client;


    opens co.edu.uniquindo.proyectosubastaquindio.controllersView;
    opens co.edu.uniquindo.proyectosubastaquindio.controller;
    exports co.edu.uniquindo.proyectosubastaquindio.controllersView ;
    exports co.edu.uniquindo.proyectosubastaquindio.controller;
    exports co.edu.uniquindo.proyectosubastaquindio.model;
    exports co.edu.uniquindo.proyectosubastaquindio.mapping.dto;
    exports co.edu.uniquindo.proyectosubastaquindio.model.enums;
    exports co.edu.uniquindo.proyectosubastaquindio.mapping.mappers;



}