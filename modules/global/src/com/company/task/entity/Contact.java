package com.company.task.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Listeners("task_ContactChangedListener")
@Table(name = "TASK_CONTACT")
@Entity(name = "task_Contact")
@NamePattern("%s|account")
public class Contact extends StandardEntity {
    private static final long serialVersionUID = -5350994310050426650L;

    @NotNull
    @Column(name = "CONTACT_TYPE", nullable = false)
    private String contactType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACCOUNT_ID")
    @NotNull
    private Account account;

    @Column(name = "VALUE_")
    private String value;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactType getContactType() {
        return contactType == null ? null : ContactType.fromId(contactType);
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType == null ? null : contactType.getId();
    }

}