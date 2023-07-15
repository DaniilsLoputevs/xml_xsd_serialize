package com.company.jaxb.wrappers;

import com.company.models.Employee;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

/**
 * Класс адаптер под XML список элементов класса {@link Employee}.
 * <p>
 * JAXB требует аннотации @XmlRootElement поверх большинства классов, которые мы собираемся маршалировать или демаршалировать.
 * Классы из java collection framework не имеет аннотаций JAXB. Что бы, работало нужен класс, который будет представлять набор сотрудников.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@XmlRootElement(name = "employees")
public class Employees {
    private Collection<Employee> employees;
    
}
