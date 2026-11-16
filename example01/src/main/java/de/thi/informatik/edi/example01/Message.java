package de.thi.informatik.edi.example01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
// Lombok generiert alle Konstruktor
@AllArgsConstructor
// Lombok generiert alle Getter / Setter
@Data
//-> start schlägt fehl?
//-> ggf. maven?
public class Message {

    private String msg;


}
