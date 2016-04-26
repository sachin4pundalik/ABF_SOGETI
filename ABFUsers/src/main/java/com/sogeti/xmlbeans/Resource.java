package com.sogeti.xmlbeans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Resource")
public class Resource {
	
	private int id;

    private Months months;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public Months getMonths ()
    {
        return months;
    }

    public void setMonths (Months months)
    {
        this.months = months;
    }

}
