package com.company.models;

import com.company.xml.adapter.LocalDateXMLAdapter;
import com.company.xml.adapter.LongXMLAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "organizations")
@XmlType(propOrder = {"id", "fullName", "birthday", "organizations"})
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    
    @XmlID
    @XmlJavaTypeAdapter(type = long.class, value = LongXMLAdapter.class)
    private Long id;
    
    /**
     * В рамках ТЗ не было уточнение должно это быть 1 или 3 поля.
     * Из личного опыта, знаю что, там где МЫ используем ФИО, в Англии используют full name как термин.
     * full name - Имя + Титул/Прозвище/Ник/Позывной/Псевдоним(если имеется) + Фамилия
     * Там где мы используем ФИО(Жуков Георгий Константинович)
     * В Англии использовалось full name(Георгий 'Маршал Победы' Жуков)
     */
    private String fullName;
    
    /**
     * 1 - В каком формате на проекте Храним/Используем Дату, решил использовать LocalDate,
     * как наиболее распространённый вариант.
     * 2 - т.к. в рамках ТЗ нет уточнений какой формат у Входящих данных, решил использовать dd.MM.YYYY
     */
    @XmlJavaTypeAdapter(value = LocalDateXMLAdapter.class)
    private LocalDate birthday;
    private List<Organization> organizations;
    
}


