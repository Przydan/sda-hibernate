package pl.sda.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created by pzawa on 02.02.2017.
 */

@Entity
@Table(name = "Emp")
public class Employee {

    @Id
    @Column(name = "empno")
    private int empno;

    @Column(name = "ename")
    private String ename;


    private String job;

    //@Column(name = "manager")
    private Integer manager;

    @Column(name = "hiredate")
    private Date hiredate;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "commision")
    private BigDecimal commision;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Department dept;

    public Employee() {
    }

    public Employee(int empno, String ename, String job, Integer manager, Date hiredate, BigDecimal salary, BigDecimal commision, Department department) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.manager = manager;
        this.hiredate = hiredate;
        this.salary = salary;
        this.commision = commision;
        this.dept = department;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }


    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empno == employee.empno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", manager='" + manager + '\'' +
                ", hiredate=" + hiredate +
                ", salary=" + salary +
                ", commision=" + commision +'}';
    }
}
