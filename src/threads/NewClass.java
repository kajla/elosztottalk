/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.Serializable;

/**
 *
 * @author kajla
 */
public class NewClass implements Serializable {
    private String id;

    public NewClass(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NewClass{" + "id=" + id + '}';
    }
}
