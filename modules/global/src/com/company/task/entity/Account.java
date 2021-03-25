package com.company.task.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "TASK_ACCOUNT")
@Entity(name = "task_Account")
@NamePattern("%s|name")
public class Account extends StandardEntity {
    private static final long serialVersionUID = 7756742954022722894L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "account")
    private List<Order> order;

    @NotNull
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @JoinColumn(name = "PHOTO_ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    private FileDescriptor photo;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "account")
    private List<Contact> contacts;

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Order> getOrder() {
        return order;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setPhoto(FileDescriptor photo) {
        this.photo = photo;
    }

    public FileDescriptor getPhoto() {
        return photo;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}