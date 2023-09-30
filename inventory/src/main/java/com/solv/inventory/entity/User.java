package com.solv.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "txn_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Audit{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String name;
    @Column(name="user_email")
    private String email;
    @Column(name = "user_mobile_number")
    private String mobNum;
    @Column(name = "user_type")
    private String userType;

}
