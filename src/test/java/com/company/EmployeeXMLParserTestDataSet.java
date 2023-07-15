package com.company;


import com.company.models.Employee;
import com.company.models.Organization;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Класс хранитель тест данных для {@link EmployeeXMLParserTest}. Хотелось разделить на части:
 * - логику подготовки тест данных
 * - логику самих test-case
 * <p>
 * Тестовые данные в человеко-читаемом виде:
 * <p>
 * Сотрудники:
 * Иванов Иван Иванович         15.02.1984
 * Смирнова Елена Александровна 29.07.1991
 * Петров Сергей Владимирович   10.04.1987
 * Соколова Анна Игоревна       05.09.1996
 * Кузнецов Дмитрий Алексеевич  22.11.1983
 * <p>
 * Организации:
 * ООО "Рога и копыта",         10.03.2005, OPEN
 * ЗАО "Прогресс",              05.12.1998, CLOSE
 * ООО "Солнечный мир",         21.07.2010, OPEN
 * АО "Спутник",                15.09.1995, CLOSE
 * НКО "Благотворительность",   30.11.2012, OPEN
 * <p>
 * Данные были получены благодаря ChatGPT
 */
@Getter
public class EmployeeXMLParserTestDataSet {
    private static final String EXPECTED_XML_FILENAME = "expected.xml";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    private List<Employee> testEmployees;
    private List<Organization> testOrganizations;
    private String expectedXML;
    
    public EmployeeXMLParserTestDataSet() {
        prepareInputData();
        prepareExpectedData();
    }
    
    @BeforeEach
    public void prepareInputData() {
        var employee1 = new Employee(1L, "Иванов Иван Иванович", dateOf("15.02.1984"), List.of());
        var employee2 = new Employee(2L, "Смирнова Елена Александровна", dateOf("29.07.1991"), List.of());
        var employee3 = new Employee(3L, "Петров Сергей Владимирович", dateOf("10.04.1987"), List.of());
        var employee4 = new Employee(4L, "Соколова Анна Игоревна", dateOf("05.09.1996"), List.of());
        var employee5 = new Employee(5L, "Кузнецов Дмитрий Алексеевич", dateOf("22.11.1983"), List.of());
        var employee6 = new Employee(6L, "Николаева Ольга Александровна", dateOf("17.09.1988"), List.of());
        this.testEmployees = List.of(employee1, employee2, employee3, employee4, employee5, employee6);
        
        var organization1 = new Organization(1L, "ООО \"Рога и копыта\"", dateOf("10.03.2005"), Organization.Status.OPEN, List.of());
        var organization2 = new Organization(2L, "ЗАО \"Прогресс\"", dateOf("05.12.1998"), Organization.Status.CLOSE, List.of());
        var organization3 = new Organization(3L, "ООО \"Солнечный мир\"", dateOf("21.07.2010"), Organization.Status.OPEN, List.of());
        var organization4 = new Organization(4L, "АО \"Спутник\"", dateOf("15.09.1995"), Organization.Status.CLOSE, List.of());
        var organization5 = new Organization(5L, "НКО \"Благотворительность\"", dateOf("30.11.2012"), Organization.Status.OPEN, List.of());
        this.testOrganizations = List.of(organization1, organization2, organization3, organization4, organization5);
        
        employee1.setOrganizations(List.of(organization1)); // e1 - [o1]
        employee2.setOrganizations(List.of(organization2, organization3)); // e2 - [o2,o3]
        employee3.setOrganizations(List.of(organization3, organization4, organization5)); // e3 - [o3,o4,o5]
//        employee4.setOrganizations(List.of()); // e4 - []
        employee5.setOrganizations(testOrganizations); // e5 - [o1,o2,o3,o4,o5]
        
        
        organization1.setEmployees(List.of(employee1, employee5)); // o1 - [e1,e5]
        organization2.setEmployees(List.of(employee2, employee5)); // o2 - [e2,e5]
        organization3.setEmployees(List.of(employee2, employee3, employee5)); // o3 - [e2,e3,e5]
        organization4.setEmployees(List.of(employee3, employee5)); // o4 - [e3,e5]
        organization5.setEmployees(List.of(employee3, employee5)); // o5 - [e3,e5]
    }
    
    /**
     * Момент с replaceAll() - нужен, что бы, файлы с тест данными созданные на разных ОС, одинаково проходили тесты и было меньшее магии.
     */
    private void prepareExpectedData() {
        try (var input = getClass().getClassLoader().getResourceAsStream(EXPECTED_XML_FILENAME)) {
            if (input == null) throw new RuntimeException(String.format(
                    "Fail to open InputStream for test file with name \"%s\". " +
                            "Check classpath and filename spelling!", EXPECTED_XML_FILENAME));
            this.expectedXML = new String(input.readAllBytes())
                    .replaceAll("\\r\\n", "\n")
                    .replaceAll("\\r", "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private LocalDate dateOf(String text) {
        return LocalDate.from(formatter.parse(text));
    }
    
}
