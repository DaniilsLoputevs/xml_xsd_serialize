package com.company;

import com.company.models.Employee;
import com.company.services.EmployeeXMLParser;
import com.company.services.EmployeeXMLParserImpl;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * В Файле описаны все тест-кейсы для {@link EmployeeXMLParser}.
 * <p>
 * Именно в interface описаны все базовые тест-кейсы для любой Реализации {@link EmployeeXMLParser}.
 * То есть, все реализации должны проходить все тесты, что описаны в interface.
 * <p>
 * В классах реализации, описаны Специфичные только для конкретно этой реализации, тест-кейсы.
 * <p>
 * Такой подход позволяет быстро и понятно расширять тесты для новых реализаций.
 */
public interface EmployeeXMLParserTest {
    EmployeeXMLParserTestDataSet DATA = new EmployeeXMLParserTestDataSet();
    
    /**
     * Метод getter для получения Тестируемого объекта в ходе написания тест-кейсов.
     * Переопределяется для каждой реализации {@link EmployeeXMLParserTest}
     * <br>
     * Можно понимать как поле внутри тест-класса.
     * <pre>{@code
     * EmployeeXMLParser service = new EmployeeXMLParserImpl();
     * @Autowired EmployeeXMLParser service; // В случаи Spring
     * }</pre>
     */
    EmployeeXMLParser getImpl();
    
    class DefaultImplTest implements EmployeeXMLParserTest {
        private final EmployeeXMLParser service = new EmployeeXMLParserImpl();
        
        @Override public EmployeeXMLParser getImpl() {
            return service;
        }
    }
    
    
    /* interface test-cases */
    
    
    /**
     * Сериализуем коллекцию {@link Employee} в String XML.
     * Предположительно, далее этот String XML отправится в сеть(HTTP socket, massage broker и т.д.)
     */
    @Test default void serialize() {
        List<Employee> input = DATA.getInputEmployees();
        String expected = DATA.getExpectedDataXML();
        String rsl = this.getImpl().serialize(input);
        System.out.println(rsl);
        assertEquals(expected, rsl);
    }
    
    /**
     * Десериализуем String XML в коллекцию {@link Employee}.
     * Предположительно, этот String XML мы получили из сети(HTTP socket, massage broker и т.д.)
     */
    @Test default void deserialize() {
        String input = DATA.getExpectedDataXML();
        List<Employee> expected = DATA.getInputEmployees();
        Collection<Employee> rsl = this.getImpl().deserialize(input);
        assertEquals(expected, rsl);
    }
    
    /**
     * Генерируем String XSD.
     * Предположительно, этот String XSD мы запишем в файл.
     */
    @Test default void generateSchema() {
        String expected = DATA.getExpectedSchemaXSD();
        String rsl = this.getImpl().generateSchemaForSupportedClass();
        assertEquals(expected, rsl);
    }
    
}
