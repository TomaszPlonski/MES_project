package com.github.tomaszplonski.mes_project.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="expansion_joints")
public class ExpansionJoint extends Product{

    private String bridgeName;

    private String type;

    private double length;



}
