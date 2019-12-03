package pl.sda.dao;

import pl.sda.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BigDecimal> query = em.createQuery("select sum(salary) from Employee where dept.deptno = :dept", BigDecimal.class);
            query.setParameter("dept", dept);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Employee> getEmployeesByDept(int deptNo) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em
                    .createQuery("from Employee where dept.deptno = :deptNo", Employee.class);
            query.setParameter("deptNo", deptNo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Employee> getEmployeeByName(String ename) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em
                    .createQuery("from Employee where ename = :ename", Employee.class);
            query.setParameter("ename", ename);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
