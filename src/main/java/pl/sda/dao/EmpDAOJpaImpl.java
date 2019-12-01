package pl.sda.dao;

import pl.sda.domain.Department;
import pl.sda.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by marek
 */
public class EmpDAOJpaImpl implements EmpDAO {
    private final EntityManagerFactory emf;

    public EmpDAOJpaImpl(EntityManagerFactory em) {
        this.emf = em;
    }

    @Override
    public Employee findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void create(Employee employee) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(employee);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Employee employee) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(employee);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }

    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Employee emp = em.find(Employee.class, id);
            em.remove(emp);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void create(List<Employee> employees) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            employees.forEach(em::persist);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public BigDecimal getTotalSalaryByDept(int dept) {
        // TODO: implement method
        return null;
    }

    @Override
    public List<Employee> getEmployeesByDept(int deptNo) {
        // TODO: implement method
        return null;
    }

    @Override
    public List<Employee> getEmployeeByName(String ename) {
        // TODO: implement method
        return null;
    }
}
