/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientObjects;

import java.io.Serializable;

/**
 *
 * @author harsh
 */
public class Identifier implements Serializable {

    double relativePosition;
    int siteId;

    public double getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(double relativePosition) {
        this.relativePosition = relativePosition;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
