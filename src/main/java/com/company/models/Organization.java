package com.company.models;

import com.company.jaxb.adapters.LocalDateXMLAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "employees")
@XmlType(propOrder = {"id", "name", "createdAt", "status", "employees"})
@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization {
    
    private Long id;
    
    private String name;
    /**
     * 1 - В каком формате на проекте Храним/Используем Дату, решил использовать LocalDate,
     * как наиболее распространённый вариант.
     * 2 - т.к. в рамках ТЗ нет уточнений какой формат у Входящих данных, решил использовать dd.MM.YYYY
     */
    
    @XmlJavaTypeAdapter(value = LocalDateXMLAdapter.class)
    private LocalDate createdAt;
    /**
     * Можно делать boolean, однако мир не предсказуем и может сдаться что Организация активна,
     * но не доступна по каким-то причинам, в таком случаи Enum смотрится как выгодное решение, а boolean нет.
     */
    private Status status;
    
    @XmlIDREF
    private List<Employee> employees;
    
    
    /**
     * Можно делать отдельный файл-класс и в зависимости от правил на проекте, возможно для всех models-enum
     * есть отдельная папочка, например: com.company.models.enums
     * Я Решил делать внутри модели по 2 причинам:
     * 1 - Модель не является Огромным классом на 100-200+ строк + enum занимает 3 строчки и отдельный файл на 3 строчки,
     * только сильнее раздувает кодовую базу проекта и уменьшает читабельность при массовых применениях.
     * 2 - нейминг Enum и обращение через класс модель.
     * Был живой кейс с 3 моделями с похожими названиями, из разных областей применения в одном проекте.
     * ExternalAccount
     * ExternalBankAccount
     * ExternalBankAccountBalance
     * К каждой модели был + свой enum status и другие enum. В итоге это выглядело +- так:
     * ExternalAccountStatus
     * ExternalBankAccountStatus
     * ExternalBankAccountBalanceStatus
     * ExternalBankAccountBalanceOneMoreEnum
     * После было понятно что в коде было бы
     * проще юзать ExternalBankAccountBalance.Status.ACTIVE чем ExternalBankAccountBalanceStatus.ACTIVE
     * т.к. имя используемой модели прям перед глазами, а через auto-complete нужно ещё внимательно посмотреть, что выбрать
     * + другие мелкие бытовые нюансы
     */
    public enum Status {
        OPEN, CLOSE
    }
}
