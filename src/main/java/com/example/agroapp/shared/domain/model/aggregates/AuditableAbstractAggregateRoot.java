package com.example.agroapp.shared.domain.model.aggregates;

import lombok.Data;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
public abstract class AuditableAbstractAggregateRoot {

}