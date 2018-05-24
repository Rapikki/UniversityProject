package com.foxminded.university.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "teacher")
public class Teacher extends Person implements Serializable {
    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return department == teacher.department;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), department);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                ", position='" + position + '\'' +
                '}';
    }
}
