package com.foxminded.university.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student extends Person implements Serializable {

    @Column(name = "card_number")
    private String cardNumber;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(cardNumber, student.cardNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), cardNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
