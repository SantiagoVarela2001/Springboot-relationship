package com.springboot.jpa.springboot_jpa_relationship.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "client_id") //si no lo agregamos hace una tabla intermedia
    @JoinTable(
        name = "tbl_clientes_to_direcciones", 
        joinColumns = @JoinColumn(name = "id_cliente"),
        inverseJoinColumns = @JoinColumn(name = "id_direcciones"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"}))
    private List<Address> addresses;

    public Client() {
        this.addresses = new ArrayList<>();
    }
    
    public Client(String name, String lastname) {
        this.addresses = new ArrayList<>();
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", lastname=" + lastname + ", addresses=" + addresses + "]";
    }

   

}
