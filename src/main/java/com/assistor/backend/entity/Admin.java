package com.assistor.backend.entity;


import com.assistor.backend.entity.base.MongoDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.assistor.backend.entity.collections.Collections.ADMIN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(ADMIN)
public class Admin extends MongoDocument {
    private String name;
    private String email;
    private String password;
}
