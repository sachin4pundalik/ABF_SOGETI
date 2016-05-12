package com.sogeti.xmlbeans;

public class Month {
	
	private String name;

    private Weeks weeks;
    
    private float total;
    
    public Month(){
    	//this.weeks = new Weeks();
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Weeks getWeeks ()
    {
        return weeks;
    }

    public void setWeeks (Weeks weeks)
    {
        this.weeks = weeks;
    }

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
