package com.company.services;

import com.company.models.Employee;
import com.company.models.Organization;

import java.util.Collection;
import java.util.List;

/**
 * Т.к. постановка дана именно на Сериализуем & Десериализуем пару из {@link Employee} & {@link Organization}, то
 * предполагаю что тут требуется Специфичный по бизнес логике маппер и в будущем логика может расшириться.
 * В другом случаи, в зависимости формата входящих ванных, может быть достаточно того, что предоставляют стандартные
 * мапперы из библиотек JAXB, Jackson и т.д.
 */
public interface EmployeeXMLParser {
    
    /**
     * @return XML String
     */
    String serialize(Collection<Employee> employees);
    
    /**
     * Т.к. в рамках ТЗ нет уточнений, в таком типе мы хотим использовать данные(List, Set, Queue, Custom Collection).
     * Решил использовать базовый вариант с List<Employee>, так же можно сделать Set<Employee>,
     * если требуется собрать только Уникальные элементы.
     *
     * @param employees XML String
     */
    Collection<Employee> deserialize(String employees);
}
